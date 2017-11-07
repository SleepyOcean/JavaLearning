package com.sleepy.data;

public class Goods {
	public int id;
	public String category;
	public String name;
	public float price;
	public int quantity;

	public Goods(int id, String name, String category, float price,int quantity) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}
