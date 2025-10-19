package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Bricks {

  public abstract static class Brick {
    private final double x, y;
    private final double width, height;
    private static String urlImg;
    private final Image img;

    Brick(double x, double y, double width, double height, String urlImg) {
      this.urlImg = urlImg;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      img =
          new Image(
              Objects.requireNonNull(Brick.class.getResourceAsStream(Config.IMAGE_PATH + urlImg)));
    }

    void render(GraphicsContext gc) {
      gc.drawImage(img, this.x, this.y, Config.brickWidth, Config.brickHeight);
    }
      public double getX(){
          return x;
      }
      public double getY(){
        return y;
      }
      public double getHeight(){
        return height;
      }
      public double getWidth(){
        return width;
      }

  }

  public static class BrickOrange extends Brick {
    BrickOrange(double x, double y, double width, double height) {
      super(x, y, width, height, "brickOrange.png");
    }
  }

  public static class BrickRed extends Brick {
    BrickRed(double x, double y, double width, double height) {

      super(x, y, width, height, "brickRed.png");
    }
  }

  public static class BrickGreen extends Brick {
    BrickGreen(double x, double y, double width, double height) {
      super(x, y, width, height, "brickGreen.png");
    }
  }

  public static class BrickPurple extends Brick {
    BrickPurple(double x, double y, double width, double height) {
      super(x, y, width, height, "brickPurple.png");
    }
  }
}
