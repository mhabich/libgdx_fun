/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static game.drop.Blobs.audioFactory;
import static game.drop.Blobs.spriteBatch;
import static game.drop.Blobs.textureFactory;
import static game.drop.Globs.SCREEN_HEIGHT;
import static game.drop.Globs.SCREEN_WIDTH;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.martinandrewhabich.screen.DesktopScreen;
import com.martinandrewhabich.sound.AudioFileType;
import com.martinandrewhabich.sprite.Sprite2D;
import com.martinandrewhabich.texture.TextureFileType;

/**
 * BucketScene.java
 * 
 * @author Martin
 */
public class DropScreen extends DesktopScreen {

  private static final int IMAGE_WIDTH = 64;
  private static final int IMAGE_HEIGHT = 64;

  private static final int BUCKET_Y_POS = 20;
  private static final int BUCKET_KEYBOARD_SPEED = 350;

  private static int RAINDROP_INTERVAL_NANOSECONDS = 1000000000;
  private static int RAINDROP_SPEED = 200;

  private Texture menuBorderImage;
  private Texture dropImage;
  private Texture bucketImage;
  private Sound dropSound;
  private Music rainMusic;

  private Sprite2D menuBorder;
  private Sprite2D bucket;
  Array<Sprite2D> raindrops;

  OrthographicCamera camera;

  long lastDropTime;

  public DropScreen(Game game) {
    super(game);

    menuBorderImage = textureFactory.makeTexture("menu_border", TextureFileType.PNG);
    menuBorder = new Sprite2D(menuBorderImage, 0, SCREEN_HEIGHT - 208, 484, 208);

    dropImage = textureFactory.makeTexture("drop", TextureFileType.PNG);

    bucketImage = textureFactory.makeTexture("bucket", TextureFileType.PNG);
    bucket = new Sprite2D(bucketImage, SCREEN_WIDTH * 0.5F - IMAGE_WIDTH * 0.5F, BUCKET_Y_POS,
        IMAGE_WIDTH, IMAGE_HEIGHT);

    dropSound = audioFactory.makeSound("drop", AudioFileType.WAV);
    rainMusic = audioFactory.makeMusic("rain", AudioFileType.MP3);
    rainMusic.setLooping(true);

    camera = new OrthographicCamera();
    camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

    raindrops = new Array<Sprite2D>(Sprite2D.class);
    spawnRaindrop();
  }

  @Override
  public void show() {
    rainMusic.play();
  }

  @Override
  public void resize(int width, int height) {
    // Intentionally blank...
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0.1F, 0.2F, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    // Cameras use a mathematical entity called matrix that is responsible for setting up the
    // coordinate system for rendering. These matrices need to be recomputed every time we change a
    // property of the camera, like its position. We don't do this in our simple example, but it is
    // generally a good practice to update the camera once per frame.
    camera.update();

    renderSprites(menuBorder, bucket);
    renderSprites(raindrops.items);

    // Poll for mouse or keyboard input.
    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.setX(touchPos.x - IMAGE_WIDTH / 2);
    } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      bucket.translate(-1 * BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime(), 0);
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      bucket.translate(BUCKET_KEYBOARD_SPEED * Gdx.graphics.getDeltaTime(), 0);
    }

    // Keep the bucket inside the bounds of the screen.
    if (bucket.getX() < 0) {
      bucket.setX(0);
    } else if (bucket.getX() > getImageRightBound()) {
      bucket.setX(getImageRightBound());
    }

    if (TimeUtils.nanoTime() - lastDropTime > RAINDROP_INTERVAL_NANOSECONDS) {
      spawnRaindrop();
    }

    Iterator<Sprite2D> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Sprite2D raindrop = iter.next();
      raindrop.translate(0, -1 * RAINDROP_SPEED * Gdx.graphics.getDeltaTime());
      if (raindrop.getRectangle().overlaps(bucket.getRectangle())) {
        dropSound.play();
        iter.remove();
      } else if (raindrop.getY() + IMAGE_HEIGHT < 0) {
        iter.remove();
      }
    }
  }

  private void renderSprites(Sprite2D... sprites) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    for (Sprite2D sprite : sprites) {
      if (sprite != null) {
        spriteBatch.draw(sprite.getTexture(), sprite.getX(), sprite.getY());
      }
    }
    spriteBatch.end();
  }

  private void spawnRaindrop() {
    Sprite2D raindrop = new Sprite2D(dropImage, MathUtils.random(0, getImageRightBound()),
        SCREEN_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);
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
    menuBorderImage.dispose();
    dropImage.dispose();
    bucketImage.dispose();
    dropSound.dispose();
    rainMusic.dispose();
  }

  @Override
  public void hide() {
    // Intentionally blank..
  }

}
