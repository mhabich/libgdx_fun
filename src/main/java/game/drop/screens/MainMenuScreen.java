/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static game.drop.Blobs.bitmapFont;
import static game.drop.Blobs.spriteBatch;
import game.drop.Globs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.martinandrewhabich.screen.DesktopScreen;

/**
 * MainMenuScreen.java
 * 
 * @author Martin
 */
public class MainMenuScreen extends DesktopScreen {

  OrthographicCamera camera;

  public MainMenuScreen(Game game) {
    super(game);
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Globs.SCREEN_WIDTH, Globs.SCREEN_HEIGHT);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0.1F, 0.2F, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    camera.update();
    spriteBatch.setProjectionMatrix(camera.combined);

    spriteBatch.begin();
    bitmapFont.draw(spriteBatch, "Welcome to Drop!!! ", 100, 150);
    bitmapFont.draw(spriteBatch, "Tap anywhere to begin!", 100, 100);
    spriteBatch.end();

    if (Gdx.input.isTouched()) {
      game.setScreen(new DropScreen(game));
      dispose();
    }
  }

  @Override
  public void resize(int width, int height) {
    // Intentionally blank.
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
