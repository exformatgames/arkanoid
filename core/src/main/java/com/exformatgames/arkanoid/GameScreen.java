package com.exformatgames.arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.exformatgames.defender.Configurations;

public class GameScreen implements Screen {

	private final AssetManager assetManager = new AssetManager();
	private final TextureAtlas textureAtlas = new TextureAtlas();
	private ArkanoidCore core;

	@Override
	public void show() {
		assetManager.load("audio/ball-contact-with-wall-sound.ogg", Sound.class);
		assetManager.load("audio/ball-contact-with-block-sound.ogg", Sound.class);
		assetManager.load("audio/ball-contact-with-paddle-sound.ogg", Sound.class);
		assetManager.load("audio/ball-in-deadzone-sound.ogg", Sound.class);

		assetManager.load("ball.png", Texture.class);
		assetManager.load("paddle.png", Texture.class);
		assetManager.load("background.png", Texture.class);
		assetManager.load("blocks.png", Texture.class);
		assetManager.load("bonuses/length-bonus.png", Texture.class);
		assetManager.load("bonuses/balls-bonus.png", Texture.class);
		assetManager.load("bonuses/live-bonus.png", Texture.class);
		assetManager.load("bonuses/bomb-bonus.png", Texture.class);

		assetManager.load("font.png", Texture.class);
		assetManager.load("font.fnt", BitmapFont.class);


		while ( ! assetManager.update()){}

		textureAtlas.addRegion("ball", new TextureRegion(assetManager.get("ball.png", Texture.class)));
		textureAtlas.addRegion("paddle", new TextureRegion(assetManager.get("paddle.png", Texture.class)));
		textureAtlas.addRegion("background", new TextureRegion(assetManager.get("background.png", Texture.class)));
		textureAtlas.addRegion("length_bonus", new TextureRegion(assetManager.get("bonuses/length-bonus.png", Texture.class)));
		textureAtlas.addRegion("balls_bonus", new TextureRegion(assetManager.get("bonuses/balls-bonus.png", Texture.class)));
		textureAtlas.addRegion("live_bonus", new TextureRegion(assetManager.get("bonuses/live-bonus.png", Texture.class)));
		textureAtlas.addRegion("bomb_bonus", new TextureRegion(assetManager.get("bonuses/bomb-bonus.png", Texture.class)));

		for (int i = 0; i < 8; i++) {
			textureAtlas.addRegion("block", assetManager.get("blocks.png", Texture.class), i * 61, 0, 61, 28)
					.index = i + 1;
		}

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		Configurations.SCL = 0.01f;
		Configurations.DIVIDER = 100;
		Configurations.WORLD_WIDTH = 10.8f;
		Configurations.WORLD_HEIGHT = 19.2f;
		Configurations.UI_WIDTH = 1080;
		Configurations.UI_HEIGHT = 1920;

		core = new ArkanoidCore(new Vector2(10.8f, 19.2f), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), new Vector2(), inputMultiplexer, textureAtlas, assetManager);
		core.create(true, false);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);

		core.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		core.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		core.dispose();
	}
}