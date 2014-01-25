/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static game.drop.Globs.SCREEN_HEIGHT;
import game.drop.Globs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.martinandrewhabich.sprite.Sprite2D;

/**
 * Drop.java
 * 
 * @author Martin
 */
public class RainDrop extends Sprite2D implements Poolable {

  public static final int WIDTH = Globs.DEFAULT_IMAGE_WIDTH;
  public static final int HEIGHT = Globs.DEFAULT_IMAGE_HEIGHT;

  public RainDrop(Texture texture, float x, float y, float width, float height) {
    super(texture, x, y, width, height);
  }

  /**
   * Callback method when the object is freed. It is automatically called by Pool.free() Must reset
   * every meaningful field of this bullet.
   */
  @Override
  public void reset() {
    this.setX(MathUtils.random(0, Gdx.graphics.getWidth() - WIDTH));
    this.setY(SCREEN_HEIGHT);
  }
}
