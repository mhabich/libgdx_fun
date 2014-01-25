/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static com.martinandrewhabich.uglyglobals.Blobs.textureFactory;
import static game.drop.Globs.SCREEN_HEIGHT;
import static game.drop.Globs.SCREEN_WIDTH;
import game.drop.Globs;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.martinandrewhabich.animation.Animation2D;
import com.martinandrewhabich.animation.AnimationFactory;
import com.martinandrewhabich.font.FontObject;
import com.martinandrewhabich.physics.RectangularPhysicsBody;
import com.martinandrewhabich.rendering.RenderUtil;
import com.martinandrewhabich.screen.DesktopScreen;
import com.martinandrewhabich.sound.AudioFileType;
import com.martinandrewhabich.sprite.Sprite2D;
import com.martinandrewhabich.texture.TextureFileType;
import com.martinandrewhabich.uglyglobals.Blobs;

/**
 * PlatformScreen.java
 * 
 * @author Martin
 */
public class PlatformScreen extends DesktopScreen {

  Sprite2D background = new Sprite2D("splash_background", 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
  Sprite2D floor = new Sprite2D("platform/floor", 0, 0, SCREEN_WIDTH, 20);
  Sprite2D platform1 = new Sprite2D("platform/floor", 100, 150, 100, 20);
  Sprite2D platform2 = new Sprite2D("platform/floor", 400, 300, 100, 20);
  Animation2D player;
  Music theme = Blobs.audioFactory.makeMusic("platform/platform_theme", AudioFileType.MP3);

  ArrayList<FontObject> credits = new ArrayList<FontObject>();

  World world = new World(new Vector2(0, -30), true);
  Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

  RectangularPhysicsBody floorBody;
  RectangularPhysicsBody platform1Body;
  RectangularPhysicsBody platform2Body;
  RectangularPhysicsBody playerBody;

  protected PlatformScreen(Game game) {
    super(game);
    Texture playerImage = Blobs.textureFactory.makeTexture("platform/player", TextureFileType.PNG);
    player = new Animation2D( //
        AnimationFactory.makeAnimation(playerImage, 4, 1, 1 / 5F), //
        400, 20, 64, 128);
    int yPos = -100;
    int leftColumn = 250;
    int rightColumn = 415;
    int gapSize = 45;
    credits.add(new FontObject("CREDITS", leftColumn, yPos));
    yPos -= gapSize;
    credits.add(new FontObject("Produced By:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= gapSize;
    credits.add(new FontObject("Concept By:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= gapSize;
    credits.add(new FontObject("Senior Programmer:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= gapSize;
    credits.add(new FontObject("Intern:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= gapSize;
    credits.add(new FontObject("Composer:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= gapSize * 3;
    credits.add(new FontObject("SPECIAL THANKS TO", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));

    buildBodies();
  }

  private void buildBodies() {
    floorBody = new RectangularPhysicsBody(world, SCREEN_WIDTH / 2F, 10F, SCREEN_WIDTH, 20F,
        BodyType.StaticBody);
    platform1Body = new RectangularPhysicsBody(world, 150F, 110F, 100F, 20F, BodyType.StaticBody);
    platform2Body = new RectangularPhysicsBody(world, 450F, 290F, 100F, 20F, BodyType.StaticBody);
    playerBody = new RectangularPhysicsBody(world, 300F, 290F, 64F, 128F, BodyType.DynamicBody);
    playerBody.body.setFixedRotation(true);
    playerBody.body.setLinearVelocity(100F, 30F);

    floor = new Sprite2D("platform/floor", floorBody.getXLeft(), floorBody.getYBottom(),
        SCREEN_WIDTH, 20);
    platform1 = new Sprite2D("platform/floor", platform1Body.getXLeft(),
        platform1Body.getYBottom(), platform1Body.getWidth(), platform1Body.getHeight());
    platform2 = new Sprite2D("platform/floor", platform2Body.getXLeft(),
        platform2Body.getYBottom(), platform2Body.getWidth(), platform2Body.getHeight());
  }

  @Override
  public void show() {
    theme.setLooping(true);
    theme.play();
  }

  @Override
  public void update(float delta) {
    player.update(delta);
    player.setX(playerBody.getXLeft());
    player.setY(playerBody.getYBottom());
    RenderUtil.renderSprites(camera, background, floor, platform1, platform2);
    for (int index = credits.size() - 1; index >= 0; index--) {
      credits.get(index).setY(credits.get(index).getY() + Globs.CREDIT_SPEED * delta);
      if (credits.get(index).getY() > SCREEN_HEIGHT + 50) {
        credits.remove(index);
      }
    }
    RenderUtil.renderFonts(camera, credits.toArray(new FontObject[20]));
    RenderUtil.renderSprites(camera, player);

    pollInput();

    // It is recommended that you render all your graphics before you do your physics step,
    // otherwise it will be out of sync.
    debugRenderer.render(world, camera.combined);
    world.step(1 / 30F, 6, 2);
  }

  private void pollInput() {

    Vector2 vel = playerBody.body.getLinearVelocity();

    // Apply left impulse, but only if max velocity is not reached yet.
    if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
      playerBody.body.setLinearVelocity(-150F, vel.y);
    }

    // Apply right impulse, but only if max velocity is not reached yet.
    if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
      playerBody.body.setLinearVelocity(150F, vel.y);
    }

    // Jump! Apply upward impulse if not already moving.
    if (Gdx.input.isKeyPressed(Keys.SPACE) && vel.y < 2) {
      playerBody.body.setLinearVelocity(0F, 6000F);
    }
  }

  @Override
  public void hide() {
    theme.stop();
  }

  @Override
  public void dispose() {
    // Since we're using an AssetManager, this will dispose all of our textures.
    textureFactory.dispose();
    theme.dispose();
  }

}
