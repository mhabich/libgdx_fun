/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.scene;

import com.badlogic.gdx.ApplicationListener;

/**
 * DesktopScene.java
 * 
 * Overrides several Android-specific methods such that base classes need not.
 * 
 * @author Martin
 */
abstract class DesktopScene implements ApplicationListener {

  @Override
  public final void pause() {
    // Ignoring Android-specific event handler
  }

  @Override
  public final void resume() {
    // Ignoring Android-specific event handler
  }

}
