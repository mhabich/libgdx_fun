/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.martinandrewhabich.scene.DropScene;

/**
 * Application.java
 * 
 * @author Martin
 */
public class Application {

  private static final String SCREEN_TITLE = "Drop";
  private static final int SCREEN_WIDTH = 800;
  private static final int SCREEN_HEIGHT = 480;

  public static void main(String[] args) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = SCREEN_TITLE;
    cfg.width = SCREEN_WIDTH;
    cfg.height = SCREEN_HEIGHT;
    new LwjglApplication(new DropScene(), cfg);
    System.out.println("hello world.");
  }

}
