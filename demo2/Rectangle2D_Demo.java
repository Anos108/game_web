package org.example.demo2;

import javafx.geometry.Rectangle2D;

/**
 * 🎓 DEMO: Minh họa cách sử dụng Rectangle2D cho Collision Detection
 * 
 * File này CHỈ để học tập, KHÔNG chạy trong game thực tế.
 * Chạy method main() để xem các ví dụ.
 */
public class Rectangle2D_Demo {

    public static void main(String[] args) {
        System.out.println("=== 🎓 RECTANGLE2D COLLISION DETECTION DEMO ===\n");

        // ==========================================
        // VÍ DỤ 1: Tạo Rectangle2D
        // ==========================================
        System.out.println("📌 VÍ DỤ 1: Tạo Rectangle2D");
        System.out.println("─".repeat(50));

        // Tạo rectangle cho bóng
        Rectangle2D ball = new Rectangle2D(100, 200, 20, 20);
        System.out.println("Ball (bóng):");
        printRectangle(ball);

        // Tạo rectangle cho brick
        Rectangle2D brick = new Rectangle2D(80, 180, 93, 30);
        System.out.println("\nBrick (gạch):");
        printRectangle(brick);

        System.out.println("\n");

        // ==========================================
        // VÍ DỤ 2: Kiểm tra va chạm (intersects)
        // ==========================================
        System.out.println("📌 VÍ DỤ 2: Kiểm tra va chạm");
        System.out.println("─".repeat(50));

        boolean collision = ball.intersects(brick);
        System.out.println("ball.intersects(brick) = " + collision);

        if (collision) {
            System.out.println("✅ CÓ VA CHẠM! Bóng đập vào gạch!");
        } else {
            System.out.println("❌ KHÔNG VA CHẠM! Bóng và gạch không chạm nhau.");
        }

        System.out.println("\n");

        // ==========================================
        // VÍ DỤ 3: Tính toán overlap (chồng lấp)
        // ==========================================
        System.out.println("📌 VÍ DỤ 3: Tính overlap để xác định hướng va chạm");
        System.out.println("─".repeat(50));

        if (collision) {
            // Tính overlap từ 4 hướng
            double overlapLeft = ball.getMaxX() - brick.getMinX();
            double overlapRight = brick.getMaxX() - ball.getMinX();
            double overlapTop = ball.getMaxY() - brick.getMinY();
            double overlapBottom = brick.getMaxY() - ball.getMinY();

            System.out.println("Overlap từ trái:  " + overlapLeft + " pixels");
            System.out.println("Overlap từ phải:  " + overlapRight + " pixels");
            System.out.println("Overlap từ trên:  " + overlapTop + " pixels");
            System.out.println("Overlap từ dưới:  " + overlapBottom + " pixels");

            // Tìm overlap nhỏ nhất
            double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                        Math.min(overlapTop, overlapBottom));

            System.out.println("\n➡️ Overlap nhỏ nhất: " + minOverlap);

            // Xác định hướng va chạm
            String direction;
            if (minOverlap == overlapLeft) {
                direction = "TRÁI";
            } else if (minOverlap == overlapRight) {
                direction = "PHẢI";
            } else if (minOverlap == overlapTop) {
                direction = "TRÊN";
            } else {
                direction = "DƯỚI";
            }

            System.out.println("🎯 Hướng va chạm chính: " + direction);
            System.out.println("💡 Bóng nên nảy theo hướng: " + getReflectionDirection(direction));
        }

        System.out.println("\n");

        // ==========================================
        // VÍ DỤ 4: So sánh với cách tính thủ công
        // ==========================================
        System.out.println("📌 VÍ DỤ 4: So sánh Rectangle2D vs Tính thủ công");
        System.out.println("─".repeat(50));

        // Cách 1: Dùng Rectangle2D (KHUYÊN DÙNG)
        long start1 = System.nanoTime();
        boolean result1 = ball.intersects(brick);
        long end1 = System.nanoTime();

