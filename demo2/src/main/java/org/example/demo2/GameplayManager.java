package org.example.demo2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class GameplayManager {
    private static final int INITIAL_LIFE = 3;
    private static int life = INITIAL_LIFE;
    private static final Image LIFE_IMG = new Image(
            Objects.requireNonNull(
                    GameplayManager.class.getResourceAsStream(Config.IMAGE_PATH + "mang.png")
            )
    );
    private static int score = 0;
    private static boolean checkLife=false;

    public static void setCheckLife(boolean checkLife) {
        GameplayManager.checkLife = checkLife;
    }

    public static boolean isCheckLife() {
        return checkLife;
    }

    public static void equalLife(){
        if(checkLife){
        life--;
        checkLife=false;
        }
    }

    public static void setCheckLife() {
        checkLife = !checkLife;
    }

    public static int getLife(){
        return life;
    }
    public static void resetLife(){
        life = INITIAL_LIFE;
    }
    public static int plusLife(){
        return life++;
    }
    public static void plusScore() {
        GameplayManager.score++;
    }

    public static void resetScore() {
        GameplayManager.score = 0;
    }

    public static void resetState() {
        life = INITIAL_LIFE;
        score = 0;
        checkLife = false;
    }

    public static void renderLives(GraphicsContext gc) {
        double x=0; double y=0;
        double size=64; double gap=8;
        for (int i = 0; i < life; i++) {
            gc.drawImage(LIFE_IMG, x + i * (size + gap), y, size, size);
        }
    }

    public static int getScore() {
        return score;
    }
}
