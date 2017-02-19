package com.game.player;

import java.awt.Color;

public class Pet {
	public String name;
	public int level;
	public int harmPoint;
	public int lifePoint;
	public int lifeFullPoint;
	public int expNum;
	public int expFullNum;
	public int postionX;
	public int postionY;

	public boolean isEnemy;

	public boolean myTurn;

	public Color colorHpBG = Color.WHITE;
	public Color colorExpColorBG = Color.WHITE;
	public Color colorHp = Color.RED;
	public Color colorExpColor = Color.GREEN;
	public double PostionY_Hp;
	public int PostionX_Hp;
	public double PostionX_Exp;
	public double PostionY_Exp;
	public int PostionX_AttackMode;
	public int PostionY_AttackMode;

	public String[] nameArray = new String[8];
	public int[] enemyPetImage = new int[8];
	public int enemyPetImageIndex;
	private boolean isProtected = false;
	private boolean isFirstLoad;

	public Pet(boolean isEnemy) {
		// TODO Auto-generated constructor stub
		isFirstLoad = true;
		for (int i = 0; i < 8; i++){
			enemyPetImage[i] = i + 40;
			nameArray[i] = "µÐÈË"+i;
		}
		enemyPetImageIndex = 0;
		if (isEnemy) {
			PostionX_AttackMode = 12;
			PostionY_AttackMode = 1;
			PostionY_Hp = 2;
			PostionX_Hp = 6;
			level = 5;
			myTurn = false;
			this.isEnemy = isEnemy;
		} else {
			PostionX_AttackMode = 6;
			PostionY_AttackMode = 3;
			PostionY_Hp = 5;
			PostionX_Hp = 13;
			PostionX_Exp = 12.2;
			PostionY_Exp = 6.8;
			level = 10;
			myTurn = true;
		}
		refreshParameter();
		isFirstLoad = false;
	}

	public void resetEnemy() {
		if (isEnemy) {
			PostionX_AttackMode = 12;
			PostionY_AttackMode = 1;
			PostionY_Hp = 2;
			PostionX_Hp = 6;
			harmPoint = 3 * level;
			lifePoint = 21 * level;
		}
	}

	public void wasHitted(Pet petAttacking) {
		if (!isProtected)
			this.lifePoint = this.lifePoint - petAttacking.harmPoint;
	}

	public void enemyAttack(Pet playerPet) {
		if (isEnemy && myTurn) {
			playerPet.lifePoint = playerPet.lifePoint - this.harmPoint;
		}
	}

	public void cureSelf() {
		if (!isEnemy)
			this.lifePoint += 12;
	}

	public void defense() {
		if (!isEnemy)
			isProtected = true;
	}

	public boolean isProtect() {
		return isProtected;
	}

	public void resetDefense() {
		if (!isEnemy)
			isProtected = false;
	}

	public void deadTogether(Pet opponent) {
		this.lifePoint = 0;
		opponent.lifePoint = 0;
	}

	public boolean isDead() {
		if (lifePoint <= 0)
			return true;
		return false;
	}

	public void refreshParameter() {
		harmPoint = 3 * level;
		lifeFullPoint = 21 * level;
		if (isEnemy || isFirstLoad)
			lifePoint = lifeFullPoint;
		expFullNum = 80 * level;
		if (expNum > expFullNum) {
			level++;
			expNum = expNum % expFullNum;
		}
	}

	public void expNumUp(Pet enemy) {
		expNum += enemy.level * 100;
	}
}
