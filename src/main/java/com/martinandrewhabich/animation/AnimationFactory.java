/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * AnimationFactory.java
 * 
 * @author Martin
 */
public class AnimationFactory {

  public static Animation makeAnimation(Texture spriteSheet, int numberOfCols, int numberOfRows,
      float frameDuration) {
    TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / numberOfCols,
        spriteSheet.getHeight() / numberOfRows);
    TextureRegion[] walkFrames = new TextureRegion[numberOfCols * numberOfRows];
    int index = 0;
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        walkFrames[index++] = tmp[i][j];
      }
    }
    return new Animation(frameDuration, walkFrames);
  }

}
