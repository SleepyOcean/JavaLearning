package com.game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.game.player.BagMode;
import com.game.player.BattleMode;
import com.game.player.BussinessMode;
import com.game.player.Bussinessman;
import com.game.player.DiscoveryMode;
import com.game.player.Doctor;
import com.game.player.Enemy;
import com.game.player.Pet;
import com.game.player.Player;
import com.game.view.FrameView.Panel.PulseSignalTask;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class FrameView extends JFrame {

	Player player = new Player();
	Bussinessman bussinessman = new Bussinessman();
	Doctor doctor = new Doctor();
	Enemy enemy = new Enemy();
	BattleMode battleMode = new BattleMode();
	DiscoveryMode discoveryMode = new DiscoveryMode();
	Pet petEnemy = new Pet(true);
	Pet petPlayer = new Pet(false);
	Image offScreenImage;
	Timer timer;
	Timer PulseSignal;
	Font battleParamsFont = new Font("宋体", Font.PLAIN, 11);
	Font battleMenuFont = new Font("宋体", Font.PLAIN, 20);
	Font bagMenuFont = new Font("宋体", Font.BOLD, 20);
	int[] assetImage = new int[200];

	final int WALK_FRAMES = 32; // 一秒多少帧
	final int WALK_TIME = 500 / WALK_FRAMES; // I*TIME=1S=1000

	final int BATTLE_FRAMES = 25;
	final int BATTLE_TIME = 100 / BATTLE_FRAMES;
	final int BATTLE_LOOP_TIMES = 3;

	public FrameView() {
		// TODO Auto-generated constructor stub
		Panel panel = new Panel();
		add(panel);
		addKeyListener(panel);
		MyUtil.SetImageList();
	}

	public class Panel extends JPanel implements KeyListener {

		public Panel() {
			// TODO Auto-generated constructor stub
			timer = new Timer();
			timer.schedule(new MyTask(), 1000, ((int) ((Math.random() * 1000) % 3) + 1) * 10000);
			PulseSignal = new Timer();
			timer.schedule(new PulseSignalTask(), 1000, 1000);

			for (int i = 0; i < 200; i++) {
				if (i == doctor.postionX + doctor.postionY * 20) {
					assetImage[i] = doctor.imageIndex[0];
				} else if (i == 4) {
					assetImage[i] = 7;
					assetImage[i + 1] = 8;
					assetImage[i + 2] = 9;
				} else if (i == bussinessman.postionX + bussinessman.postionY * 20) {
					assetImage[i] = bussinessman.imageIndex[0];
				} else if (i == enemy.postionX + enemy.postionY * 20) {
					assetImage[i] = enemy.imageIndex[0];
				} else if (i == 55) {
					assetImage[i] = 1;
					assetImage[i + 1] = 2;
					assetImage[i + 2] = 3;
					assetImage[i + 20] = 4;
					assetImage[i + 21] = 5;
					assetImage[i + 22] = 6;
					assetImage[i + 40] = 7;
					assetImage[i + 41] = 8;
					assetImage[i + 42] = 9;
				} else if (i == 189) {
					assetImage[i] = 1;
					assetImage[i + 1] = 2;
					assetImage[i + 2] = 3;
				} else if (i == 40) {
					for (int j = 0; j < 6; j++) {
						assetImage[i + j] = 11;
						assetImage[i + j + 20] = 11;
						assetImage[i + j + 80] = 11;
						assetImage[i + j + 100] = 11;
					}
				} else if (i == 52) {
					assetImage[i] = 11;
					assetImage[i + 20] = 11;
					assetImage[i + 40] = 11;
					assetImage[i + 60] = 11;
				} else if (i == 33 || i == 89 || i == 152 || i == 157)
					assetImage[i] = 12;
			}
		}

		@Override
		public void update(Graphics g) { // 双缓冲，解决动画闪烁问题
			// TODO Auto-generated method stub
			if (offScreenImage == null)
				offScreenImage = this.createImage(640, 320); // 新建一个图像缓存空间,这里图像大小为640*320
			Graphics gImage = offScreenImage.getGraphics(); // 把它的画笔拿过来,给gImage保存着
			paint(gImage); // 将要画的东西画到图像缓存空间去
			g.drawImage(offScreenImage, 0, 0, null); // 然后一次性显示出来
		}

		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub

			if (!MyUtil.ACTTACK_MODE) {
				discoveryMode(g);
			} else {
				BattleMode(g);
			}

			if (MyUtil.isBagMode) {
				drawBagItem(g);
			}

		}

		/*
		 * 探索界面显示
		 */
		public void discoveryMode(Graphics g) {
			for (int i = 0; i < 200; i++) {
				// 在整个画布上画草
				g.drawImage(MyUtil.images.get(0), (i % 20) * MyUtil.iconSingle.getIconWidth(),
						(i / 20) * MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getIconWidth(),
						MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());

				// 画石头、树、湖等
				if (i == doctor.postionX + doctor.postionY * 20)
					g.drawImage(MyUtil.images.get(doctor.imageIndex[doctor.index]),
							(i % 20) * MyUtil.iconSingle.getIconWidth(), (i / 20) * MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getImageObserver());
				else if (i == bussinessman.postionX + bussinessman.postionY * 20)
					g.drawImage(MyUtil.images.get(bussinessman.imageIndex[bussinessman.index]),
							(i % 20) * MyUtil.iconSingle.getIconWidth(), (i / 20) * MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getImageObserver());
				else if (i == enemy.postionX + enemy.postionY * 20)
					g.drawImage(MyUtil.images.get(enemy.imageIndex[enemy.index]),
							(i % 20) * MyUtil.iconSingle.getIconWidth(), (i / 20) * MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getImageObserver());
				else
					g.drawImage(MyUtil.images.get(assetImage[i]), (i % 20) * MyUtil.iconSingle.getIconWidth(),
							(i / 20) * MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getIconWidth(),
							MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());
			}
			for (int i = 0; i < 200; i++)
				// 画玩家
				if (i == (player.postionX + player.postionY * 20)) {
					if (!discoveryMode.isMoving)
						g.drawImage(MyUtil.images.get(player.imageIndex[discoveryMode.playerDirection]),
								(i % 20) * MyUtil.iconSingle.getIconWidth(),
								(i / 20) * MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getIconWidth(),
								MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());
					else
						walkEffect(g, i);
				}
			if (MyUtil.isBussinessMode) {
				drawBussinessDialogue(g);
			}
			if (MyUtil.isDoctorMode) {
				drawDoctorDialogue(g);
			}
			if (MyUtil.isEnemyMode) {
				drawEnemyDialogue(g);
			}
		}

		private void drawEnemyDialogue(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyUtil.images.get(50), 0 * MyUtil.iconSingle.getIconWidth(),
					6 * MyUtil.iconSingle.getIconHeight(), MyUtil.SCREEN_WIDTH, (int) (MyUtil.SCREEN_HEIGHT / 2.5),
					MyUtil.dialogueBox.getImageObserver());
			g.setColor(Color.WHITE);
			g.setFont(battleMenuFont);
			g.drawString("想挑战我，你还不行!", 0 * MyUtil.iconSingle.getIconWidth() + 36,
					6 * MyUtil.iconSingle.getIconHeight() + 55);
			g.drawString("下次等你变强了再来找我吧，哈......哈......哈......!!!", 0 * MyUtil.iconSingle.getIconWidth() + 36,
					6 * MyUtil.iconSingle.getIconHeight() + 55 + BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
		}

		private void drawDoctorDialogue(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyUtil.images.get(50), 0 * MyUtil.iconSingle.getIconWidth(),
					6 * MyUtil.iconSingle.getIconHeight(), MyUtil.SCREEN_WIDTH, (int) (MyUtil.SCREEN_HEIGHT / 2.5),
					MyUtil.dialogueBox.getImageObserver());
			g.setColor(Color.WHITE);
			g.setFont(battleMenuFont);
			g.drawString("治疗完成，你的宠物已经充满了活力!", 0 * MyUtil.iconSingle.getIconWidth() + 36,
					6 * MyUtil.iconSingle.getIconHeight() + 55);
		}

		private void drawBussinessDialogue(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyUtil.images.get(50), 0 * MyUtil.iconSingle.getIconWidth(),
					6 * MyUtil.iconSingle.getIconHeight(), MyUtil.SCREEN_WIDTH, (int) (MyUtil.SCREEN_HEIGHT / 2.5),
					MyUtil.dialogueBox.getImageObserver());
			g.drawImage(MyUtil.images.get(17),
					0 * MyUtil.iconSingle.getIconWidth() + 26
							+ BussinessMode.pointerChoose * BussinessMode.NAME_ITEM_INTERVAL_X_OFFSET,
					6 * MyUtil.iconSingle.getIconHeight() + 65, MyUtil.iconSingle.getIconWidth(),
					MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());
			g.setColor(Color.WHITE);
			g.setFont(battleMenuFont);
			g.drawString("你想买点什么？", 0 * MyUtil.iconSingle.getIconWidth() + 36,
					6 * MyUtil.iconSingle.getIconHeight() + 55);
			g.drawString("精灵球",
					0 * MyUtil.iconSingle.getIconWidth() + 36 + 0 * BussinessMode.NAME_ITEM_INTERVAL_X_OFFSET,
					6 * MyUtil.iconSingle.getIconHeight() + 55 + BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
			g.drawString("奇异果",
					0 * MyUtil.iconSingle.getIconWidth() + 36 + 1 * BussinessMode.NAME_ITEM_INTERVAL_X_OFFSET,
					6 * MyUtil.iconSingle.getIconHeight() + 55 + BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
			g.drawString("精神草",
					0 * MyUtil.iconSingle.getIconWidth() + 36 + 2 * BussinessMode.NAME_ITEM_INTERVAL_X_OFFSET,
					6 * MyUtil.iconSingle.getIconHeight() + 55 + BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);

		}

		/*
		 * 战斗界面显示
		 */
		public void BattleMode(Graphics g) {
			g.drawImage(MyUtil.images.get(16), 0, 0, MyUtil.battleMap.getIconWidth(), MyUtil.battleMap.getIconHeight(), // 战斗主界面
					MyUtil.battleMap.getImageObserver());

			drawText(g);

			if (!MyUtil.startAttackEffect)
				drawPetImage(g);
			else {
				if (!battleMode.CloseBattleMode_Enable)
					attackEffect(g);
				if (petPlayer.myTurn || battleMode.CloseBattleMode_Enable)
					g.drawImage(MyUtil.images.get(19), // 玩家图片
							petPlayer.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth(),
							petPlayer.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight()
									+ battleMode.PET_PLAYER_OFFSET,
							MyUtil.playerPet.getIconWidth(), MyUtil.playerPet.getIconHeight(),
							MyUtil.playerPet.getImageObserver());
				else if (petEnemy.myTurn || battleMode.CloseBattleMode_Enable)
					g.drawImage(MyUtil.images.get(petEnemy.enemyPetImage[petEnemy.enemyPetImageIndex]), // 敌人图片
							petEnemy.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth(),
							petEnemy.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight(),
							MyUtil.enemyPet.getIconWidth(), MyUtil.enemyPet.getIconHeight(),
							MyUtil.enemyPet.getImageObserver());

			}

			if (battleMode.FistLoad)
				g.drawImage(MyUtil.images.get(17), battleMode.pionter2PostionX * MyUtil.iconSingle.getIconWidth(), // 指示符号2
						battleMode.pionter2PostionY * MyUtil.iconSingle.getIconHeight(),
						MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
						MyUtil.iconSingle.getImageObserver());
			else {
				if (!battleMode.CloseBattleMode_Enable)
					g.drawImage(MyUtil.images.get(17), battleMode.pionter1PostionX * MyUtil.iconSingle.getIconWidth(), // 指示符号1
							battleMode.pionter1PostionY * MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
							MyUtil.iconSingle.getImageObserver());
			}
			drawHP(g);

		}

		private void drawBagItem(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyUtil.images.get(48), 0, 0, MyUtil.SCREEN_WIDTH, MyUtil.SCREEN_HEIGHT,
					MyUtil.bagMode.getImageObserver());
			g.setColor(Color.GRAY);
			g.setFont(bagMenuFont);
			g.drawImage(MyUtil.images.get(49),
					BagMode.POINTER_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.POINTER_ITEM_X_OFFSET, // 指示符号1
					BagMode.POINTER_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.POINTER_ITEM_Y_OFFSET
							+ BagMode.pointerChoose * BagMode.NAME_ITEM_INTERVAL_Y_OFFSET,
					MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
					MyUtil.iconSingle.getImageObserver());
			g.drawString("精灵球",
					BagMode.NAME_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.NAME_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET);
			g.drawString("X" + String.valueOf(player.ballNum),
					BagMode.QUANTITY_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.QUANTITY_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET);
			g.drawString("奇异果",
					BagMode.NAME_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.NAME_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET
							+ BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
			g.drawString("X" + String.valueOf(player.amazingFoodNum),
					BagMode.QUANTITY_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.QUANTITY_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET
							+ BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
			g.drawString("精神草",
					BagMode.NAME_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.NAME_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET
							+ 2 * BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);
			g.drawString("X" + String.valueOf(player.grassNum),
					BagMode.QUANTITY_ITEM_POSTION_X * MyUtil.iconSingle.getIconWidth() + BagMode.NAME_ITEM_POSTION_X,
					BagMode.QUANTITY_ITEM_POSTION_Y * MyUtil.iconSingle.getIconHeight() + BagMode.NAME_ITEM_Y_OFFSET
							+ 2 * BagMode.NAME_ITEM_INTERVAL_Y_OFFSET);

		}

		private void drawPetImage(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyUtil.images.get(petEnemy.enemyPetImage[petEnemy.enemyPetImageIndex]), // 敌人图片
					petEnemy.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth(),
					petEnemy.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight(), MyUtil.enemyPet.getIconWidth(),
					MyUtil.enemyPet.getIconHeight(), MyUtil.enemyPet.getImageObserver());
			g.drawImage(MyUtil.images.get(19), // 玩家图片
					petPlayer.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth(),
					petPlayer.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight() + battleMode.PET_PLAYER_OFFSET,
					MyUtil.playerPet.getIconWidth(), MyUtil.playerPet.getIconHeight(),
					MyUtil.playerPet.getImageObserver());
		}

		private void drawHP(Graphics g) {
			// TODO Auto-generated method stub
			g.setColor(petEnemy.colorHp);
			g.fillRoundRect(petEnemy.PostionX_Hp * MyUtil.iconSingle.getIconWidth() - 1,
					(int) (petEnemy.PostionY_Hp) * MyUtil.iconSingle.getIconHeight() - 10,
					(battleMode.LIFE_POINT_WIDTH * MyUtil.iconBitWidth) * petEnemy.lifePoint / petEnemy.lifeFullPoint,
					(int) (2 * MyUtil.iconBitHeight), (int) (0.1 * MyUtil.iconSingle.getIconWidth()),
					(int) (0.1 * MyUtil.iconSingle.getIconHeight())); // 敌人HP

			g.fillRoundRect(petPlayer.PostionX_Hp * MyUtil.iconSingle.getIconWidth(),
					((int) (petPlayer.PostionY_Hp)) * MyUtil.iconSingle.getIconHeight() + 2,
					(battleMode.LIFE_POINT_WIDTH * MyUtil.iconBitWidth) * petPlayer.lifePoint / petPlayer.lifeFullPoint,
					(int) (2 * MyUtil.iconBitHeight), (int) (0.01 * MyUtil.iconSingle.getIconWidth()),
					(int) (0.01 * MyUtil.iconSingle.getIconHeight())); // 玩家HP

		}

		private void drawText(Graphics g) {
			// TODO Auto-generated method stub
			g.setFont(battleParamsFont);
			g.drawString(petEnemy.nameArray[petEnemy.enemyPetImageIndex],
					5 * MyUtil.iconSingle.getIconWidth() + BattleMode.NAME_PET_ENEMY_X_OFFSET,
					1 * MyUtil.iconSingle.getIconHeight() + BattleMode.NAME_PET_ENEMY_Y_OFFSET);
			g.drawString(String.valueOf(petEnemy.level),
					8 * MyUtil.iconSingle.getIconWidth() + BattleMode.LEVEL_PET_ENEMY_X_OFFSET,
					1 * MyUtil.iconSingle.getIconHeight() + BattleMode.LEVEL_PET_ENEMY_Y_OFFSET);
			g.drawString("火鸡战士", 12 * MyUtil.iconSingle.getIconWidth() + BattleMode.NAME_PET_PLAYER_X_OFFSET,
					4 * MyUtil.iconSingle.getIconHeight() + BattleMode.NAME_PET_PLAYER_Y_OFFSET);
			g.drawString(String.valueOf(petPlayer.level),
					15 * MyUtil.iconSingle.getIconWidth() + BattleMode.LEVEL_PET_PLAYER_X_OFFSET,
					4 * MyUtil.iconSingle.getIconHeight() + BattleMode.LEVEL_PET_PLAYER_Y_OFFSET);
			g.drawString(String.valueOf(petPlayer.expFullNum),
					14 * MyUtil.iconSingle.getIconWidth() + BattleMode.EXPFULLNUM_PET_PLAYER_X_OFFSET,
					5 * MyUtil.iconSingle.getIconHeight() + BattleMode.EXPFULLNUM_PET_PLAYER_Y_OFFSET);
			g.drawString(String.valueOf(petPlayer.expNum),
					14 * MyUtil.iconSingle.getIconWidth() + BattleMode.EXPNUM_PET_PLAYER_X_OFFSET,
					5 * MyUtil.iconSingle.getIconHeight() + BattleMode.EXPNUM_PET_PLAYER_Y_OFFSET);
			if (battleMode.CloseBattleMode_Enable) {
				g.setColor(Color.WHITE);
				g.setFont(battleMenuFont);
				if (petEnemy.isDead()) {
					g.drawString("You Win,你获得了" + String.valueOf(petEnemy.level * 10) + "经验值",
							2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					if (petPlayer.expNum > petPlayer.expFullNum)
						g.drawString("你已升到" + String.valueOf(petPlayer.level + 1) + "级",
								2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
								8 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
				} else if (petPlayer.isDead())
					g.drawString("You lose！！！", 2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);

			} else {
				g.setColor(Color.WHITE);
				g.setFont(battleMenuFont);
				if (battleMode.FistLoad) {
					g.drawString("战斗", 13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("背包", 16 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("怪兽", 13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							8 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("逃跑", 16 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							8 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("你想干啥？", 2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
				} else if (battleMode.isFighting) {
					g.drawString("攻击", 2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("治疗", 8 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("防御", 2 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							8 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					g.drawString("自爆", 8 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_X_OFFSET,
							8 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					if (battleMode.pionter1PostionX == 2 && battleMode.pionter1PostionY == 7)
						g.drawString("伤害值：" + petPlayer.harmPoint,
								13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_MESSAGE_X_OFFSET,
								7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 8
							&& battleMode.pionter1PostionY == 7)
						g.drawString("本回合补充20HP",
								13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_MESSAGE_X_OFFSET,
								7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 2
							&& battleMode.pionter1PostionY == 8)
						g.drawString("本回合对方攻击无效",
								13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_MESSAGE_X_OFFSET,
								7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
					else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 8
							&& battleMode.pionter1PostionY == 8)
						g.drawString("双方同归于尽", 13 * MyUtil.iconSingle.getIconWidth() + BattleMode.MENU_MESSAGE_X_OFFSET,
								7 * MyUtil.iconSingle.getIconHeight() + BattleMode.MENU_Y_OFFSET);
				}
			}
		}

		/*
		 * 攻击特效显示
		 */
		public void attackEffect(Graphics g) {
			if (petPlayer.myTurn) {
				if (!petPlayer.isProtect())
					g.drawImage(MyUtil.images.get(petEnemy.enemyPetImage[petEnemy.enemyPetImageIndex]), // 敌人图片
							petEnemy.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth() + (MyUtil.loopNum - 25),
							petEnemy.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight(),
							MyUtil.enemyPet.getIconWidth(), MyUtil.enemyPet.getIconHeight(),
							MyUtil.enemyPet.getImageObserver());
				else
					g.drawImage(MyUtil.images.get(petEnemy.enemyPetImage[petEnemy.enemyPetImageIndex]), // 敌人图片
							petEnemy.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth(),
							petEnemy.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight(),
							MyUtil.enemyPet.getIconWidth(), MyUtil.enemyPet.getIconHeight(),
							MyUtil.enemyPet.getImageObserver());

			} else {
				g.drawImage(MyUtil.images.get(19), // 玩家图片
						petPlayer.PostionX_AttackMode * MyUtil.iconSingle.getIconWidth() + (MyUtil.loopNum - 25),
						petPlayer.PostionY_AttackMode * MyUtil.iconSingle.getIconHeight()
								+ battleMode.PET_PLAYER_OFFSET,
						MyUtil.playerPet.getIconWidth(), MyUtil.playerPet.getIconHeight(),
						MyUtil.playerPet.getImageObserver());
			}

		}

		public void walkEffect(Graphics g, int i) {
			switch (discoveryMode.playerDirection) {
			case 1:
				g.drawImage(MyUtil.images.get(player.imageIndex[player.index]),
						(i % 20) * MyUtil.iconSingle.getIconWidth() - (MyUtil.bitLength),
						(i / 20) * MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getIconWidth(),
						MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());
				break;

			case 2:
				g.drawImage(MyUtil.images.get(player.imageIndex[player.index]),
						(i % 20) * MyUtil.iconSingle.getIconWidth() + (MyUtil.bitLength),
						(i / 20) * MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getIconWidth(),
						MyUtil.iconSingle.getIconHeight(), MyUtil.iconSingle.getImageObserver());
				break;
			case 3:
				g.drawImage(MyUtil.images.get(player.imageIndex[player.index]),
						(i % 20) * MyUtil.iconSingle.getIconWidth(),
						(i / 20) * MyUtil.iconSingle.getIconHeight() + MyUtil.bitLength,
						MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
						MyUtil.iconSingle.getImageObserver());
				break;
			case 4:
				g.drawImage(MyUtil.images.get(player.imageIndex[player.index]),
						(i % 20) * MyUtil.iconSingle.getIconWidth(),
						(i / 20) * MyUtil.iconSingle.getIconHeight() - MyUtil.bitLength,
						MyUtil.iconSingle.getIconWidth(), MyUtil.iconSingle.getIconHeight(),
						MyUtil.iconSingle.getImageObserver());
				break;
			default:
				break;
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (MyUtil.ACTTACK_MODE && !petPlayer.myTurn)
					;
				else
					up();
				break;
			case KeyEvent.VK_DOWN:
				if (MyUtil.ACTTACK_MODE && !petPlayer.myTurn)
					;
				else
					down();
				break;
			case KeyEvent.VK_LEFT:
				if (MyUtil.ACTTACK_MODE && !petPlayer.myTurn)
					;
				else
					left();
				break;
			case KeyEvent.VK_RIGHT:
				if (MyUtil.ACTTACK_MODE && !petPlayer.myTurn)
					;
				else
					right();
				break;
			case KeyEvent.VK_Z:
				if (MyUtil.isBagMode) {
					bagModeHandle();
				} else if (MyUtil.ACTTACK_MODE) {
					battleModeHandle();
				} else if (MyUtil.isBussinessMode) {
					bussinessModeHandle();
				} else if (MyUtil.isDoctorMode) {
					MyUtil.isDoctorMode = false;
				} else if (MyUtil.isEnemyMode) {
					MyUtil.isEnemyMode = false;
				} else {
					discoveryModeHandle();
				}
				break;
			case KeyEvent.VK_X:
				if (MyUtil.ACTTACK_MODE && MyUtil.isBagMode)
					MyUtil.isBagMode = false;
				if (!MyUtil.ACTTACK_MODE && MyUtil.isBussinessMode) {
					MyUtil.isBussinessMode = false;
					validate();
					update(getGraphics());
				}
				if (MyUtil.ACTTACK_MODE && !petPlayer.myTurn)
					;
				else {
					battleMode.FistLoad = true;
					battleMode.isFighting = false;
				}
				break;
			case KeyEvent.VK_A:
				if (MyUtil.isBagMode) { // 切换出背包界面
					MyUtil.isBagMode = false;
					validate();
					update(getGraphics());
				} else if (!MyUtil.ACTTACK_MODE) {
					MyUtil.isBagMode = true;
					validate();
					update(getGraphics());
				}
				break;
			default:
				break;
			}
		}

		/*
		 * 探索界面确定键（z）键的处理过程
		 */
		private void discoveryModeHandle() {
			// TODO Auto-generated method stub
			/*
			 * 非战斗模式下确定键 可用于对话、查找背包
			 */
			if ((player.postionX + player.postionY * 20) == (doctor.postionX + doctor.postionY * 20 - 1)
					|| (player.postionX + player.postionY * 20) == (doctor.postionX + doctor.postionY * 20 + 1)
					|| (player.postionX + player.postionY * 20) == (doctor.postionX + (doctor.postionY + 1) * 20)
					|| (player.postionX + player.postionY * 20) == (doctor.postionX + (doctor.postionY - 1) * 20))// 触发医生界面
			{
				// 治疗玩家宠物，将玩家宠物lifePoint变为100
				doctor.aidPets(petPlayer);
				MyUtil.isDoctorMode = true;
				validate();
				update(getGraphics());
			} else if ((player.postionX + player.postionY * 20) == (enemy.postionX + enemy.postionY * 20 - 1)
					|| (player.postionX + player.postionY * 20) == (enemy.postionX + enemy.postionY * 20 + 1)
					|| (player.postionX + player.postionY * 20) == (enemy.postionX + (enemy.postionY + 1) * 20)
					|| (player.postionX + player.postionY * 20) == (enemy.postionX + (enemy.postionY - 1) * 20)) // 触发对手界面
			{
				// 进入对战模式
				MyUtil.isEnemyMode = true;
				validate();
				update(getGraphics());
			} else if ((player.postionX + player.postionY * 20) == (bussinessman.postionX + bussinessman.postionY * 20
					- 1)
					|| (player.postionX + player.postionY * 20) == (bussinessman.postionX + bussinessman.postionY * 20
							+ 1)
					|| (player.postionX + player.postionY * 20) == (bussinessman.postionX
							+ (bussinessman.postionY + 1) * 20)
					|| (player.postionX + player.postionY * 20) == (bussinessman.postionX
							+ (bussinessman.postionY - 1) * 20)) // 触发商人界面
			{
				// 进入买卖模式
				// System.out.println("进入买卖");
				MyUtil.isBussinessMode = true;
				validate();
				update(getGraphics());
			}
		}

		private void bussinessModeHandle() {
			// TODO Auto-generated method stub
			switch (BussinessMode.pointerChoose) {
			case 0:
				// add精灵球
				if (player.ballNum > 0) {
					player.ballNum++;
					System.out.println("add精灵球");
				}
				break;
			case 1:
				// add奇异果
				if (player.amazingFoodNum > 0) {
					System.out.println("add奇异果");
					player.amazingFoodNum++;
				}
				break;
			case 2:
				// add精神草
				if (player.grassNum > 0) {
					System.out.println("add精神草");
					player.grassNum++;
				}
				break;
			default:
				validate();
				update(getGraphics());
				break;
			}
		}

		/*
		 * 战斗界面确定键（z）的处理过程
		 */
		private void battleModeHandle() {
			// TODO Auto-generated method stub
			if (battleMode.isFighting) {
				petPlayer.resetDefense();
				if (!battleMode.FistLoad && battleMode.pionter1PostionX == 2 && battleMode.pionter1PostionY == 7) { // 玩家攻击敌人
					petEnemy.wasHitted(petPlayer);
					MyUtil.startAttackEffect = true;
					for (int j = 0; j < BATTLE_LOOP_TIMES; j++) {
						for (int i = 0; i < BATTLE_FRAMES; i++) {
							MyUtil.loopNum = i;
							validate();
							update(getGraphics());
							try {
								Thread.sleep(BATTLE_TIME);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					MyUtil.startAttackEffect = false;
				}

				else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 8 && battleMode.pionter1PostionY == 7) { // 治愈
					petPlayer.cureSelf();
				}

				else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 2 && battleMode.pionter1PostionY == 8) { // 防御
					petPlayer.defense();
				}

				else if (!battleMode.FistLoad && battleMode.pionter1PostionX == 8 && battleMode.pionter1PostionY == 8) { // 同归于尽
					petPlayer.deadTogether(petEnemy);
				}

				if (!petEnemy.isDead()) {
					petEnemy.myTurn = true;
					petPlayer.myTurn = false;
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					MyUtil.startAttackEffect = true;
					for (int j = 0; j < BATTLE_LOOP_TIMES; j++) {
						for (int i = 0; i < BATTLE_FRAMES; i++) {
							MyUtil.loopNum = i;
							validate();
							update(getGraphics());
							try {
								Thread.sleep(BATTLE_TIME);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					MyUtil.startAttackEffect = false;

					petPlayer.wasHitted(petEnemy); // 敌人攻击玩家
					petEnemy.myTurn = false;
					petPlayer.myTurn = true;
					repaint();

				}
				if ((petEnemy.isDead() || petPlayer.isDead()) && battleMode.CloseBattleMode_Enable) {
					MyUtil.ACTTACK_MODE = false;
					battleMode.FistLoad = true;
					battleMode.isFighting = false;
					battleMode.CloseBattleMode_Enable = false;
					petEnemy.resetEnemy();
					battleMode.resetPointer();
					repaint();
				}

			} else if (battleMode.pionter2PostionX == 16 && battleMode.pionter2PostionY == 8) { // 逃命
				MyUtil.ACTTACK_MODE = false;
				battleMode.FistLoad = true;
				battleMode.isFighting = false;
				petEnemy.resetEnemy();
				battleMode.resetPointer();
				repaint();
			} else if (battleMode.pionter2PostionX == 13 && battleMode.pionter2PostionY == 7) { // 进入攻击栏
				battleMode.isFighting = true;
				battleMode.FistLoad = false;
				repaint();
			} else if (battleMode.pionter2PostionX == 16 && battleMode.pionter2PostionY == 7) {
				MyUtil.isBagMode = true;
			} else {
				/*
				 * 背包和切换宠物两个选项 分别需要两个else if来判断
				 */
			}
			if (petEnemy.isDead() || petPlayer.isDead()) {
				battleMode.CloseBattleMode_Enable = true;
				if (petEnemy.isDead()) {
					petPlayer.expNumUp(petEnemy);
				}
				validate();
				update(getGraphics());
			}
		}

		private void bagModeHandle() {
			switch (BagMode.pointerChoose) {
			case 0:
				// 使用精灵球
				if (player.ballNum > 0) {
					player.ballNum--;
					
					/*
					 * 未定义精灵球使用方式
					 */
					
					System.out.println("使用精灵球");
				}
				break;
			case 1:
				// 使用奇异果
				if (player.amazingFoodNum > 0) {
					player.amazingFoodNum--;
					petPlayer.level++;
				}
				break;
			case 2:
				// 使用精神草
				if (player.grassNum > 0) {
					player.grassNum--;
					petPlayer.lifePoint = petPlayer.lifeFullPoint;
				}
				break;
			default:
				validate();
				update(getGraphics());
				break;
			}
		}

		private void right() {
			// TODO Auto-generated method stub
			if (MyUtil.isBussinessMode) {
				if (BussinessMode.pointerChoose < 2) {
					BussinessMode.pointerChoose++;
					validate();
					update(getGraphics());
				}
			} else {
				if (!MyUtil.ACTTACK_MODE) {
					if (player.imageIndex[player.directionIndex] != Player.DIERCTION_RIGHT) {
						player.setIndex(Player.DIERCTION_RIGHT);
						player.setDirectionIndex(Player.DIERCTION_RIGHT);
						discoveryMode.playerDirection = player.directionIndex;
					} else {
						if (player.postionX < 19 && assetImage[((player.postionX + 1) + (player.postionY * 20))] == 0) {
							discoveryMode.playerDirection = player.directionIndex;
							discoveryMode.isMoving = true;
							MyUtil.startWalkEffect = true;
							for (int i = 0; i < WALK_FRAMES; i++) {
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								// MyUtil.loopNum = i;
								MyUtil.bitLength = i;
								player.setIndex(32);
								validate();
								update(getGraphics());
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								player.setIndex(39);
								validate();
								update(getGraphics());
							}
							MyUtil.startWalkEffect = false;
							player.postionX++;
							discoveryMode.isMoving = false;
						}
					}
				} else {
					if (battleMode.isFighting && !battleMode.FistLoad) {
						if (battleMode.pionter1PostionX != 8)
							if (battleMode.pionter1PostionY != 8)
								battleMode.setPionter1Postion(2);
							else
								battleMode.setPionter1Postion(4);
					} else {
						if (battleMode.pionter2PostionX != 16)
							if (battleMode.pionter2PostionY != 8)
								battleMode.setPionter2Postion(2);
							else
								battleMode.setPionter2Postion(4);
					}
				}
				repaint();
			}
		}

		private void left() {
			// TODO Auto-generated method stub
			if (MyUtil.isBussinessMode) {
				if (BussinessMode.pointerChoose > 0) {
					BussinessMode.pointerChoose--;
					validate();
					update(getGraphics());
				}
			} else {
				if (!MyUtil.ACTTACK_MODE) {
					if (player.imageIndex[player.directionIndex] != Player.DIERCTION_LEFT) {
						player.setIndex(Player.DIERCTION_LEFT);
						player.setDirectionIndex(Player.DIERCTION_LEFT);
						;
						discoveryMode.playerDirection = player.directionIndex;
					} else {
						if (player.postionX > 0 && assetImage[((player.postionX - 1) + (player.postionY * 20))] == 0) {
							discoveryMode.playerDirection = player.directionIndex;
							discoveryMode.isMoving = true;
							MyUtil.startWalkEffect = true;
							for (int i = 0; i < WALK_FRAMES; i++) {
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								MyUtil.loopNum = i;
								MyUtil.bitLength = i;
								player.setIndex(37);
								validate();
								update(getGraphics());
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								player.setIndex(38);
								validate();
								update(getGraphics());
							}
							MyUtil.startWalkEffect = false;
							player.postionX--;
							discoveryMode.isMoving = false;
						}
					}
				} else {
					if (battleMode.isFighting && !battleMode.FistLoad) {
						if (battleMode.pionter1PostionX != 2) {
							if (battleMode.pionter1PostionY != 8)
								battleMode.setPionter1Postion(1);
							else
								battleMode.setPionter1Postion(3);
						}
					} else {
						if (battleMode.pionter2PostionX != 13) {
							if (battleMode.pionter2PostionY != 8)
								battleMode.setPionter2Postion(1);
							else
								battleMode.setPionter2Postion(3);
						}
					}
				}
				repaint();
			}
		}

		private void down() {
			// TODO Auto-generated method stub
			if (MyUtil.isBagMode) {
				if (BagMode.pointerChoose < 2) {
					BagMode.pointerChoose++;
					validate();
					update(getGraphics());
				}
			} else {
				if (!MyUtil.ACTTACK_MODE) {
					if (player.imageIndex[player.directionIndex] != Player.DIERCTION_DOWN) {
						player.setIndex(Player.DIERCTION_DOWN);
						player.setDirectionIndex(Player.DIERCTION_DOWN);
						;
						discoveryMode.playerDirection = player.directionIndex;
					} else {
						if (player.postionY < 9 && assetImage[(player.postionX + ((player.postionY + 1) * 20))] == 0) {
							discoveryMode.playerDirection = player.directionIndex;
							discoveryMode.isMoving = true;
							MyUtil.startWalkEffect = true;
							for (int i = 0; i < WALK_FRAMES; i++) {
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								MyUtil.loopNum = i;
								MyUtil.bitLength = i;
								player.setIndex(33);
								validate();
								update(getGraphics());
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								player.setIndex(34);
								validate();
								update(getGraphics());
							}
							MyUtil.startWalkEffect = false;
							player.postionY++;
							discoveryMode.isMoving = false;
						}
					}
				} else {
					if (battleMode.isFighting && !battleMode.FistLoad) {
						if (battleMode.pionter1PostionY != 8) {
							if (battleMode.pionter1PostionX != 8)
								battleMode.setPionter1Postion(3);
							else
								battleMode.setPionter1Postion(4);
						}
					} else {
						if (battleMode.pionter2PostionY != 8)
							if (battleMode.pionter2PostionX != 16)
								battleMode.setPionter2Postion(3);
							else
								battleMode.setPionter2Postion(4);
					}
				}
				repaint();
			}
		}

		private void up() {
			// TODO Auto-generated method stub
			if (MyUtil.isBagMode) {
				if (BagMode.pointerChoose > 0) {
					BagMode.pointerChoose--;
					validate();
					update(getGraphics());
				}
			} else {
				if (!MyUtil.ACTTACK_MODE) {
					if (player.imageIndex[player.directionIndex] != Player.DIERCTION_UP) {
						player.setIndex(Player.DIERCTION_UP);
						player.setDirectionIndex(Player.DIERCTION_UP);
						;
						discoveryMode.playerDirection = player.directionIndex;
					} else {
						if (player.postionY > 0 && assetImage[(player.postionX + ((player.postionY - 1) * 20))] == 0) {
							discoveryMode.playerDirection = player.directionIndex;
							discoveryMode.isMoving = true;
							MyUtil.startWalkEffect = true;
							for (int i = 0; i < WALK_FRAMES; i++) {
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								MyUtil.loopNum = i;
								MyUtil.bitLength = i;
								player.setIndex(35);
								validate();
								update(getGraphics());
								try {
									Thread.sleep(WALK_TIME / 2);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								player.setIndex(36);
								validate();
								update(getGraphics());
							}
							MyUtil.startWalkEffect = false;
							player.postionY--;
							discoveryMode.isMoving = false;
						}
					}
				} else {
					if (battleMode.isFighting && !battleMode.FistLoad) {
						if (battleMode.pionter1PostionY != 7) {
							if (battleMode.pionter1PostionX != 8)
								battleMode.setPionter1Postion(1);
							else
								battleMode.setPionter1Postion(2);
						}
					} else {
						if (battleMode.pionter2PostionY != 7)
							if (battleMode.pionter2PostionX != 16)
								battleMode.setPionter2Postion(1);
							else
								battleMode.setPionter2Postion(2);
					}
				}
				repaint();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		class MyTask extends TimerTask {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!MyUtil.ACTTACK_MODE && discoveryMode.isMoving) {
					MyUtil.ACTTACK_MODE = true;
					// System.out.println("ready");
					repaint();
				}
			}
		}

		public class PulseSignalTask extends TimerTask {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				doctor.index = (int) (Math.random() * 1000 % 2);
				bussinessman.index = (int) (Math.random() * 1000 % 3);
				enemy.index = (int) (Math.random() * 1000 % 3);
				if (!MyUtil.ACTTACK_MODE) {
					petEnemy.level = petPlayer.level + (int) ((Math.random() * 1000) % 10) - 5;
					petEnemy.enemyPetImageIndex = (int) ((Math.random() * 1000) % 8);
					petEnemy.refreshParameter();
					petPlayer.refreshParameter();
					battleMode.CloseBattleMode_Enable = MyUtil.ACTTACK_MODE;
				}
				validate();
				update(getGraphics());
			}

		}
	}

	public static void main(String[] args) {
		FrameView frame = new FrameView();
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu game = new JMenu("游戏");
		JMenuItem start = game.add("开始");
		JMenu help = new JMenu("帮助");
		JMenuItem about = help.add("关于我");
		menuBar.add(game);
		menuBar.add(help);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(645, 373);
		frame.setTitle("RPG游戏--口袋妖怪");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
