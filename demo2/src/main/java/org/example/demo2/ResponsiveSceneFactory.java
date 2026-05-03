package org.example.demo2;

import java.util.Objects;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public final class ResponsiveSceneFactory {
  /** Scale applied to menu / game content so it fits smaller windows. */
  public static final double CONTENT_SCALE = 0.9;

  /** Full-bleed image behind scaled UI (removes letterbox bars when window is not square). */
  public enum SceneBackdrop {
    MENU,
    GAME_OVER
  }

  private ResponsiveSceneFactory() {}

  public static Scene create(Parent content) {
    return create(content, SceneBackdrop.MENU);
  }

  public static Scene create(Parent content, SceneBackdrop backdrop) {
    content.resize(Config.WIDTH, Config.HEIGHT);
    if (content instanceof Region region) {
      region.setPrefSize(Config.WIDTH, Config.HEIGHT);
      region.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
      region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }
    content.setScaleX(CONTENT_SCALE);
    content.setScaleY(CONTENT_SCALE);

    Region fill = new Region();
    fill.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    switch (backdrop) {
      case MENU -> fill.getStyleClass().add("menu-scene-backdrop");
      case GAME_OVER -> fill.getStyleClass().add("game-over-scene-backdrop");
    }

    StackPane root = new StackPane(fill, content);
    root.setAlignment(Pos.CENTER);

    Scene scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
    scene.setFill(Color.BLACK);
    scene
        .getStylesheets()
        .add(
            Objects.requireNonNull(
                    ResponsiveSceneFactory.class.getResource("/org/example/demo2/styles.css"))
                .toExternalForm());
    return scene;
  }
}
