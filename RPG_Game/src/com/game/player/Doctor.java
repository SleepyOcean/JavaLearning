package com.game.player;

public class Doctor {
	public int postionX = 3;
	public int postionY = 0;

	public int[] imageIndex = new int[] { 20, 21 };
	public int index;

	public void aidPets(Pet pet) {
		pet.lifePoint = pet.lifeFullPoint;
	}
}
