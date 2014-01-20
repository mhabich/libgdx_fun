/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich;

import com.martinandrewhabich.sound.AudioFactory;
import com.martinandrewhabich.texture.TextureFactory;

/**
 * DependencyBlob.java
 * 
 * "Dependency Injection" *cough cough*
 * 
 * @author Martin
 */
public class DependencyBlob {

  private static final TextureFactory textureFactory = new TextureFactory();
  private static final AudioFactory audioFactory = new AudioFactory();

  public static TextureFactory getTextureFactory() {
    return textureFactory;
  }

  public static AudioFactory getAudioFactory() {
    return audioFactory;
  }

}
