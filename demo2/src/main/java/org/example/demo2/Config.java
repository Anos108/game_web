package org.example.demo2;

import javafx.geometry.Rectangle2D;

public class Config {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final double gravityX = 0.4;
    public static final double gravityY = 0.4;

    public static boolean leftPressed = false;
    public static boolean rightPressed = false;

    public static final double ballX = 50;
    public static final double ballY = 300;

    public static final double paddleX = 0;
    public static final double paddleY = 700;

    public static final double brickX = 150;
    public static final double brickY = 100;
    public static final double brickWidth = (double) 64;
    public static final double brickHeight = (double) 32;

    public static final String IMAGE_PATH = "/asset/images/";
    public static final String SOUND_PATH = "/asset/sounds/";

    public static boolean interact(Rectangle2D a, Rectangle2D b) {
        return a.intersects(b);
    }
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



}
