/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import game.drop.Globs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.martinandrewhabich.font.FontObject;
import com.martinandrewhabich.rendering.RenderUtil;
import com.martinandrewhabich.screen.DesktopScreen;
import com.martinandrewhabich.sprite.Sprite2D;

/**
 * MainMenuScreen.java
 * 
 * @author Martin
 */
public class MainMenuScreen extends DesktopScreen {

  Sprite2D background;

  public MainMenuScreen(Game game) {
    super(game);
    background = new Sprite2D("splash_background", 0, 0, Globs.SCREEN_WIDTH, Globs.SCREEN_HEIGHT);
  }

  @Override
  public void show() {
    // INTENTIONALLY BLANK
  }

  @Override
  public void update(float delta) {
    RenderUtil.renderSprites(camera, background);
    RenderUtil.renderFonts(camera, //
        new FontObject("Welcome to Drop.", 100, 150), //
        new FontObject("Tap anywhere to begin...", 100, 100));
    pollInput();
  }

  private void pollInput() {
    if (Gdx.input.isTouched()) {
      game.setScreen(new DropScreen(game));
      dispose();
    }
  }

  @Override
  public void hide() {
    // Intentionally blank.
  }

  @Override
  public void dispose() {
    // Intentionally blank.
  }

}
