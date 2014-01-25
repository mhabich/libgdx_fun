/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.sprite;

import static com.martinandrewhabich.uglyglobals.Blobs.textureFactory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.martinandrewhabich.texture.TextureFileType;

/**
 * Sprite2D.java
 * 
 * Class representing a "renderable" object. Includes both an image (Texture) and 2D positional info
 * (x, y, height, width). 2D info is stored using the traditional libgdx Rectangle class.
 * 
 * @author Martin
 */
public class Sprite2D {

  private Texture texture;
  private TextureRegion textureRegion;
  private Rectangle rectangle;

  public Sprite2D(String textureName, float x, float y, float width, float height) {
    this(textureFactory.makeTexture(textureName, TextureFileType.getDefaultType()), x, y, width,
        height);
  }

  public Sprite2D(Texture texture, float x, float y, float width, float height) {
    this.texture = texture;
    this.rectangle = new Rectangle(x, y, width, height);
  }

  public Sprite2D(TextureRegion textureRegion, float x, float y, float width, float height) {
    this.textureRegion = textureRegion;
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

  public float getWidth() {
    return rectangle.width;
  }

  public void setWidth(float width) {
    rectangle.width = width;
  }

  public float getHeight() {
    return rectangle.height;
  }

  public void setHeight(float height) {
    rectangle.height = height;
  }

  public void translate(float xTrans, float yTrans) {
    rectangle.x += xTrans;
    rectangle.y += yTrans;
  }

  public Texture getTexture() {
    return texture;
  }

  public TextureRegion getTextureRegion() {
    return textureRegion;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

}
