package com.game.player;

public class Bussinessman {
	public int postionX = 16;
	public int postionY = 7;

	public int[] imageIndex = new int[] { 22, 23, 24 };
	public int index;

	public void AddBallNum(Player p,int num) {
		p.ballNum+=num;
	}

	public void AddGrassNum(Player p,int num) {
		p.grassNum+=num;
	}

	public void AddAmazingFoodNum(Player p,int num) {
		p.amazingFoodNum+=num;
	}

}
