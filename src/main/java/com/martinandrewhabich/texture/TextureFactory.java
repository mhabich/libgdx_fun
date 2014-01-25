/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.texture;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.martinandrewhabich.file.FileUtil;

/**
 * TextureFactory.java
 * 
 * Factory methods and configuration for Texture objects.
 * 
 * @author Martin
 */
public class TextureFactory implements Disposable {

  private static AssetManager assetManager = new AssetManager();

  public Texture makeTexture(String fileName, TextureFileType textureFileType) {
    String fileNamePlusExtension = FileUtil.getFileNamePlusExtension(fileName, textureFileType);
    if (!assetManager.isLoaded(fileNamePlusExtension)) {
      assetManager.load(fileNamePlusExtension, Texture.class);
      while (!assetManager.update()) {
        // Still loading.
      }
    }
    return assetManager.get(fileNamePlusExtension);
  }

  @Override
  public void dispose() {
    // Dispose of all textures without disposing of the AssetManager itself.
    assetManager.clear();
  }

}
