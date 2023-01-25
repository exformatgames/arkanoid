package com.exformatgames.arkanoid;

import com.badlogic.gdx.Game;

public class ArkanoidGame extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}