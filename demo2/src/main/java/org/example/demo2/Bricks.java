package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javafx.geometry.Rectangle2D;
import java.util.Objects;

import static java.lang.Math.clamp;

public class Bricks {

    public abstract static class Brick {
        private final double x, y;
        private final double width, height;
        private static String urlImg;
        private int score;


        private final Image img;
        private boolean destroyed=false;
        private int health;
        private final int maxHealth;
        private double alpha =1.0;
        private double glowTime = 0; // thời gian còn lại của hiệu ứng glow
        private final DropShadow glow = new DropShadow();
        private boolean hasPowerUp = false;
        final double rand = Math.random();



        Brick(double x, double y, double width, double height, String urlImg,int maxHealth) {
            this.urlImg = urlImg;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.maxHealth = maxHealth;
            img =
                    new Image(
                            Objects.requireNonNull(
                                    Brick.class.getResourceAsStream(Config.IMAGE_PATH + urlImg)));
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setScore(int score) {
            this.score = score;
        }
        public int getHealth() {
            return health;
        }

        public int getScore() {
            return score;
        }

        public void setHasPowerUp() {
            this.hasPowerUp = true;
        }
        public boolean getHasPowerUp() {
            return this.hasPowerUp;
        }

        public void healthDown() {
            health--;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public int setDestroyed(boolean destroyed) {
            if(health==0){
                this.destroyed = destroyed;
                return score;
            }
            alpha = clamp((double) health / (double) maxHealth, 0.0, 1.0);
            glowTime = 0.3; // sáng trong 0.3 giây
            glow.setColor(Color.web("#FFEA00")); // màu vàng sáng
            glow.setRadius(20);
            return 0;
        }

        void render(GraphicsContext gc) {
            gc.save();
            gc.setGlobalAlpha(alpha);
            gc.drawImage(img, this.x, this.y, Config.BRICK_WIDTH, Config.BRICK_HEIGHT);
            gc.restore();
            gc.save();
            if (glowTime > 0) gc.setEffect(glow);
            gc.setGlobalAlpha(alpha);
            gc.drawImage(img, x, y, Config.BRICK_WIDTH, Config.BRICK_HEIGHT);
            gc.restore();
        }
        void Update() {
            double dt = 0.016;
            if (glowTime > 0) {
                glowTime -= dt;
                double intensity = Math.max(0, glowTime / 0.3);
                glow.setRadius(20 * intensity);  // giảm dần bán kính sáng
                glow.setColor(Color.color(1.0, 0.9, 0.3, intensity)); // giảm dần độ sáng
            }
        }

        public Rectangle2D getBounds(){
            return new Rectangle2D(x, y, Config.BRICK_WIDTH, Config.BRICK_HEIGHT);
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }

    public static class BrickOrange extends Brick {


        BrickOrange(double x, double y, double width, double height) {
            super(x, y, width, height,"brickOrange.png",1);
            super.setScore(100);
            if (super.rand > 0.95) setHasPowerUp();
            setHealth(1);
        }


    }

    public static class BrickRed extends Brick {
        BrickRed(double x, double y, double width, double height) {

            super(x, y, width, height,"brickRed.png",2);
            super.setScore(150);
            if (super.rand > 0.80) setHasPowerUp();
            setHealth(2);
        }
    }

    public static class BrickGreen extends Brick {
        BrickGreen(double x, double y, double width, double height) {
            super(x, y, width, height,"brickGreen.png",3);
            super.setScore(300);
            if (super.rand > 0.60) setHasPowerUp();
            setHealth(4);
        }
    }

    public static class BrickPurple extends Brick {
        BrickPurple(double x, double y, double width, double height) {
            super(x, y, width, height,"brickPurple.png",6);
            super.setScore(600);
            if (super.rand > 0.40) setHasPowerUp();
            setHealth(6);
        }
    }




}