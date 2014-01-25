/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.texture;

import com.martinandrewhabich.file.FileType;

/**
 * TextureFileType.java
 * 
 * Represents the various libgdx-compatible image file formats.
 * 
 * @author Martin
 */
public enum TextureFileType implements FileType {

  PNG("png");

  private String fileExtension;

  private TextureFileType(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public String getFileExtension() {
    return fileExtension;
  }

  public static TextureFileType getDefaultType() {
    return PNG;
  }

}
