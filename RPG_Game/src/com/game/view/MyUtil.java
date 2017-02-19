package com.game.view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MyUtil {
	public static ArrayList<Image> images = new ArrayList<Image>();
	public static ImageIcon iconSingle = new ImageIcon("res/floor2.png");
	public static int iconBitWidth = iconSingle.getIconWidth() / 10;
	public static int iconBitHeight = iconSingle.getIconHeight() / 10;
	public static ImageIcon battleMap = new ImageIcon("res/BattleField.png");
	public static ImageIcon battleActtackModeMap = new ImageIcon("res/BattleField.png");
	public static ImageIcon iconBigTree = new ImageIcon("res/bigtree.png");
	public static ImageIcon enemyPet = new ImageIcon("res/pet1.png");
	public static ImageIcon playerPet1 = new ImageIcon("res/palyerFightMode.png");
	public static ImageIcon playerPet = new ImageIcon("res/Player_pet1.png");
	public static ImageIcon bagMode = new ImageIcon("res/bag2.png");
	public static ImageIcon dialogueBox = new ImageIcon("res/fightingText.png");
	public static boolean ACTTACK_MODE = false;
	public static boolean DISCOVERY_MODE = true;
	public static boolean CURRENT_MODE = false;
	public static boolean isBagMode = false;
	public static boolean isBussinessMode = false;
	public static boolean isDoctorMode = false;
	public static boolean isEnemyMode = false;
	
	public static boolean startAttackEffect = false;
	public static boolean startWalkEffect = false;

	public static int loopNum = 0;					
	public static int bitLength = 0;				//走路动画偏移量

	public static int SCREEN_WIDTH = 640;
	public static int SCREEN_HEIGHT = 320;

	public static void SetImageList() {
		images.add(iconSingle.getImage()); // 0 草
		images.add(new ImageIcon("res/poor1.png").getImage()); // 1 水池1
		images.add(new ImageIcon("res/poor2.png").getImage()); // 2 水池2
		images.add(new ImageIcon("res/poor3.png").getImage()); // 3 水池3
		images.add(new ImageIcon("res/poor4.png").getImage()); // 4 水池4
		images.add(new ImageIcon("res/poor5.png").getImage()); // 5 水池5
		images.add(new ImageIcon("res/poor6.png").getImage()); // 6 水池6
		images.add(new ImageIcon("res/poor7.png").getImage()); // 7 水池7
		images.add(new ImageIcon("res/poor8.png").getImage()); // 8 水池8
		images.add(new ImageIcon("res/poor9.png").getImage()); // 9 水池9
		images.add(iconBigTree.getImage()); // 10 大树
		images.add(new ImageIcon("res/tree.png").getImage()); // 11 小树
		images.add(new ImageIcon("res/stone.png").getImage()); // 12 石头
		images.add(new ImageIcon("res/player1.png").getImage()); // 13 玩家
		images.add(new ImageIcon("res/floor.png").getImage()); // 14 小草
		images.add(battleMap.getImage()); // 15 战斗地图
		images.add(battleActtackModeMap.getImage()); // 16 战斗地图战斗模式
		images.add(new ImageIcon("res/pionter.png").getImage()); // 17 指示符号
		images.add(enemyPet.getImage()); // 18 战斗中敌方宠物
		images.add(playerPet.getImage()); // 19 战斗中玩家宠物
		// images.add(playerPet1.getImage()); // 19 战斗中玩家宠物
		images.add(new ImageIcon("res/doctor1.png").getImage()); // 20
																	// 地图上的医生--状态1
		images.add(new ImageIcon("res/doctor2.png").getImage()); // 21
																	// 地图上的医生--状态2
		images.add(new ImageIcon("res/business1.png").getImage()); // 22
																	// 地图上的商人--状态1
		images.add(new ImageIcon("res/business2.png").getImage()); // 23
																	// 地图上的商人--状态2
		images.add(new ImageIcon("res/business3.png").getImage()); // 24
																	// 地图上的商人--状态3
		images.add(new ImageIcon("res/enemy1.png").getImage()); // 25
																// 地图上的对手--状态1
		images.add(new ImageIcon("res/enemy2.png").getImage()); // 26
																// 地图上的对手--状态2
		images.add(new ImageIcon("res/enemy3.png").getImage()); // 27
																// 地图上的对手--状态3
		images.add(new ImageIcon("res/player1 (1).png").getImage());// 28
																	// 玩家--状态--静止向左
		images.add(new ImageIcon("res/player1 (2).png").getImage());// 29
																	// 玩家--状态--静止向右
		images.add(new ImageIcon("res/player1 (3).png").getImage());// 30
																	// 玩家--状态--静止向前
		images.add(new ImageIcon("res/player1 (4).png").getImage());// 31
																	// 玩家--状态--静止向后
		images.add(new ImageIcon("res/player2 (1).png").getImage());// 32
																	// 玩家--状态--奔跑向右--左脚
		images.add(new ImageIcon("res/player2 (2).png").getImage());// 33
																	// 玩家--状态--奔跑向前--左脚
		images.add(new ImageIcon("res/player2 (3).png").getImage());// 34
																	// 玩家--状态--奔跑向前--右脚
		images.add(new ImageIcon("res/player2 (4).png").getImage());// 35
																	// 玩家--状态--奔跑向后--右脚
		images.add(new ImageIcon("res/player2 (5).png").getImage());// 36
																	// 玩家--状态--奔跑向后--左脚
		images.add(new ImageIcon("res/player2 (6).png").getImage());// 37
																	// 玩家--状态--奔跑向左--右脚
		images.add(new ImageIcon("res/player2 (7).png").getImage());// 38
																	// 玩家--状态--奔跑向左--左脚
		images.add(new ImageIcon("res/player2 (8).png").getImage());// 39
																	// 玩家--状态--奔跑向右--右脚
		images.add(new ImageIcon("res/386-迪奥西斯.png").getImage()); // 40 战斗中敌方宠物
		images.add(new ImageIcon("res/146-火焰鸟.png").getImage()); // 41 战斗中敌方宠物
		images.add(new ImageIcon("res/151-梦幻.png").getImage()); // 42 战斗中敌方宠物
		images.add(new ImageIcon("res/157-烈焰兽.png").getImage()); // 43 战斗中敌方宠物
		images.add(new ImageIcon("res/244-炎帝.png").getImage()); // 44 战斗中敌方宠物
		images.add(new ImageIcon("res/245-水君.png").getImage()); // 45 战斗中敌方宠物
		images.add(new ImageIcon("res/243-雷皇.png").getImage()); // 46 战斗中敌方宠物
		images.add(new ImageIcon("res/006-喷火龙.png").getImage()); // 47 战斗中敌方宠物
		images.add(bagMode.getImage()); // 48 战斗中敌方宠物
		images.add(new ImageIcon("res/pionter1.png").getImage()); // 49 指示符号
		images.add(dialogueBox.getImage()); // 50 对话框
	}
}
