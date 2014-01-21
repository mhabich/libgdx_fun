/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * Application.java
 * 
 * Application bootstrap.
 * 
 * @author Martin
 */
public class Application {

  public static void main(String[] args) {
    Game game = new DropGame();
    new LwjglApplication(game, Globs.getLwjglAppConfig());
  }

}