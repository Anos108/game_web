package org.example.demo2;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;

public final class ResponsiveSceneFactory {
  /** Scale applied to menu / game content so it fits smaller windows. */
  public static final double CONTENT_SCALE = 0.9;

  private ResponsiveSceneFactory() {}

  public static Scene create(Parent content) {
    content.resize(Config.WIDTH, Config.HEIGHT);
    if (content instanceof Region region) {
      region.setPrefSize(Config.WIDTH, Config.HEIGHT);
      region.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
      region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }
    content.setScaleX(CONTENT_SCALE);
    content.setScaleY(CONTENT_SCALE);

    StackPane root = new StackPane(content);
    root.setAlignment(Pos.CENTER);

    return new Scene(root, Config.WIDTH, Config.HEIGHT);
  }
}
