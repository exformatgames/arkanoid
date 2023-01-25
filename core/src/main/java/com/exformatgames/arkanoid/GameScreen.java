package com.exformatgames.arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.exformatgames.defender.Constants;

public class GameScreen implements Screen {

	private final AssetManager assetManager = new AssetManager();
	private final TextureAtlas textureAtlas = new TextureAtlas();
	private ArkanoidCore core;

	@Override
	public void show() {

		textureAtlas.addRegion("ball", new TextureRegion(new Texture("ball.png")));
		textureAtlas.addRegion("paddle", new TextureRegion(new Texture("paddle.png")));
		textureAtlas.addRegion("background", new TextureRegion(new Texture("background.png")));

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		core = new ArkanoidCore(new Vector2(10.8f, 19.2f), new Vector2(0, 0), inputMultiplexer, textureAtlas, assetManager);
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
		core.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		ScreenUtils.clear(0,0,0,1);
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