/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.martinandrewhabich.file.FileUtil;

/**
 * SoundFactory.java
 * 
 * Factory methods and configuration for Sound and Music objects.
 * 
 * @author Martin
 */
public class AudioFactory {

  public Sound makeSound(String fileName, AudioFileType audioFileType) {
    FileHandle fileHandle = FileUtil.getFileHandle(fileName, audioFileType);
    return Gdx.audio.newSound(fileHandle);
  }

  public Music makeMusic(String fileName, AudioFileType audioFileType) {
    FileHandle fileHandle = FileUtil.getFileHandle(fileName, audioFileType);
    return Gdx.audio.newMusic(fileHandle);
  }

}
