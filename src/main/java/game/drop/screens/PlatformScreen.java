/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package game.drop.screens;

import static com.martinandrewhabich.uglyglobals.Blobs.textureFactory;
import static game.drop.Globs.SCREEN_HEIGHT;
import static game.drop.Globs.SCREEN_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.martinandrewhabich.animation.Animation2D;
import com.martinandrewhabich.animation.AnimationFactory;
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
  Animation2D player;
  Music theme = Blobs.audioFactory.makeMusic("platform/platform_theme", AudioFileType.MP3);

  protected PlatformScreen(Game game) {
    super(game);
    Texture playerImage = Blobs.textureFactory.makeTexture("platform/player", TextureFileType.PNG);
    player = new Animation2D( //
        AnimationFactory.makeAnimation(playerImage, 4, 1, 1 / 5F), //
        400, 20, 64, 128);
  }

  @Override
  public void show() {
    theme.setLooping(true);
    theme.play();
  }

  @Override
  public void update(float delta) {
    player.update(delta);
    RenderUtil.renderSprites(camera, background, floor, player);
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
