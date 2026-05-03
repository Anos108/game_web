package org.example.demo2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameView extends Application {
  private List<Ball> balls;
  private Paddle paddle;
  private List<Bricks.Brick> bricks;
  private List<PowerUp> powerUps;
  public static GameplayManager gameplayManager;

  @Override
  public void start(Stage stage) throws IOException {
    stage.setTitle("Arkanoid");
    Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/MainMenu.fxml"));
    Scene menuScene = ResponsiveSceneFactory.create(root);
    stage.getIcons().add(new Image(Objects.requireNonNull(Config.class.getResourceAsStream("/asset/images/logo.png"))));
    stage.setScene(menuScene);
    stage.show();
  }

  public void setDifficulty(String difficulty) {
    Config.getInstance().setDifficulty(difficulty);
  }

  public void startGame(Stage stage) {
    Canvas canvas = new Canvas(Config.WIDTH, Config.HEIGHT);
    Config.getInstance().setGc(canvas.getGraphicsContext2D());
    Config.getInstance().setPrimaryStage(stage);

    Image bgImage =
        new Image(
            Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH + "background.png")));

    Region backdrop = new Region();
    backdrop.getStyleClass().add("game-play-backdrop");
    backdrop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    // ~ web CSS filter: blur(8px) brightness(0.4) — JavaFX uses effect chain on the node
    GaussianBlur blur = new GaussianBlur(8);
    ColorAdjust darken = new ColorAdjust();
    darken.setBrightness(-0.6);
    darken.setInput(blur);
    backdrop.setEffect(darken);

    StackPane canvasHost = new StackPane(canvas);
    StackPane.setAlignment(canvas, Pos.CENTER);
    canvasHost.setPrefSize(Config.WIDTH, Config.HEIGHT);
    canvasHost.setMinSize(Config.WIDTH, Config.HEIGHT);
    canvasHost.setMaxSize(Config.WIDTH, Config.HEIGHT);
    canvasHost.setScaleX(ResponsiveSceneFactory.CONTENT_SCALE);
    canvasHost.setScaleY(ResponsiveSceneFactory.CONTENT_SCALE);

    StackPane root = new StackPane(backdrop, canvasHost);
    Scene scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
    scene
        .getStylesheets()
        .add(
            Objects.requireNonNull(getClass().getResource("/org/example/demo2/styles.css"))
                .toExternalForm());

    stage.setScene(scene);

    gameplayManager = new GameplayManager();
    balls = new ArrayList<>();
    powerUps = new ArrayList<>();
    paddle = new Paddle(Config.PADDLE_START_X, Config.PADDLE_START_Y);
    Config.getInstance().setBackground(bgImage);
    bricks = new ArrayList<>();
    gameplayManager.createLevel(bricks);

    SoundManager.getInstance().playGamePlaySound();

    // key sensor
    scene.setOnKeyPressed(
        e -> {
          if (e.getCode() == KeyCode.A) Config.getInstance().setLeftPressed(true);
          if (e.getCode() == KeyCode.D) Config.getInstance().setRightPressed(true);
        });
    scene.setOnKeyReleased(
        e -> {
          if (e.getCode() == KeyCode.A) Config.getInstance().setLeftPressed(false);
          if (e.getCode() == KeyCode.D) Config.getInstance().setRightPressed(false);
        });

    AnimationTimer gameLoop =
        new AnimationTimer() {
          @Override
          public void handle(long now) {
            update();
            render();
          }
        };
    Config.getInstance().setGameLoop(gameLoop);
    gameLoop.start();
  }

  private void update() {
    // Tăng tốc độ
    Config config = Config.getInstance();
    config.setSpeed(config.getSpeed() + config.getSpeedUp());

    paddle.update(config.isLeftPressed(), config.isRightPressed(), paddle.getBounds());

    boolean lostBallThisFrame = false;

    for (Ball ball : balls) {
      gameplayManager.setLevel(ball);
      ball.setSpeed(config.getSpeed());
      ball.update();
      if (config.interact(ball.getBounds(), paddle.getBounds())) {
        SoundManager.getInstance().playPaddleHitSound();
        Physic.ballPaddle(ball, paddle);
      }
      lostBallThisFrame = Wall.wallBall(ball);
      for (Bricks.Brick brick : bricks) {
        brick.Update();
        if (config.interact(ball.getBounds(), brick.getBounds())) {
          Physic.ballBrickCollision(ball, brick);
          brick.healthDown();
          gameplayManager.plusScore(brick.setDestroyed(true));
          if (!brick.isDestroyed()) SoundManager.getInstance().playBrickHitSound();
          if (brick.isDestroyed() && brick.getHasPowerUp()) {
            powerUps.add(new PowerUp(brick.getX(), brick.getY()));
          }
          break;
        }
      }

      for (PowerUp powerUp : powerUps) {
        int powerRes = powerUp.update(paddle.getBounds());
        if (powerRes == 1) {
          SoundManager.getInstance().playBallAddSound();
          gameplayManager.setBall_plus();
          powerUp.setDead();
        } else if (powerRes == 2) {
          for (Ball ball1 : balls) {
            SoundManager.getInstance().playBallAddSound();
            ball1.downSpeed(config.getSpeed());
            powerUp.setDead();
          }
        } else if (powerRes == 3) {
          SoundManager.getInstance().playBallAddSound();
          GameplayManager.plusLife();
          powerUp.setDead();
        }
      }
    }

    gameplayManager.cleanLevel(bricks, powerUps, balls);
    if (lostBallThisFrame && balls.isEmpty()) {
      GameplayManager.equalLife();
      GameplayManager.setCheckLife(false);
    }
    if (bricks.isEmpty()) {
      gameplayManager.createLevel(bricks);
    }

    if (!GameplayManager.isCheckLife()) {
      balls.add(new Ball(Config.BALL_START_X, Config.BALL_START_Y));
      GameplayManager.setCheckLife(true);
    }
    for (int i = 0; i < gameplayManager.getBall_add(); i++) {
      balls.add(new Ball(Config.BALL_START_X, Config.BALL_START_Y));
    }
    gameplayManager.setBall_add(0);
    if (GameplayManager.getLife() == 0) { // het game
      SoundManager.getInstance().stopGamePlaySound();
      SoundManager.getInstance().playGameOverSound();
      gameplayManager.setGameOver(true);
      config.setScore(GameplayManager.getScore(), config.getDifficulty());
      GameplayManager.setCheckLife(false);
    }
    if (gameplayManager.getGameOver()) {
      handleGameOver();
    }
  }

  private void handleGameOver() {
    gameplayManager.setGameOver(true);
    AnimationTimer gameLoop = Config.getInstance().getGameLoop();
    if (gameLoop != null) {
      gameLoop.stop();
    }
    Platform.runLater(this::showGameOver);
  }

  private void showGameOver() {
    Stage primaryStage = Config.getInstance().getPrimaryStage();
    if (primaryStage == null) {
      return;
    }
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/org/example/demo2/GameOver.fxml"));
      Parent root = loader.load();
      GameOverController controller = loader.getController();
      controller.setScore(GameplayManager.getScore());
      Scene scene =
          ResponsiveSceneFactory.create(root, ResponsiveSceneFactory.SceneBackdrop.GAME_OVER);
      primaryStage.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void render() {
    Config config = Config.getInstance();
    config.getGc().clearRect(0, 0, Config.WIDTH, Config.HEIGHT);
    config.getGc().drawImage(config.getBackground(), 0, 0, Config.WIDTH, Config.HEIGHT);
    GameplayManager.renderLives(config.getGc());

    for (Ball ball : balls) {
      ball.render(config.getGc());
    }
    paddle.render(config.getGc());
    for (Bricks.Brick brick : bricks) {
      brick.render(config.getGc());
    }
    for (PowerUp powerUp : powerUps) {
      powerUp.renderPowerUp(config.getGc());
    }

    config.getGc().setFont(Config.PIXEL_FONT);
    config.getGc().setFill(Color.ORANGE);
    config.getGc().fillText(String.valueOf(GameplayManager.getScore()), 650, 30);

    config.getGc().setFont(Config.PIXEL_FONT);
    config.getGc().setFill(Color.ORANGE);
    config.getGc().fillText(String.valueOf(config.getScore(config.getDifficulty())), 400, 30);
  }
}
