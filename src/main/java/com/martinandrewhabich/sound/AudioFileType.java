/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.sound;

import com.martinandrewhabich.file.FileType;

/**
 * SoundType.java
 * 
 * Represents the various libgdx-compatible audio file formats.
 * 
 * @author Martin
 */
public enum AudioFileType implements FileType {
  MP3("mp3"), WAV("wav");
  private String fileExtension;

  private AudioFileType(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public String getFileExtension() {
    return fileExtension;
  }
}
