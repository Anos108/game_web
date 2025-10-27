package org.example.demo2;

import javafx.geometry.Rectangle2D;

public class Wall {
    public static int check_wall(Rectangle2D a){

        if(Config.interact(a,new Rectangle2D(3,3,799,3))){
            return 1;
        } //tuong tren
        if(Config.interact(a,new Rectangle2D(3,3,3,799))){
            return 2;
        } //tuong trai
        if(Config.interact(a,new Rectangle2D(797,3,3,799))){
            return 3;
        }//tuong phai
        if(a.getMaxY() >= Config.HEIGHT){
            return 4;  // đáy
        }
        else{
            return 0;
        }
    }

}
