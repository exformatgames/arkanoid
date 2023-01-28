package com.exformatgames.arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Constants;

public class GameScreen implements Screen {

	private final AssetManager assetManager = new AssetManager();
	private final TextureAtlas textureAtlas = new TextureAtlas();
	private ArkanoidCore core;

	private Viewport gameViewport;
	private Viewport uiViewport;

	@Override
	public void show() {
		gameViewport = new ExtendViewport(10.8f, 19.2f);
		gameViewport.apply(true);
		gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		uiViewport = new ExtendViewport(10.8f, 22);
		uiViewport.apply(true);
		uiViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		assetManager.load("audio/ball-contact-with-wall-sound.ogg", Sound.class);
		assetManager.load("audio/ball-contact-with-block-sound.ogg", Sound.class);
		assetManager.load("audio/ball-contact-with-paddle-sound.ogg", Sound.class);
		assetManager.load("audio/ball-in-deadzone-sound.ogg", Sound.class);

		assetManager.load("ball.png", Texture.class);
		assetManager.load("paddle.png", Texture.class);
		assetManager.load("background.png", Texture.class);
		assetManager.load("blocks.png", Texture.class);

		while ( ! assetManager.update()){}

		textureAtlas.addRegion("ball", new TextureRegion(assetManager.get("ball.png", Texture.class)));
		textureAtlas.addRegion("paddle", new TextureRegion(assetManager.get("paddle.png", Texture.class)));
		textureAtlas.addRegion("background", new TextureRegion(assetManager.get("background.png", Texture.class)));

		for (int i = 0; i < 8; i++) {
			textureAtlas.addRegion("block", assetManager.get("blocks.png", Texture.class), i * 61, 0, 61, 28)
					.index = i + 1;
		}

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		core = new ArkanoidCore((OrthographicCamera) gameViewport.getCamera(), (OrthographicCamera) uiViewport.getCamera(), new World(new Vector2(), true), new SpriteBatch(), inputMultiplexer, textureAtlas, assetManager);
		//core = new ArkanoidCore(new Vector2(10.8f, 19.2f), new Vector2(10.8f, 19.2f), new Vector2(), inputMultiplexer, textureAtlas, assetManager);
		core.create(true, false);

		Constants.WORLD_WIDTH = 10.8f;
		Constants.WORLD_HEIGHT = 19.2f;
		Constants.UI_WIDTH = 10.8f;
		Constants.UI_HEIGHT = 19.2f;
		Constants.SCL = 0.01f;
		Constants.DIVIDER = 100;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);

		core.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		gameViewport.update(width, height, true);
		uiViewport.update(width, height, true);

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