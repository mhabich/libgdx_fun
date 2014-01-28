/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static com.martinandrewhabich.uglyglobals.Blobs.textureFactory;
import static game.drop.Globs.SCALE;
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

  Sprite2D background = new Sprite2D("splash_background", 0, 0, SCREEN_WIDTH * SCALE, SCREEN_HEIGHT
      * SCALE);
  Sprite2D floor = new Sprite2D("platform/floor", 0, 0, SCREEN_WIDTH * SCALE, 20);
  Sprite2D platform1 = new Sprite2D("platform/floor", 100, 150, 100, 20);
  Sprite2D platform2 = new Sprite2D("platform/floor", 400, 300, 100, 20);
  Animation2D player;
  Music theme = Blobs.audioFactory.makeMusic("platform/platform_theme", AudioFileType.MP3);

  ArrayList<FontObject> credits = new ArrayList<FontObject>();

  World world = new World(new Vector2(0F, -5F), true);
  Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

  RectangularPhysicsBody floorBody;
  RectangularPhysicsBody platform1Body;
  RectangularPhysicsBody platform2Body;
  RectangularPhysicsBody playerBody;

  protected PlatformScreen(Game game) {
    super(game);
    camera.setToOrtho(false, //
        Gdx.graphics.getWidth() * SCALE, //
        Gdx.graphics.getHeight() * SCALE);
    Texture playerImage = Blobs.textureFactory.makeTexture("platform/player", TextureFileType.PNG);
    player = new Animation2D( //
        AnimationFactory.makeAnimation(playerImage, 4, 1, 1 / 5F), //
        400 * SCALE, 20 * SCALE, 64 * SCALE, 128 * SCALE);
    float yPos = -100 * SCALE;
    float leftColumn = 250 * SCALE;
    float rightColumn = 415 * SCALE;
    float gapSize = 45 * SCALE;
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
    floorBody = new RectangularPhysicsBody(world, SCREEN_WIDTH / 2F * SCALE, 10F * SCALE,
        SCREEN_WIDTH * SCALE, 20F * SCALE, BodyType.StaticBody, true);
    platform1Body = new RectangularPhysicsBody(world, 150F * SCALE, 110F * SCALE, 100F * SCALE,
        20F * SCALE, BodyType.StaticBody, true);
    platform2Body = new RectangularPhysicsBody(world, 450F * SCALE, 290F * SCALE, 100F * SCALE,
        20F * SCALE, BodyType.StaticBody, true);
    playerBody = new RectangularPhysicsBody(world, 300F * SCALE, 290F * SCALE, 64F * SCALE,
        128F * SCALE, BodyType.DynamicBody, false);
    playerBody.body.setFixedRotation(true);
    playerBody.body.setLinearVelocity(2F, 5F);

    floor = new Sprite2D("platform/floor", floorBody.getXLeft(), floorBody.getYBottom(),
        SCREEN_WIDTH * SCALE, 20 * SCALE);
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
      if (credits.get(index).getY() > SCALE * (SCREEN_HEIGHT + 50)) {
        credits.remove(index);
      }
    }
    RenderUtil.renderFonts(camera, credits.toArray(new FontObject[20]));
    RenderUtil.renderSprites(camera, player);

    pollInput();

    // It is recommended that you render all your graphics before you do your physics step,
    // otherwise it will be out of sync.
    debugRenderer.render(world, camera.combined);
    world.step(1 / 30F, 1, 1);
  }

  private void pollInput() {

    Vector2 vel = playerBody.body.getLinearVelocity();
    float MAX_PLAYER_VELOCITY = 2F;
    boolean isPressingLeftMovementKey = Gdx.input.isKeyPressed(Keys.A)
        || Gdx.input.isKeyPressed(Keys.LEFT);
    boolean isPressingRightMovementKey = Gdx.input.isKeyPressed(Keys.D)
        || Gdx.input.isKeyPressed(Keys.RIGHT);

    if (vel.x > -MAX_PLAYER_VELOCITY && isPressingLeftMovementKey) {
      // Move left
      playerBody.body.applyForceToCenter(-10F, 0F, true);
    } else if (vel.x < MAX_PLAYER_VELOCITY && isPressingRightMovementKey) {
      // Move right
      playerBody.body.applyForceToCenter(10F, 0F, true);
    } else if (!isPressingLeftMovementKey && !isPressingRightMovementKey) {
      // Damp horizontal motion - necessary because we set the player's friction to zero.
      // We set the player's friction to zero to prevent them from "sticking" to walls.
      playerBody.body.setLinearVelocity(0.9F * vel.x, vel.y);
    }

    // Jump! Apply upward impulse if not already moving.
    if (Gdx.input.isKeyPressed(Keys.SPACE) && Math.abs(vel.y) < 0.1F) {
      playerBody.body.setLinearVelocity(vel.x, 5F);
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
