package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GameView extends Application {
  private List<Ball> balls;
  private Paddle paddle;
  private List<Bricks.Brick> bricks;
  private List<PowerUp> powerUps;
  private SoundManager soundManager;
  public static GameplayManager gameplayManager;

  @Override
  public void start(Stage stage) throws IOException {
    stage.setTitle("Arkanoid");

    // Load MainMenu FXML
    Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/MainMenu.fxml"));
    Scene menuScene = new Scene(root, Config.WIDTH, Config.HEIGHT);
    stage.setScene(menuScene);
    stage.show();
  }

  public void setDifficulty(String difficulty) {
    Config.difficulty = difficulty;
  }

  public void startGame(Stage stage) {
    Canvas canvas = new Canvas(Config.WIDTH, Config.HEIGHT);
    Config.gc = canvas.getGraphicsContext2D();
    Config.primaryStage = stage;
    StackPane root = new StackPane(canvas);
    Scene scene = new Scene(root, Config.WIDTH, Config.HEIGHT);

    stage.setScene(scene);

    gameplayManager = new GameplayManager();
    soundManager = new SoundManager();
    balls = new ArrayList<>();
    powerUps = new ArrayList<>();
    paddle = new Paddle(Config.paddleX, Config.paddleY);
    Config.background =
        new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/background.png")));
    bricks = new ArrayList<>();
    gameplayManager.createLevel(bricks);

    // key sensor
    scene.setOnKeyPressed(
        e -> {
          if (e.getCode() == KeyCode.A) Config.leftPressed = true;
          if (e.getCode() == KeyCode.D) Config.rightPressed = true;
        });
    scene.setOnKeyReleased(
        e -> {
          if (e.getCode() == KeyCode.A) Config.leftPressed = false;
          if (e.getCode() == KeyCode.D) Config.rightPressed = false;
        });

    Config.gameLoop =
        new AnimationTimer() {
          @Override
          public void handle(long now) {
            update();
            render();
          }
        };
    Config.gameLoop.start();
  }

  private void update() {

    paddle.update(Config.leftPressed, Config.rightPressed, paddle.getBounds());

    boolean lostBallThisFrame = false;

    for (Ball ball : balls) {
      gameplayManager.setLevel(ball);

      ball.update();

      // Paddle collision
      if (Config.interact(ball.getBounds(), paddle.getBounds())) {
        soundManager.get_paddle_hit_sound();
        Physic.ballPaddle(ball, paddle);
      }

      lostBallThisFrame = Wall.wallBall(ball);

      // Bricks: (lưu ý chỉ update bricks 1 lần/frame, đoạn dưới tối thiểu sửa va chạm)
      for (Bricks.Brick brick : bricks) {
        brick.Update();
        if (Config.interact(ball.getBounds(), brick.getBounds())) {
          soundManager.get_brick_Hit_Sound();
          Physic.ballBrickCollision(ball, brick);
          brick.healthDown();
          gameplayManager.plusScore(brick.setDestroyed(true));
          if (brick.isDestroyed() && brick.getHasPowerUp()) {
            powerUps.add(new PowerUp(brick.getX(), brick.getY()));
          }
          break;
        }
      }

      for (PowerUp powerUp : powerUps) {
        if (powerUp.update(paddle.getBounds()) == 1) {
          soundManager.get_ball_add_sound();
          gameplayManager.setBall_plus();
          powerUp.setDead();
        } else if (powerUp.update(paddle.getBounds()) == 2) {
          for (Ball ball1 : balls) {
            soundManager.get_ball_add_sound();
            ball1.downSpeed();
            powerUp.setDead();
          }
          ;
        } else if (powerUp.update(paddle.getBounds()) == 3) {
          soundManager.get_ball_add_sound();
          GameplayManager.plusLife();
          powerUp.setDead();
        }
      }
    }

    gameplayManager.cleanLevel(bricks, powerUps, balls);

    // 4) xử lý mất mạng CHỈ 1 LẦN / lần rơi
    if (lostBallThisFrame && balls.isEmpty()) {
      GameplayManager.equalLife(); // nhớ clamp không cho âm
      GameplayManager.setCheckLife(false); // cho phép respawn bóng
      // có thể đặt lại bóng về paddle tại đây nếu muốn
    }

    // 5) spawn bóng mới nếu cần (ngoài vòng for)
    if (!GameplayManager.isCheckLife()) {
      balls.add(new Ball(Config.ballX, Config.ballY));
      GameplayManager.setCheckLife(true);
    }
    for (int i = 0; i < gameplayManager.getBall_add(); i++) {
      balls.add(new Ball(Config.ballX, Config.ballY));
    }
    gameplayManager.setBall_add(0);
    if (GameplayManager.getLife() == 0) { // het game
      gameplayManager.setGameOver(true);
      Config.setScore(GameplayManager.getScore(), Config.difficulty);
      GameplayManager.setCheckLife(false);
    }
    if (gameplayManager.getGameOver()) {
      handleGameOver();
    }
  }

  private void handleGameOver() {

    gameplayManager.setGameOver(true);
    if (Config.gameLoop != null) {
      Config.gameLoop.stop();
    }
    Platform.runLater(this::showGameOver);
  }

  private void showGameOver() {
    if (Config.primaryStage == null) {
      return;
    }
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/org/example/demo2/GameOver.fxml"));
      Parent root = loader.load();
      GameOverController controller = loader.getController();
      controller.setScore(GameplayManager.getScore());
      Scene scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
      Config.primaryStage.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void render() {
    Config.gc.clearRect(0, 0, Config.WIDTH, Config.HEIGHT);
    Config.gc.drawImage(Config.background, 0, 0, Config.WIDTH, Config.HEIGHT);
    GameplayManager.renderLives(Config.gc);
    for (Ball ball : balls) {
      ball.render(Config.gc);
    }
    paddle.render(Config.gc);
    for (Bricks.Brick brick : bricks) {
      brick.render(Config.gc);
    }
    for (PowerUp powerUp : powerUps) {
      powerUp.renderPowerUp(Config.gc);
    }
    Config.gc.setFont(Config.pixelFont); // font + size
    Config.gc.setFill(Color.ORANGE); // màu chữ
    Config.gc.fillText(String.valueOf(GameplayManager.getScore()), 650, 30);

    Config.gc.setFont(Config.pixelFont); // font + size
    Config.gc.setFill(Color.ORANGE); // màu chữ
    Config.gc.fillText(String.valueOf(Config.getScore(Config.difficulty)), 400, 30);
  }
}
