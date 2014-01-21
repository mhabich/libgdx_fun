/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * ShamelessGlobalConfig.java
 * 
 * @author Martin
 */
public class Globs {

  public static final String SCREEN_TITLE = "Drop";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 480;

  public static final int IMAGE_WIDTH = 64;
  public static final int IMAGE_HEIGHT = 64;
  public static final int BUCKET_Y_POS = 20;

  public static final int BUCKET_KEYBOARD_SPEED = 350;

  public static int RAINDROP_INTERVAL_NANOSECONDS = 1000000000;
  public static int RAINDROP_SPEED = 200;

  public static LwjglApplicationConfiguration LWJGL_APP_CONFIG = getLwjglAppConfig();

  private static LwjglApplicationConfiguration getLwjglAppConfig() {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = Globs.SCREEN_TITLE;
    cfg.width = Globs.SCREEN_WIDTH;
    cfg.height = Globs.SCREEN_HEIGHT;
    return cfg;
  }

}
