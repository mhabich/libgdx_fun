/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Texture;

/**
 * Application.java
 * 
 * Application bootstrap.
 * 
 * @author Martin
 */
public class Application {

  public static void main(String[] args) {
    // Don't force image heights/widths to be a power of two (POT).
    Texture.setEnforcePotImages(false);
    Game game = new DropGame();
    new LwjglApplication(game, Globs.LWJGL_APP_CONFIG);
  }

}