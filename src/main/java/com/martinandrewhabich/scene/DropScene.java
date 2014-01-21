/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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

  @Override
  public void create() {
    dropImage = textureFactory.makeTexture("drop", TextureFileType.PNG);
    bucketImage = textureFactory.makeTexture("bucket", TextureFileType.PNG);

    dropSound = audioFactory.makeSound("drop", AudioFileType.WAV);
    rainMusic = audioFactory.makeMusic("rain", AudioFileType.MP3);
    rainMusic.setLooping(true);
    rainMusic.play();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, GlobalConfig.SCREEN_WIDTH, GlobalConfig.SCREEN_HEIGHT);
    batch = new SpriteBatch();

    bucket = new Rectangle();
    bucket.x = GlobalConfig.SCREEN_WIDTH * 0.5F - GlobalConfig.BUCKET_WIDTH * 0.5F;
    bucket.y = GlobalConfig.BUCKET_Y_POS;
    bucket.width = GlobalConfig.BUCKET_WIDTH;
    bucket.height = GlobalConfig.BUCKET_HEIGHT;
  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub
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
    batch.end();

    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.x = touchPos.x - GlobalConfig.BUCKET_WIDTH / 2;
    }
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub
  }

}
