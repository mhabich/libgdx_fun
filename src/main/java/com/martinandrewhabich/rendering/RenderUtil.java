/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.rendering;

import static com.martinandrewhabich.uglyglobals.Blobs.bitmapFont;
import static com.martinandrewhabich.uglyglobals.Blobs.spriteBatch;

import com.badlogic.gdx.graphics.Camera;
import com.martinandrewhabich.font.FontObject;
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
      if (sprite == null) {
        continue;
      }
      if (sprite.getTexture() != null) {
        renderTexture(sprite);
      } else {
        renderTextureRegion(sprite);
      }
    }

    spriteBatch.end();
  }

  private static void renderTexture(Sprite2D sprite) {
    spriteBatch.draw( //
        sprite.getTexture(), //
        sprite.getX(), sprite.getY(), //
        sprite.getWidth(), sprite.getHeight());
  }

  private static void renderTextureRegion(Sprite2D sprite) {
    spriteBatch.draw( //
        sprite.getTextureRegion(), //
        sprite.getX(), sprite.getY(), //
        sprite.getWidth(), sprite.getHeight());
  }

  public static void renderFonts(Camera camera, FontObject... fontObjects) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    for (FontObject font : fontObjects) {
      bitmapFont.draw(spriteBatch, font.getText(), font.getX(), font.getY());
    }
    spriteBatch.end();
  }

}
