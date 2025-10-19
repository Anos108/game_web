package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Bricks {

    public abstract static class Brick {
        private final double x, y;
        private final double width, height;

        static final Image img;

        static {
            try {
                img =
                        new Image(
                                Objects.requireNonNull(
                                        Brick.class.getResourceAsStream(Config.IMAGE_PATH + "brickresize.jpg")));
            } catch (NullPointerException e) {
                System.err.println(
                        "Không tìm thấy file ảnh brickresize.jpg. Kiểm tra lại đường dẫn trong thư mục asset/images!");
                throw new RuntimeException(e);
            }
        }

        Brick(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        protected void render(GraphicsContext gc, Color tintColor) {
            // Lưu trạng thái hiện tại của GraphicsContext
            gc.save();

            // Vẽ ảnh gốc
            gc.drawImage(img, this.x, this.y, Config.brickWidth, Config.brickHeight);

            // Áp dụng màu tint
            gc.setGlobalBlendMode(javafx.scene.effect.BlendMode.MULTIPLY);
            gc.setFill(tintColor);
            gc.fillRect(this.x, this.y, Config.brickWidth, Config.brickHeight);

            // Khôi phục trạng thái GraphicsContext
            gc.restore();
        }

        public abstract void render(GraphicsContext gc);
    }

    public static class BrickOrange extends Brick {
        BrickOrange(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.ORANGE);
        }
    }

    public static class BrickRed extends Brick {
        BrickRed(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.RED);
        }
    }

    public static class BrickGreen extends Brick {
        BrickGreen(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.GREEN);
        }
    }

    public static class BrickBlue extends Brick {
        BrickBlue(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.BLUE);
        }
    }

    public static class BrickPink extends Brick {
        BrickPink(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.PINK);
        }
    }

    public static class BrickLightBlue extends Brick {
        BrickLightBlue(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.LIGHTBLUE);
        }
    }

    public static class BrickYellow extends Brick {
        BrickYellow(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.YELLOW);
        }
    }

    public static class BrickPurple extends Brick {
        BrickPurple(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void render(GraphicsContext gc) {
            render(gc, Color.MAGENTA);
        }
    }
}