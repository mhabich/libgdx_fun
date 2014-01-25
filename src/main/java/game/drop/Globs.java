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
  public static final int SCREEN_WIDTH = 1024;
  public static final int SCREEN_HEIGHT = 800;

  public static LwjglApplicationConfiguration LWJGL_APP_CONFIG = getLwjglAppConfig();

  private static LwjglApplicationConfiguration getLwjglAppConfig() {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = Globs.SCREEN_TITLE;
    cfg.width = Globs.SCREEN_WIDTH;
    cfg.height = Globs.SCREEN_HEIGHT;
    return cfg;
  }

}
