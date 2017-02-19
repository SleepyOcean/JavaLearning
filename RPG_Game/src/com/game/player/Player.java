package com.game.player;

public class Player {
	public String name;
	public int petNum;
	public int ballNum;
	public int grassNum;
	public int amazingFoodNum;
	public int money;

	public int state;
	public int postionX;
	public int postionY;
	public boolean FistLoad;

	public int PostionX_AttackMode = 6;
	public int PostionY_AttackMode = 3;

	public int[] imageIndex = new int[] { 13, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
	public int index = 0;
	public int directionIndex = 0;

	public static int DIERCTION_LEFT = 28;
	public static int DIERCTION_RIGHT = 29;
	public static int DIERCTION_UP = 31;
	public static int DIERCTION_DOWN = 30;

	public Player() {
		// TODO Auto-generated constructor stub
		postionX = 7;
		postionY = 3;
		ballNum = 5;
		grassNum = 2;
		amazingFoodNum = 1;
		state = 0;
		FistLoad = true;
	}

	public void setPostion() {
		if (FistLoad) {
			postionX = postionY = 0;
			return;
		}
		return;
	}

	public void moveAction(int drection) {
		// 根据方向改变state，是人物朝向改变；
	}

	public void setIndex(int indexIn) {
		for (int i = 0; i < imageIndex.length; i++) {
			if (imageIndex[i] == indexIn) {
				index = i;
				break;
			}
		}
	}

	public void setDirectionIndex(int indexIn) {
		for (int i = 0; i < imageIndex.length; i++) {
			if (imageIndex[i] == indexIn) {
				directionIndex = i;
				break;
			}
		}
	}

}
