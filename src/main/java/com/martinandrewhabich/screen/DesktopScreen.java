/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * DesktopScene.java
 * 
 * Overrides several Android-specific methods such that base classes need not.
 * 
 * @author Martin
 */
public abstract class DesktopScreen implements Screen {

  protected final Game game;

  protected DesktopScreen(Game game) {
    this.game = game;
  }

  // Declare methods in the order I like them (i.e. so that Eclipse auto-generates the methods in
  // this order).
  @Override
  public abstract void show();

  @Override
  public abstract void render(float delta);

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
