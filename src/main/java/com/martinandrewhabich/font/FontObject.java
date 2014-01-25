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

  private String text;
  private float x;
  private float y;

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

  public void setX(float X) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

}
