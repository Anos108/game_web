package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Bricks {
    public abstract static class Brick {
        private double x, y;
        private double width, height;
        static Image img;

        Brick(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            img =new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream(Config.IMAGE_PATH+"brick.png")));
        }
        void render(GraphicsContext gc) {
            gc.drawImage(img, x, y, Config.brickWidth, Config.brickHeight);
        }
    }

    public static class BrickOrange extends Brick{
        private final double srcX=0;
        private final double srcY=0;
        public final double srcW=70;
        public final double srcH=37;

        BrickOrange(double x, double y, double width, double height){
            super(x, y, width, height);
        }
        @Override
        void render(GraphicsContext gc) {
            gc.drawImage(img,this.srcX,this.srcY,this.srcW,this.srcH,
                    super.x, super.y, Config.brickWidth, Config.brickHeight);
        }
    }

    public static class BrickRed extends Brick{
        private final double srcX=70;
        private final double srcY=0;
        public final double srcW=70;
        public final double srcH=37;

        BrickRed(double x, double y, double width, double height){
            super(x, y, width, height);
        }
        @Override
        void render(GraphicsContext gc) {
            gc.drawImage(img,this.srcX,this.srcY,this.srcW,this.srcH,
                    super.x, super.y, Config.brickWidth, Config.brickHeight);
        }
    }

    public static class BrickPink extends Brick{
        private final double srcX=0;
        private final double srcY=37;
        public final double srcW=70;
        public final double srcH=37;

        BrickPink(double x, double y, double width, double height){
            super(x, y, width, height);
        }
        @Override
        void render(GraphicsContext gc) {
            gc.drawImage(img,this.srcX,this.srcY,this.srcW,this.srcH,
                    super.x, super.y, Config.brickWidth, Config.brickHeight);
        }
    }
}
