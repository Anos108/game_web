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
    public static final double paddleWidth = 906/7;
    public static final double paddleHeight = 484/7;


    public static final double brickX = 150;
    public static final double brickY = 100;
    public static final double brickWidth = (double) 64;
    public static final double brickHeight = (double) 32;

    public static final String IMAGE_PATH = "/asset/images/";
    public static final String SOUND_PATH = "/asset/sounds/";

    public static boolean interact(Rectangle2D a, Rectangle2D b) {
        return a.intersects(b);
    }




}
