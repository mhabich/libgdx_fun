/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.martinandrewhabich.sprite.Sprite2D;

/**
 * Animation2D.java
 * 
 * @author Martin
 */
public class Animation2D extends Sprite2D {

  private float stateTime = 0;
  private Animation animation;

  public Animation2D(Animation animation, float x, float y, float width, float height) {
    super(animation.getKeyFrame(0), x, y, width, height);
    this.animation = animation;
  }

  public void update(float delta) {
    this.stateTime += delta;
  }

  @Override
  public TextureRegion getTextureRegion() {
    return animation.getKeyFrame(stateTime, true);
  }

}
