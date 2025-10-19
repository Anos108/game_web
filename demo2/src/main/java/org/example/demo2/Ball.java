package org.example.demo2;

import javafx.geometry.Rectangle2D;
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

    // Va chạm với paddle (sử dụng Rectangle2D)
    void checkPaddleCollision(Paddle paddle) {
        Rectangle2D ballBounds = getBounds();
        Rectangle2D paddleBounds = paddle.getBounds();

        // Kiểm tra va chạm sử dụng Rectangle2D.intersects()
        if (ballBounds.intersects(paddleBounds)) {
            velocityY = -Math.abs(velocityY); // Đảm bảo bóng nảy lên
            y = paddle.getY() - BALL_SIZE; // Đặt bóng ngay phía trên paddle

            // Thay đổi góc nảy dựa trên vị trí va chạm trên paddle
            double hitPos = (x + BALL_SIZE / 2 - paddle.getX()) / paddle.getWidth();
            velocityX = (hitPos - 0.5) * 6; // Tạo góc nảy từ -3 đến 3
        }
    }

    // Va chạm với brick (sử dụng Rectangle2D)
    boolean checkBrickCollision(Bricks.Brick brick) {
        Rectangle2D ballBounds = getBounds();
        Rectangle2D brickBounds = brick.getBounds();

        // Kiểm tra va chạm sử dụng Rectangle2D.intersects()
        if (ballBounds.intersects(brickBounds)) {
            // Tính toán overlap để xác định hướng va chạm
            double overlapLeft = ballBounds.getMaxX() - brickBounds.getMinX();
            double overlapRight = brickBounds.getMaxX() - ballBounds.getMinX();
            double overlapTop = ballBounds.getMaxY() - brickBounds.getMinY();
            double overlapBottom = brickBounds.getMaxY() - ballBounds.getMinY();

            // Tìm overlap nhỏ nhất để xác định hướng va chạm chính
            double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                        Math.min(overlapTop, overlapBottom));

            // Nảy theo hướng va chạm
            if (minOverlap == overlapLeft || minOverlap == overlapRight) {
                velocityX = -velocityX; // Va chạm từ trái/phải
            } else {
                velocityY = -velocityY; // Va chạm từ trên/dưới
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

    // Trả về bounds (hình chữ nhật bao quanh) của bóng
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, BALL_SIZE, BALL_SIZE);
    }
}
