/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop;

import game.drop.screens.MainMenuScreen;

import com.badlogic.gdx.Game;

/**
 * DropGame.java
 * 
 * @author Martin
 */
public class DropGame extends Game {

  public void create() {
    this.setScreen(new MainMenuScreen(this));
  }

  public void render() {
    super.render(); // important!
  }

  public void dispose() {
    Blobs.spriteBatch.dispose();
    Blobs.bitmapFont.dispose();
  }

}
