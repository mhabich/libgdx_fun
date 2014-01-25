/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.uglyglobals;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.martinandrewhabich.sound.AudioFactory;
import com.martinandrewhabich.texture.TextureFactory;

/**
 * DependencyBlob.java
 * 
 * "Dependency Injection" *cough cough*
 * 
 * @author Martin
 */
public class Blobs {

  public static final TextureFactory textureFactory = new TextureFactory();
  public static final AudioFactory audioFactory = new AudioFactory();
  public static final SpriteBatch spriteBatch = new SpriteBatch();
  public static final BitmapFont bitmapFont = new BitmapFont();

}
