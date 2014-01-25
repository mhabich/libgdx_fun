/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.screen;

import static game.drop.Globs.SCREEN_HEIGHT;
import static game.drop.Globs.SCREEN_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * DesktopScene.java
 * 
 * Overrides several Android-specific methods such that base classes need not.
 * 
 * @author Martin
 */
public abstract class DesktopScreen implements Screen {

  protected final Game game;

  protected final OrthographicCamera camera;

  protected DesktopScreen(Game game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  // Declare methods in the order I like them (i.e. so that Eclipse auto-generates the methods in
  // this order).
  @Override
  public abstract void show();

  @Override
  public final void render(float delta) {
    clearBuffer();
    // Cameras use a mathematical entity called matrix that is responsible for setting up the
    // coordinate system for rendering. These matrices need to be recomputed every time we change a
    // property of the camera, like its position. We don't do this in our simple example, but it is
    // generally a good practice to update the camera once per frame.
    camera.update();
    update(delta);
  }

  private void clearBuffer() {
    Gdx.gl.glClearColor(0, 0.1F, 0.2F, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
  }

  public abstract void update(float delta);

  @Override
  public abstract void resize(int width, int height);

  @Override
  public abstract void hide();

  @Override
  public abstract void dispose();

  @Override
  public final void pause() {
    // Ignoring Android-specific event handler
  }

  @Override
  public final void resume() {
    // Ignoring Android-specific event handler
  }

}
