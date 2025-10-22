package org.example.demo2;

import javafx.geometry.Rectangle2D;

public class Physic {

    public static Rectangle2D intersect(Rectangle2D r1, Rectangle2D r2) {
        double x = Math.max(r1.getMinX(), r2.getMinX());
        double y = Math.max(r1.getMinY(), r2.getMinY());
        double w = Math.min(r1.getMaxX(), r2.getMaxX()) - x;
        double h = Math.min(r1.getMaxY(), r2.getMaxY()) - y;

        if (w <= 0 || h <= 0) {
            return new Rectangle2D(0, 0, 0, 0);  // Không giao nhau
        }

        return new Rectangle2D(x, y, w, h);
    }
    public static void ballPaddle(Ball ball, Paddle paddle) {
        Rectangle2D b = ball.getBounds();
        Rectangle2D p = paddle.getBounds();

        // 1) Chỉ xử lý khi có va chạm thật
        if (!b.intersects(p)) return;

        // 2) Tính tâm theo bounds thực (không hardcode kích thước)
        double ballC = b.getMinX() + b.getWidth()  / 2.0;
        double padC  = p.getMinX() + p.getWidth()  / 2.0;

        // 3) Lệch chuẩn hóa [-1..1] (trái = -1, giữa = 0, phải = 1)
        double offset = (ballC - padC) / (p.getWidth() / 2.0);
        if (offset < -1) offset = -1;
        else if (offset > 1) offset = 1;

        // 4) Đặt góc lệch cho Ball (nếu có ANGLE_FACTOR thì nhân; nếu không, giữ nguyên)
        double angle = offset*0.6;

        ball.setAngleOffset(angle);

        // 5) Ép bóng bật lên (hệ toạ độ JavaFX: y tăng là đi xuống)
        ball.setInteractY();

        // 6) Tách bóng ra khỏi paddle để tránh kẹt
        ball.setY(p.getMinY() - b.getHeight() - 0.1);


    }
    public static void ballBrickCollision(Ball ball, Bricks.Brick brick) {


        Rectangle2D b = ball.getBounds();
        Rectangle2D r = brick.getBounds();
        if (!b.intersects(r)) return;

        // Độ chồng lấn bốn phía
        double overlapLeft   = b.getMaxX() - r.getMinX();
        double overlapRight  = r.getMaxX() - b.getMinX();
        double overlapTop    = b.getMaxY() - r.getMinY();
        double overlapBottom = r.getMaxY() - b.getMinY();

        double minXOverlap = Math.min(overlapLeft, overlapRight);
        double minYOverlap = Math.min(overlapTop,  overlapBottom);

        final double EPS = 0.1; // đẩy nhẹ ra ngoài để không kẹt

        if (minXOverlap < minYOverlap) {
            // Va chạm trục X → đẩy ra theo X và đảo interactX
            if (overlapLeft < overlapRight) {
                ball.setX(ball.getX() - (overlapLeft + EPS));
            } else {
                ball.setX(ball.getX() + (overlapRight + EPS));
            }
            ball.setInteractX();
        } else if (minYOverlap < minXOverlap) {
            // Va chạm trục Y → đẩy ra theo Y và đảo interactY
            if (overlapTop < overlapBottom) {
                ball.setY(ball.getY() - (overlapTop + EPS));
            } else {
                ball.setY(ball.getY() + (overlapBottom + EPS));
            }
            ball.setInteractY();
        } else {
            // Góc: chạm cả X và Y
            ball.setInteractX();
            ball.setInteractY();
        }


    }



}
