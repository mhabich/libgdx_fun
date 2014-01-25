/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * FileUtil.java
 * 
 * @author Martin
 */
public class FileUtil {

  public static String getFileNamePlusExtension(String fileName, FileType fileType) {
    return fileName + "." + fileType.getFileExtension();
  }

  public static FileHandle getFileHandle(String fileName, FileType fileType) {
    String fullFileName = FileUtil.getFileNamePlusExtension(fileName, fileType);
    return Gdx.files.internal(fullFileName);
  }

}
