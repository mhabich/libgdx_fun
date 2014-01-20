/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.scene;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
    camera.setToOrtho(false, GlobalConfig.SCREEN_WIDTH,
        GlobalConfig.SCREEN_HEIGHT);
    batch = new SpriteBatch();

    bucket = new Rectangle();
    bucket.x = GlobalConfig.SCREEN_WIDTH * 0.5F - GlobalConfig.BUCKET_WIDTH
        * 0.5F;
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
    // TODO Auto-generated method stub
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub
  }

}
