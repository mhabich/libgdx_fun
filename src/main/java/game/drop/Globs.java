/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Shameless[Glob]al[s].java
 * 
 * @author Martin
 */
public class Globs {

  public static final String SCREEN_TITLE = "Drop";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 480;

  public static final int DEFAULT_IMAGE_WIDTH = 64;
  public static final int DEFAULT_IMAGE_HEIGHT = 64;

  public static final int NUMBER_OF_DROPS_TO_WIN = 1;
  public static final float CREDIT_SPEED = 0.5F;

  public static final float SCALE = 1 / 80F;

  public static LwjglApplicationConfiguration LWJGL_APP_CONFIG = getLwjglAppConfig();

  private static LwjglApplicationConfiguration getLwjglAppConfig() {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = Globs.SCREEN_TITLE;
    cfg.width = Globs.SCREEN_WIDTH;
    cfg.height = Globs.SCREEN_HEIGHT;
    return cfg;
  }

}
