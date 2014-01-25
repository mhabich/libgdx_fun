/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.rendering;

import static game.drop.Blobs.spriteBatch;

import com.badlogic.gdx.graphics.Camera;
import com.martinandrewhabich.sprite.Sprite2D;

/**
 * RenderUtil.java
 * 
 * @author Martin
 */
public class RenderUtil {

  public static void renderSprites(Camera camera, Sprite2D... sprites) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    for (Sprite2D sprite : sprites) {
      if (sprite != null) {
        spriteBatch.draw( //
            sprite.getTexture(), //
            sprite.getX(), sprite.getY(), //
            sprite.getHeight(), sprite.getWidth());
      }
    }
    spriteBatch.end();
  }

}
