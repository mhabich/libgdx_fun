/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.font;

/**
 * FontObject.java
 * 
 * @author Martin
 */
public class FontObject {

  String text;
  float x;
  float y;

  public FontObject(String text, float x, float y) {
    this.text = text;
    this.x = x;
    this.y = y;
  }

  public String getText() {
    return text;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

}
