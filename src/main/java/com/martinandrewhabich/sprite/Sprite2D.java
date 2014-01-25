/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Sprite2D.java
 * 
 * Class representing a "renderable" object. Includes both an image (Texture) and 2D positional info
 * (x, y, height, width).
 * 
 * @author Martin
 */
public class Sprite2D {

  private Texture texture;
  private Rectangle rectangle;

  public Sprite2D(Texture texture, float x, float y, float width, float height) {
    this.texture = texture;
    this.rectangle = new Rectangle(x, y, width, height);
  }

  public float getX() {
    return rectangle.x;
  }

  public void setX(float x) {
    rectangle.x = x;
  }

  public float getY() {
    return rectangle.y;
  }

  public void setY(float y) {
    rectangle.y = y;
  }

  public void translate(float xTrans, float yTrans) {
    rectangle.x += xTrans;
    rectangle.y += yTrans;
  }

  public Texture getTexture() {
    return texture;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

}
