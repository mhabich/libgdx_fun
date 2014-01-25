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
import game.drop.Globs;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.martinandrewhabich.font.FontObject;
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

  private int totalDropsSpawned = 0;
  private int totalDropsCaught = 0;

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
    spawnRainDropIfNecessary();
  }

  @Override
  public void show() {
    rainBackgroundMusic.play();
  }

  @Override
  public void update(float delta) {
    RenderUtil.renderSprites(camera, background, bucket);
    RenderUtil.renderSprites(camera, activeRainDrops.items);
    RenderUtil.renderFonts(camera, new FontObject("Score: " + totalDropsCaught + " / "
        + totalDropsSpawned, SCREEN_WIDTH - 115, SCREEN_HEIGHT - 15));
    pollInput();
    makeSureBucketIsStillOnTheScreen();
    updateRainDropPositions();
    spawnRainDropIfNecessary();

    World world = new World(new Vector2(0, -10), true);
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    world.step(1 / 60F, 6, 2);
    debugRenderer.render(world, camera.combined);
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

  private void spawnRainDropIfNecessary() {
    if (TimeUtils.nanoTime() - lastDropTime > RAINDROP_INTERVAL_NANOSECONDS) {
      RainDrop rainDrop = rainDropPool.obtain();
      activeRainDrops.add(rainDrop);
      lastDropTime = TimeUtils.nanoTime();
    }
  }

  private void updateRainDropPositions() {
    Iterator<RainDrop> iter = activeRainDrops.iterator();
    while (iter.hasNext()) {
      RainDrop rainDrop = iter.next();
      rainDrop.translate(0, -1 * RAINDROP_SPEED * Gdx.graphics.getDeltaTime());
      if (rainDrop.getRectangle().overlaps(bucket.getRectangle())) {
        dropCaughtSound.play();
        rainDropPool.free(rainDrop);
        iter.remove();
        totalDropsCaught += 1;
        totalDropsSpawned += 1;
        if (totalDropsCaught > Globs.NUMBER_OF_DROPS_TO_WIN) {
          game.setScreen(new PlatformScreen(game));
          dispose();
        }
      } else if (rainDrop.getY() + RainDrop.WIDTH < 0) {
        rainDropPool.free(rainDrop);
        iter.remove();
        totalDropsSpawned += 1;
      }
    }
  }

  @Override
  public void dispose() {
    // TODO - come up with a good way to dispose of AssetManager textures.
    rainBackgroundMusic.stop();
    rainBackgroundMusic.dispose();
    dropCaughtSound.dispose();
  }

  @Override
  public void hide() {
    // Intentionally blank..
  }

}
