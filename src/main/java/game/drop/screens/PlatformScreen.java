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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.martinandrewhabich.animation.Animation2D;
import com.martinandrewhabich.animation.AnimationFactory;
import com.martinandrewhabich.font.FontObject;
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

  ArrayList<FontObject> credits = new ArrayList<FontObject>();

  protected PlatformScreen(Game game) {
    super(game);
    Texture playerImage = Blobs.textureFactory.makeTexture("platform/player", TextureFileType.PNG);
    player = new Animation2D( //
        AnimationFactory.makeAnimation(playerImage, 4, 1, 1 / 5F), //
        400, 20, 64, 128);
    int yPos = -100;
    int leftColumn = 250;
    int rightColumn = 415;
    credits.add(new FontObject("CREDITS", leftColumn, yPos));
    yPos -= 45;
    credits.add(new FontObject("Produced By:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= 45;
    credits.add(new FontObject("Concept By:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= 45;
    credits.add(new FontObject("Senior Programmer:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= 45;
    credits.add(new FontObject("Intern:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= 45;
    credits.add(new FontObject("Composer:", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
    yPos -= 100;
    credits.add(new FontObject("SPECIAL THANKS TO", leftColumn, yPos));
    credits.add(new FontObject("Martin Andrew Habich", rightColumn, yPos));
  }

  @Override
  public void show() {
    theme.setLooping(true);
    theme.play();
  }

  @Override
  public void update(float delta) {
    player.update(delta);
    RenderUtil.renderSprites(camera, background, floor);
    for (int index = credits.size() - 1; index >= 0; index--) {
      credits.get(index).setY(credits.get(index).getY() + Globs.CREDIT_SPEED * delta);
      if (credits.get(index).getY() > SCREEN_HEIGHT + 50) {
        credits.remove(index);
      }
    }
    RenderUtil.renderFonts(camera, credits.toArray(new FontObject[20]));
    RenderUtil.renderSprites(camera, player);
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
