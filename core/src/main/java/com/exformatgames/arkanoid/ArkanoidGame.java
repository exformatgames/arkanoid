package com.exformatgames.arkanoid;

import com.badlogic.gdx.Game;

public class ArkanoidGame extends Game {

	public static final short CATEGORY_BALL = 0x01;
	public static final short CATEGORY_BONUS = 0x02;
	public static final short CATEGORY_BOX = 0x04;
	public static final short CATEGORY_BULLET_PLAYER = 0x08;
	public static final short CATEGORY_WALL = 0x16;
	public static final short CATEGORY_DEADZONE = 0x24;
	public static final short CATEGORY_PLATFORM = 0x48;


	public static final short MASK_WALL = CATEGORY_BALL | CATEGORY_BULLET_PLAYER;
	public static final short MASK_DEADZONE = CATEGORY_BALL;
	public static final short MASK_PLATFORM = CATEGORY_BALL | CATEGORY_BONUS | CATEGORY_WALL;
	public static final short MASK_BOX = CATEGORY_BALL | CATEGORY_BULLET_PLAYER;
	public static final short MASK_BALL = CATEGORY_BOX | CATEGORY_WALL | CATEGORY_DEADZONE | CATEGORY_PLATFORM;

	public static GameState GAME_STATE = GameState.READY;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}