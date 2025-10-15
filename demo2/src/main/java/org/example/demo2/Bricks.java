package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Bricks {

  public abstract static class Brick {
    private final double x, y;
    private final double width, height;

    private static final double SPRITE_WIDTH = 70;
    private static final double SPRITE_HEIGHT = 37;

    static final Image img;

    static {
      try {
        img =
            new Image(
                Objects.requireNonNull(
                    Brick.class.getResourceAsStream(Config.IMAGE_PATH + "brick.png")));
      } catch (NullPointerException e) {
        System.err.println(
            "Không tìm thấy file ảnh brick.png. Kiểm tra lại đường dẫn trong Config.java!");
        throw new RuntimeException(e);
      }
    }

    Brick(double x, double y, double width, double height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }


    protected void render(GraphicsContext gc, double srcX, double srcY) {
      gc.drawImage(
          img,
          srcX,
          srcY,
          SPRITE_WIDTH,
          SPRITE_HEIGHT,
          this.x,
          this.y,
          Config.brickWidth,
          Config.brickHeight);
    }


    public abstract void render(GraphicsContext gc);
  }


  public static class BrickOrange extends Brick {
    BrickOrange(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 0, 0);
    }
  }

  public static class BrickRed extends Brick {
    BrickRed(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 70, 0);
    }
  }

  public static class BrickGreen extends Brick {
    BrickGreen(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 140, 0);
    }
  }

  public static class BrickBlue extends Brick {
    BrickBlue(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 210, 0);
    }
  }

  public static class BrickPink extends Brick {
    BrickPink(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 0, 37);
    }
  }

  public static class BrickLightBlue extends Brick {
    BrickLightBlue(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 70, 37);
    }
  }

  public static class BrickYellow extends Brick {
    BrickYellow(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 140, 37);
    }
  }

  public static class BrickPurple extends Brick {
    BrickPurple(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
      render(gc, 210, 37);
    }
  }
}