        // Cách 2: Tính thủ công (KHÔNG KHUYÊN DÙNG)
        long start2 = System.nanoTime();
        boolean result2 = manualCollisionCheck(
            ball.getMinX(), ball.getMinY(), ball.getWidth(), ball.getHeight(),
            brick.getMinX(), brick.getMinY(), brick.getWidth(), brick.getHeight()
        );
        long end2 = System.nanoTime();

        System.out.println("✅ Rectangle2D.intersects():");
        System.out.println("   Kết quả: " + result1);
        System.out.println("   Thời gian: " + (end1 - start1) + " ns");
        System.out.println("   Code: 1 dòng, dễ hiểu");

        System.out.println("\n❌ Tính thủ công:");
        System.out.println("   Kết quả: " + result2);
        System.out.println("   Thời gian: " + (end2 - start2) + " ns");
        System.out.println("   Code: 4+ dòng, dễ sai");

        System.out.println("\n");

        // ==========================================
        // VÍ DỤ 5: Các method hữu ích khác
        // ==========================================
        System.out.println("📌 VÍ DỤ 5: Các method hữu ích của Rectangle2D");
        System.out.println("─".repeat(50));

        Rectangle2D paddle = new Rectangle2D(300, 750, 120, 20);
        System.out.println("Paddle: " + formatRect(paddle));

        // Kiểm tra điểm có nằm trong rectangle không
        double pointX = 350, pointY = 760;
        boolean containsPoint = paddle.contains(pointX, pointY);
        System.out.println("\nĐiểm (" + pointX + ", " + pointY + ") có trong paddle? " + containsPoint);

        // Kiểm tra rectangle này có chứa hoàn toàn rectangle khác không
        Rectangle2D smallBall = new Rectangle2D(350, 760, 10, 10);
        boolean containsRect = paddle.contains(smallBall);
        System.out.println("Paddle có chứa hoàn toàn ball nhỏ? " + containsRect);

        System.out.println("\n");

        // ==========================================
        // KẾT LUẬN
        // ==========================================
        System.out.println("=== 🎯 KẾT LUẬN ===");
        System.out.println("─".repeat(50));
        System.out.println("✅ Rectangle2D giúp code:");
        System.out.println("   • Ngắn gọn và dễ hiểu hơn");
        System.out.println("   • Ít lỗi logic");
        System.out.println("   • Dễ maintain và mở rộng");
        System.out.println("   • Chuẩn industry practice");
        System.out.println("\n💡 Luôn dùng Rectangle2D thay vì tính thủ công!");
        System.out.println("=".repeat(50));
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================

    private static void printRectangle(Rectangle2D rect) {
        System.out.println("  Position: (" + rect.getMinX() + ", " + rect.getMinY() + ")");
        System.out.println("  Size: " + rect.getWidth() + " x " + rect.getHeight());
        System.out.println("  Left: " + rect.getMinX());
        System.out.println("  Right: " + rect.getMaxX());
        System.out.println("  Top: " + rect.getMinY());
        System.out.println("  Bottom: " + rect.getMaxY());
    }

    private static String formatRect(Rectangle2D rect) {
        return String.format("(x=%.0f, y=%.0f, w=%.0f, h=%.0f)",
            rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
    }

    private static String getReflectionDirection(String collisionDirection) {
        return switch (collisionDirection) {
            case "TRÁI", "PHẢI" -> "ĐẢO VelocityX (nảy ngang)";
            case "TRÊN", "DƯỚI" -> "ĐẢO VelocityY (nảy dọc)";
            default -> "Unknown";
        };
    }

    /**
     * Cách tính collision thủ công (KHÔNG KHUYÊN DÙNG)
     * Chỉ để demo so sánh với Rectangle2D
     */
    private static boolean manualCollisionCheck(
            double x1, double y1, double w1, double h1,
            double x2, double y2, double w2, double h2) {
        
        return x1 + w1 >= x2 && x1 <= x2 + w2 &&
               y1 + h1 >= y2 && y1 <= y2 + h2;
    }
}

