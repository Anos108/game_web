package org.example.demo2;

import javafx.geometry.Rectangle2D;

public class Wall {
    public static int check_wall(Rectangle2D a){

        if(Config.getInstance().interact(a,new Rectangle2D(3,3,799,3))){
            return 1;
        } //tuong tren
        if(Config.getInstance().interact(a,new Rectangle2D(3,3,3,799))){
            return 2;
        } //tuong trai
        if(Config.getInstance().interact(a,new Rectangle2D(797,3,3,799))){
            return 3;
        }//tuong phai
        if(a.getMaxY() >= Config.HEIGHT){
            return 4;  // đáy
        }
        else{
            return 0;
        }
    }
    public static boolean wallBall(Ball ball){
        int w = Wall.check_wall(ball.getBounds());
        if (w == 1) {
            ball.setInteractY(); // top
        } else if (w == 2 || w == 3) {
            ball.setInteractX(); // left/right
        } else if (w == 4) { // bottom -> đánh dấu mất bóng
            ball.setDead(true); // cờ để remove ở ngoài
            return true;
        }
        return false;
    }

}
