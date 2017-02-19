package com.game.player;

public class BattleMode {
	public int pionter1PostionX;
	public int pionter1PostionY;
	public int pionter2PostionX;
	public int pionter2PostionY;
	public boolean FistLoad;
	public boolean isFighting;
	public boolean CloseBattleMode_Enable;

	/*
	 * 画布的参数常量
	 */
	public static int LIFE_POINT_WIDTH = 22; // 血条在屏幕中的宽度
	public static int NAME_PET_PLAYER_X_OFFSET = -2;
	public static int NAME_PET_PLAYER_Y_OFFSET = 28;
	public static int NAME_PET_ENEMY_X_OFFSET = -2;
	public static int NAME_PET_ENEMY_Y_OFFSET = 16;
	public static int LEVEL_PET_PLAYER_X_OFFSET = -9;
	public static int LEVEL_PET_PLAYER_Y_OFFSET = 28;
	public static int LEVEL_PET_ENEMY_X_OFFSET = -7;
	public static int LEVEL_PET_ENEMY_Y_OFFSET = 16;
	public static int EXPFULLNUM_PET_PLAYER_X_OFFSET = 10;
	public static int EXPFULLNUM_PET_PLAYER_Y_OFFSET = 20;
	public static int EXPNUM_PET_PLAYER_X_OFFSET = -20;
	public static int EXPNUM_PET_PLAYER_Y_OFFSET = 20;
	public static int PET_PLAYER_OFFSET = -20;
	public static int MENU_X_OFFSET = 20;
	public static int MENU_Y_OFFSET = 23;
	public static int MENU_MESSAGE_X_OFFSET = 10;

	public BattleMode() {
		// TODO Auto-generated constructor stub
		CloseBattleMode_Enable = false;
		resetPointer();
	}

	public void setPionter1Postion(int choice) {
		switch (choice) {
		case 1:
			pionter1PostionX = 2;
			pionter1PostionY = 7;
			break;

		case 2:
			pionter1PostionX = 8;
			pionter1PostionY = 7;
			break;

		case 3:
			pionter1PostionX = 2;
			pionter1PostionY = 8;
			break;

		case 4:
			pionter1PostionX = 8;
			pionter1PostionY = 8;
			break;

		default:
			break;
		}
		return;
	}

	public void setPionter2Postion(int choice) {
		switch (choice) {
		case 1:
			pionter2PostionX = 13;
			pionter2PostionY = 7;
			break;

		case 2:
			pionter2PostionX = 16;
			pionter2PostionY = 7;
			break;

		case 3:
			pionter2PostionX = 13;
			pionter2PostionY = 8;
			break;

		case 4:
			pionter2PostionX = 16;
			pionter2PostionY = 8;
			break;

		default:
			break;
		}
		return;
	}

	public void resetPointer() {
		this.FistLoad = true;
		this.isFighting = false;
		this.setPionter1Postion(1);
		this.setPionter2Postion(1);
	}
}
