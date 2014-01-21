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
 * Application bootstrap.
 * 
 * @author Martin
 */
public class Application {

  public static void main(String[] args) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = GlobalConfig.SCREEN_TITLE;
    cfg.width = GlobalConfig.SCREEN_WIDTH;
    cfg.height = GlobalConfig.SCREEN_HEIGHT;
    new LwjglApplication(new DropScene(), cfg);
  }

}