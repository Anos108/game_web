package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GameView extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private GraphicsContext gc;
    //    private Ball ball;
    private List<Ball> balls;
    private Paddle paddle;
    private List<Bricks.Brick> bricks;
    private List<PowerUp> powerUps;
    static Image background;
    private String difficulty;
    private int ball_add=0;


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Arkanoid");

        // Load MainMenu FXML
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/MainMenu.fxml"));
        Scene menuScene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void startGame(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(scene);

//        ball = new Ball(Config.ballX, Config.ballY);
        balls = new ArrayList<>();
        powerUps = new ArrayList<>();
        paddle = new Paddle(Config.paddleX, Config.paddleY);
        background =
                new Image(
                        Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/background.png")));

        // Khởi tạo danh sách gạch và tạo màn chơi
        bricks = new ArrayList<>();
        createLevel();

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

        AnimationTimer gameLoop =
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        update();
                        render();
                    }
                };
        gameLoop.start();
    }

    private void createLevel() {

        Random random = new Random();
        int rows = 4;
        int cols = 8;
        double brickWidth = Config.brickWidth;
        double brickHeight = Config.brickHeight;
        double startX = 65;
        double startY = 50;
        double padding = 15;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double x = startX + c * (brickWidth + padding);
                double y = startY + r * (brickHeight + padding);

                Bricks.Brick newBrick;
                int colorRandom = random.nextInt(4);
                newBrick =
                        switch (colorRandom) {
                            case 0 -> new Bricks.BrickRed(x, y, brickWidth, brickHeight);
                            case 1 -> new Bricks.BrickOrange(x, y, brickWidth, brickHeight);
                            case 2 -> new Bricks.BrickGreen(x, y, brickWidth, brickHeight);
                            default -> new Bricks.BrickPurple(x, y, brickWidth, brickHeight);
                        };
                bricks.add(newBrick);
            }
        }
    }

    private void update() {

        // 1) update paddle 1 lần/frame
        paddle.update(Config.leftPressed, Config.rightPressed, paddle.getBounds());

        boolean lostBallThisFrame = false;

        // 2) duyệt balls bằng index (không thêm/xóa ở đây)
        for (Ball ball : balls) {
            if (difficulty.equals("easy")) {
                ball.setSpeed(3.5);
                ball.setSpeedUp(0.0001);

            } else if (difficulty.equals("medium")) {
                ball.setSpeed(5);
                ball.setSpeedUp(0.0005);

            } else if (difficulty.equals("hard")) {
                ball.setSpeed(6);
                ball.setSpeedUp(0.001);

            }

            ball.update();

            // Paddle collision
            if (Config.interact(ball.getBounds(), paddle.getBounds())) {
                URL music = getClass().getResource(Config.SOUND_PATH + "brick_hit.mp3");
                Media sound = new Media(music.toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                Physic.ballPaddle(ball, paddle);
            }

            // Wall collision
            int w = Wall.check_wall(ball.getBounds());
            if (w == 1) {
                ball.setInteractY();                 // top
            } else if (w == 2 || w == 3) {
                ball.setInteractX();                 // left/right
            } else if (w == 4) {                     // bottom -> đánh dấu mất bóng
                lostBallThisFrame = true;
                ball.setDead(true);                  // cờ để remove ở ngoài
            }

            // Bricks: (lưu ý chỉ update bricks 1 lần/frame, đoạn dưới tối thiểu sửa va chạm)
            for (Bricks.Brick brick : bricks) {
                brick.Update();
                if (Config.interact(ball.getBounds(), brick.getBounds())) {
                    URL music = getClass().getResource(Config.SOUND_PATH + "brick_hit.mp3");
                    Media sound = new Media(music.toExternalForm());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                    Physic.ballBrickCollision(ball, brick);
                    brick.healthDown();
                    brick.setDestroyed(true);
                    if (brick.isDestroyed() && brick.getHasPowerUp()) {
                        powerUps.add(new PowerUp(brick.getX(), brick.getY()));
                    }
                    break;
                }
            }
            for (PowerUp powerUp : powerUps) {
                if(powerUp.update(paddle.getBounds())==1){
                    ball_add++;
                    powerUp.setDead();
                }
                else  if(powerUp.update(paddle.getBounds())==2){
                    for (Ball ball1:balls){
                        ball1.downSpeed();
                        powerUp.setDead();
                    };
                }
                else if(powerUp.update(paddle.getBounds())==3) {
                    GameplayManager.plusLife();
                    powerUp.setDead();
                }
            }
        }

        // 3) dọn dẹp ngoài vòng lặp
        balls.removeIf(Ball::isDead);
        bricks.removeIf(Bricks.Brick::isDestroyed);
        powerUps.removeIf(PowerUp::isDead);

        // 4) xử lý mất mạng CHỈ 1 LẦN / lần rơi
        if (lostBallThisFrame && balls.isEmpty()) {
            GameplayManager.equalLife();      // nhớ clamp không cho âm
            GameplayManager.setCheckLife(false);  // cho phép respawn bóng
            // có thể đặt lại bóng về paddle tại đây nếu muốn
        }

        // 5) spawn bóng mới nếu cần (ngoài vòng for)
        if (!GameplayManager.isCheckLife()) {
            balls.add(new Ball(Config.ballX, Config.ballY));
            GameplayManager.setCheckLife(true);
        }
        for (int i=0;i<ball_add;i++){
            balls.add(new Ball(Config.ballX, Config.ballY));
        }
        ball_add=0;
    }

    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        GameplayManager.renderLives(gc);
        for (Ball ball : balls) {
            ball.render(gc);
        }
        paddle.render(gc);
        for (Bricks.Brick brick : bricks) {
            brick.render(gc);
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.renderPowerUp(gc);
        }
    }


}
