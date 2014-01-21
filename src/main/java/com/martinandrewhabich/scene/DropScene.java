/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.scene;

import static com.martinandrewhabich.GlobalConfig.BUCKET_KEYBOARD_SPEED;
import static com.martinandrewhabich.GlobalConfig.BUCKET_Y_POS;
import static com.martinandrewhabich.GlobalConfig.IMAGE_HEIGHT;
import static com.martinandrewhabich.GlobalConfig.IMAGE_WIDTH;
import static com.martinandrewhabich.GlobalConfig.SCREEN_HEIGHT;
import static com.martinandrewhabich.GlobalConfig.SCREEN_WIDTH;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.martinandrewhabich.DependencyBlob;
import com.martinandrewhabich.GlobalConfig;
import com.martinandrewhabich.sound.AudioFactory;
import com.martinandrewhabich.sound.AudioFileType;
import com.martinandrewhabich.texture.TextureFactory;
import com.martinandrewhabich.texture.TextureFileType;

/**
 * BucketScene.java
 * 
 * @author Martin
 */
public class DropScene extends DesktopScene {

  private TextureFactory textureFactory = DependencyBlob.getTextureFactory();
  private AudioFactory audioFactory = DependencyBlob.getAudioFactory();

  private Texture dropImage;
  private Texture bucketImage;
  private Sound dropSound;
  private Music rainMusic;

  OrthographicCamera camera;
  SpriteBatch batch;

  Rectangle bucket;
  Array<Rectangle> raindrops;

  long lastDropTime;

  @Override
  public void create() {
    dropImage = textureFactory.makeTexture("drop", TextureFileType.PNG);
    bucketImage = textureFactory.makeTexture("bucket", TextureFileType.PNG);

    dropSound = audioFactory.makeSound("drop", AudioFileType.WAV);
    rainMusic = audioFactory.makeMusic("rain", AudioFileType.MP3);
    rainMusic.setLooping(true);
    rainMusic.play();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    batch = new SpriteBatch();

    bucket = new Rectangle();
    bucket.x = SCREEN_WIDTH * 0.5F - IMAGE_WIDTH * 0.5F;
    bucket.y = BUCKET_Y_POS;
    bucket.width = IMAGE_WIDTH;
    bucket.height = IMAGE_HEIGHT;

    raindrops = new Array<Rectangle>();
    spawnRaindrop();
  }

  @Override
  public void resize(int width, int height) {
    // INTENTIONALLY BLANK
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0.1F, 0.2F, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    // Cameras use a mathematical entity called matrix that is responsible for setting up the
    // coordinate system for rendering. These matrices need to be recomputed every time we change a
    // property of the camera, like its position. We don't do this in our simple example, but it is
    // generally a good practice to update the camera once per frame.
    camera.update();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(bucketImage, bucket.x, bucket.y);
    for (Rectangle raindrop : raindrops) {
      batch.draw(dropImage, raindrop.x, raindrop.y);
    }
    batch.end();

    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.x = touchPos.x - IMAGE_WIDTH / 2;
    }

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      bucket.x -= BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime();
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      bucket.x += BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime();
    }

    if (bucket.x < 0) {
      bucket.x = 0;
    } else if (bucket.x > getImageRightBound()) {
      bucket.x = getImageRightBound();
    }

    if (TimeUtils.nanoTime() - lastDropTime > GlobalConfig.RAINDROP_INTERVAL_NANOSECONDS) {
      spawnRaindrop();
    }

    Iterator<Rectangle> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Rectangle raindrop = iter.next();
      raindrop.y -= GlobalConfig.RAINDROP_SPEED * Gdx.graphics.getDeltaTime();
      if (raindrop.overlaps(bucket)) {
        dropSound.play();
        iter.remove();
      } else if (raindrop.y + IMAGE_HEIGHT < 0) {
        iter.remove();
      }
    }
  }

  private void spawnRaindrop() {
    Rectangle raindrop = new Rectangle();
    raindrop.x = MathUtils.random(0, getImageRightBound());
    raindrop.y = SCREEN_HEIGHT;
    raindrop.width = IMAGE_WIDTH;
    raindrop.height = IMAGE_HEIGHT;
    raindrops.add(raindrop);
    lastDropTime = TimeUtils.nanoTime();
  }

  /**
   * The max x-position that an image can reside at without going off the right edge of the
   * projection matrix.
   */
  private static int getImageRightBound() {
    return SCREEN_WIDTH - IMAGE_WIDTH;
  }

  @Override
  public void dispose() {
    dropImage.dispose();
    bucketImage.dispose();
    dropSound.dispose();
    rainMusic.dispose();
    batch.dispose();
  }

}
