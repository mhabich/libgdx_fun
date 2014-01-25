/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static com.martinandrewhabich.uglyglobals.Blobs.audioFactory;
import static com.martinandrewhabich.uglyglobals.Blobs.textureFactory;
import static game.drop.Globs.DEFAULT_IMAGE_HEIGHT;
import static game.drop.Globs.DEFAULT_IMAGE_WIDTH;
import static game.drop.Globs.SCREEN_HEIGHT;
import static game.drop.Globs.SCREEN_WIDTH;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.martinandrewhabich.rendering.RenderUtil;
import com.martinandrewhabich.screen.DesktopScreen;
import com.martinandrewhabich.sound.AudioFileType;
import com.martinandrewhabich.sprite.Sprite2D;
import com.martinandrewhabich.texture.TextureFileType;

/**
 * DropScene.java
 * 
 * @author Martin
 */
public class DropScreen extends DesktopScreen {

  private static final int BUCKET_Y_POS = 20;
  private static final int BUCKET_KEYBOARD_SPEED = 350;

  private static final int RAINDROP_INTERVAL_NANOSECONDS = 1000000000;
  private static final int RAINDROP_SPEED = 200;

  private Texture rainDropImage;
  private Sound dropCaughtSound;
  private Music rainBackgroundMusic;

  private Sprite2D background;
  private Sprite2D bucket;

  Pool<RainDrop> rainDropPool = new Pool<RainDrop>() {
    @Override
    protected RainDrop newObject() {
      RainDrop rainDrop = new RainDrop( //
          rainDropImage, //
          MathUtils.random(0, Gdx.graphics.getWidth() - RainDrop.WIDTH), SCREEN_HEIGHT, //
          DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
      return rainDrop;
    }
  };
  Array<RainDrop> activeRainDrops;

  long lastDropTime;

  public DropScreen(Game game) {
    super(game);
    rainDropImage = textureFactory.makeTexture("drop", TextureFileType.PNG);
    dropCaughtSound = audioFactory.makeSound("drop", AudioFileType.WAV);
    rainBackgroundMusic = audioFactory.makeMusic("rain", AudioFileType.MP3);
    rainBackgroundMusic.setLooping(true);
    background = new Sprite2D("splash_background", 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    bucket = new Sprite2D("bucket", SCREEN_WIDTH * 0.5F - DEFAULT_IMAGE_WIDTH * 0.5F, BUCKET_Y_POS,
        DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
    activeRainDrops = new Array<RainDrop>(RainDrop.class);
    spawnRaindropIfNecessary();
  }

  @Override
  public void show() {
    rainBackgroundMusic.play();
  }

  @Override
  public void update(float delta) {
    RenderUtil.renderSprites(camera, background, bucket);
    RenderUtil.renderSprites(camera, activeRainDrops.items);
    pollInput();
    makeSureBucketIsStillOnTheScreen();
    updateRaindropPositions();
    spawnRaindropIfNecessary();
  }

  private void pollInput() {
    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.setX(touchPos.x - DEFAULT_IMAGE_WIDTH / 2);
    } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      bucket.translate(-1 * BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime(), 0);
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      bucket.translate(BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime(), 0);
    }
  }

  private void makeSureBucketIsStillOnTheScreen() {
    // Keep the bucket inside the bounds of the screen.
    if (bucket.getX() < 0) {
      bucket.setX(0);
    } else if (bucket.getX() > Gdx.graphics.getWidth() - DEFAULT_IMAGE_WIDTH) {
      bucket.setX(Gdx.graphics.getWidth() - DEFAULT_IMAGE_WIDTH);
    }
  }

  private void spawnRaindropIfNecessary() {
    if (TimeUtils.nanoTime() - lastDropTime > RAINDROP_INTERVAL_NANOSECONDS) {
      RainDrop raindrop = rainDropPool.obtain();
      activeRainDrops.add(raindrop);
      lastDropTime = TimeUtils.nanoTime();
    }
  }

  private void updateRaindropPositions() {
    Iterator<RainDrop> iter = activeRainDrops.iterator();
    while (iter.hasNext()) {
      RainDrop raindrop = iter.next();
      raindrop.translate(0, -1 * RAINDROP_SPEED * Gdx.graphics.getDeltaTime());
      if (raindrop.getRectangle().overlaps(bucket.getRectangle())) {
        dropCaughtSound.play();
        rainDropPool.free(raindrop);
        iter.remove();
      } else if (raindrop.getY() + RainDrop.WIDTH < 0) {
        rainDropPool.free(raindrop);
        iter.remove();
      }
    }
  }

  @Override
  public void dispose() {
    // TODO - move this to a resource manager at some point (i.e. libgdx's AssetManager class)
    rainDropImage.dispose();
    dropCaughtSound.dispose();
    rainBackgroundMusic.dispose();
    bucket.getTexture().dispose();
  }

  @Override
  public void hide() {
    // Intentionally blank..
  }

}
