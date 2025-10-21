package org.example.demo2;

import javafx.geometry.Rectangle2D;

public class Wall {
    public static int check_wall(Rectangle2D a){

        if(Config.interact(a,new Rectangle2D(3,3,799,3))){
            return 1;
        }
        if(Config.interact(a,new Rectangle2D(3,3,3,799))){
            return 2;
        }
        if(Config.interact(a,new Rectangle2D(797,3,3,799))){
            return 3;
        }
        else{
            return 0;
        }
    }

}
