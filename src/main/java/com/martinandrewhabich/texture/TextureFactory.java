/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.texture;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.martinandrewhabich.file.FileUtil;

/**
 * TextureFactory.java
 * 
 * Factory methods and configuration for Texture objects.
 * 
 * @author Martin
 */
public class TextureFactory {

  public Texture makeTexture(String fileName, TextureFileType textureFileType) {
    FileHandle fileHandle = FileUtil.getFileHandle(fileName, textureFileType);
    return new Texture(fileHandle);
  }

}
