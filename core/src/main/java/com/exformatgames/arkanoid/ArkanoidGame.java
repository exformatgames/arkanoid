package com.exformatgames.arkanoid;

import com.badlogic.gdx.Game;

public class ArkanoidGame extends Game {

	public static int SCORE = 0;
	public static int LIVES = 3;

	public static final short CATEGORY_BALL = 0x01;
	public static final short CATEGORY_BONUS = 0x02;
	public static final short CATEGORY_BLOCK = 0x04;
	public static final short CATEGORY_BULLET = 0x08;
	public static final short CATEGORY_DEADZONE = 0x16;
	public static final short CATEGORY_PADDLE = 0x24;
	public static final short CATEGORY_WALL = 0x48;

	public static final short MASK_BALL = CATEGORY_BLOCK | CATEGORY_WALL | CATEGORY_DEADZONE | CATEGORY_PADDLE;
	public static final short MASK_BLOCK = CATEGORY_BALL | CATEGORY_BULLET;
	public static final short MASK_BONUS = CATEGORY_PADDLE;
	public static final short MASK_DEADZONE = CATEGORY_BALL;
	public static final short MASK_PADDLE = CATEGORY_BALL | CATEGORY_BONUS | CATEGORY_WALL;
	public static final short MASK_WALL = CATEGORY_BALL | CATEGORY_BULLET;


	public static GameState GAME_STATE = GameState.READY;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}