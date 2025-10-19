package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
    private double x, y;
    private double velocityX = 3; // Vận tốc theo trục X
    private double velocityY = 3; // Vận tốc theo trục Y
    private static final double BALL_SIZE = 20; // Kích thước bóng
    static Image img;

    Ball(double x, double y) {
        this.x = x;
        this.y = y;
        img = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH + "ball.png")
        ));
    }

    void render(GraphicsContext gc) {
        gc.drawImage(img, x, y, BALL_SIZE, BALL_SIZE);
    }

    void update() {
        // Di chuyển bóng theo vận tốc
        this.x += velocityX;
        this.y += velocityY;
    }

    // Va chạm với tường
    void checkWallCollision() {
        // Tường trái và phải
        if (x <= 0 || x >= Config.WIDTH - BALL_SIZE) {
            velocityX = -velocityX;
            // Đảm bảo bóng không bị kẹt trong tường
            if (x <= 0) x = 0;
            if (x >= Config.WIDTH - BALL_SIZE) x = Config.WIDTH - BALL_SIZE;
        }
        // Tường trên
        if (y <= 0) {
            velocityY = -velocityY;
            y = 0;
        }
        // Tường dưới (game over - có thể thêm logic xử lý sau)
        if (y >= Config.HEIGHT) {
            resetBall();
        }
    }

    // Va chạm với paddle
    void checkPaddleCollision(Paddle paddle) {
        double paddleX = paddle.getX();
        double paddleY = paddle.getY();
        double paddleWidth = paddle.getWidth();
        double paddleHeight = 20; // Chiều cao paddle thực tế

        // Kiểm tra va chạm
        if (x + BALL_SIZE >= paddleX && x <= paddleX + paddleWidth &&
            y + BALL_SIZE >= paddleY && y <= paddleY + paddleHeight) {
            
            velocityY = -Math.abs(velocityY); // Đảm bảo bóng nảy lên
            y = paddleY - BALL_SIZE; // Đặt bóng ngay phía trên paddle

            // Thay đổi góc nảy dựa trên vị trí va chạm trên paddle
            double hitPos = (x + BALL_SIZE / 2 - paddleX) / paddleWidth;
            velocityX = (hitPos - 0.5) * 6; // Tạo góc nảy từ -3 đến 3
        }
    }

    // Va chạm với brick
    boolean checkBrickCollision(Bricks.Brick brick) {
        double brickX = brick.getX();
        double brickY = brick.getY();
        double brickWidth = brick.getWidth();
        double brickHeight = brick.getHeight();

        // Kiểm tra va chạm
        if (x + BALL_SIZE >= brickX && x <= brickX + brickWidth &&
            y + BALL_SIZE >= brickY && y <= brickY + brickHeight) {
            
            // Xác định va chạm từ phía nào
            double overlapLeft = x + BALL_SIZE - brickX;
            double overlapRight = brickX + brickWidth - x;
            double overlapTop = y + BALL_SIZE - brickY;
            double overlapBottom = brickY + brickHeight - y;

            double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                        Math.min(overlapTop, overlapBottom));

            // Nảy theo hướng va chạm
            if (minOverlap == overlapLeft || minOverlap == overlapRight) {
                velocityX = -velocityX;
            } else {
                velocityY = -velocityY;
            }

            return true; // Brick bị phá
        }
        return false;
    }

    // Reset bóng về vị trí ban đầu
    void resetBall() {
        this.x = Config.ballX;
        this.y = Config.ballY;
        this.velocityX = 3;
        this.velocityY = 3;
    }

    public void setBall(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSize() { return BALL_SIZE; }
}
