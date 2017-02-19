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
	public static int bitLength = 0;				//��·����ƫ����

	public static int SCREEN_WIDTH = 640;
	public static int SCREEN_HEIGHT = 320;

	public static void SetImageList() {
		images.add(iconSingle.getImage()); // 0 ��
		images.add(new ImageIcon("res/poor1.png").getImage()); // 1 ˮ��1
		images.add(new ImageIcon("res/poor2.png").getImage()); // 2 ˮ��2
		images.add(new ImageIcon("res/poor3.png").getImage()); // 3 ˮ��3
		images.add(new ImageIcon("res/poor4.png").getImage()); // 4 ˮ��4
		images.add(new ImageIcon("res/poor5.png").getImage()); // 5 ˮ��5
		images.add(new ImageIcon("res/poor6.png").getImage()); // 6 ˮ��6
		images.add(new ImageIcon("res/poor7.png").getImage()); // 7 ˮ��7
		images.add(new ImageIcon("res/poor8.png").getImage()); // 8 ˮ��8
		images.add(new ImageIcon("res/poor9.png").getImage()); // 9 ˮ��9
		images.add(iconBigTree.getImage()); // 10 ����
		images.add(new ImageIcon("res/tree.png").getImage()); // 11 С��
		images.add(new ImageIcon("res/stone.png").getImage()); // 12 ʯͷ
		images.add(new ImageIcon("res/player1.png").getImage()); // 13 ���
		images.add(new ImageIcon("res/floor.png").getImage()); // 14 С��
		images.add(battleMap.getImage()); // 15 ս����ͼ
		images.add(battleActtackModeMap.getImage()); // 16 ս����ͼս��ģʽ
		images.add(new ImageIcon("res/pionter.png").getImage()); // 17 ָʾ����
		images.add(enemyPet.getImage()); // 18 ս���ез�����
		images.add(playerPet.getImage()); // 19 ս������ҳ���
		// images.add(playerPet1.getImage()); // 19 ս������ҳ���
		images.add(new ImageIcon("res/doctor1.png").getImage()); // 20
																	// ��ͼ�ϵ�ҽ��--״̬1
		images.add(new ImageIcon("res/doctor2.png").getImage()); // 21
																	// ��ͼ�ϵ�ҽ��--״̬2
		images.add(new ImageIcon("res/business1.png").getImage()); // 22
																	// ��ͼ�ϵ�����--״̬1
		images.add(new ImageIcon("res/business2.png").getImage()); // 23
																	// ��ͼ�ϵ�����--״̬2
		images.add(new ImageIcon("res/business3.png").getImage()); // 24
																	// ��ͼ�ϵ�����--״̬3
		images.add(new ImageIcon("res/enemy1.png").getImage()); // 25
																// ��ͼ�ϵĶ���--״̬1
		images.add(new ImageIcon("res/enemy2.png").getImage()); // 26
																// ��ͼ�ϵĶ���--״̬2
		images.add(new ImageIcon("res/enemy3.png").getImage()); // 27
																// ��ͼ�ϵĶ���--״̬3
		images.add(new ImageIcon("res/player1 (1).png").getImage());// 28
																	// ���--״̬--��ֹ����
		images.add(new ImageIcon("res/player1 (2).png").getImage());// 29
																	// ���--״̬--��ֹ����
		images.add(new ImageIcon("res/player1 (3).png").getImage());// 30
																	// ���--״̬--��ֹ��ǰ
		images.add(new ImageIcon("res/player1 (4).png").getImage());// 31
																	// ���--״̬--��ֹ���
		images.add(new ImageIcon("res/player2 (1).png").getImage());// 32
																	// ���--״̬--��������--���
		images.add(new ImageIcon("res/player2 (2).png").getImage());// 33
																	// ���--״̬--������ǰ--���
		images.add(new ImageIcon("res/player2 (3).png").getImage());// 34
																	// ���--״̬--������ǰ--�ҽ�
		images.add(new ImageIcon("res/player2 (4).png").getImage());// 35
																	// ���--״̬--�������--�ҽ�
		images.add(new ImageIcon("res/player2 (5).png").getImage());// 36
																	// ���--״̬--�������--���
		images.add(new ImageIcon("res/player2 (6).png").getImage());// 37
																	// ���--״̬--��������--�ҽ�
		images.add(new ImageIcon("res/player2 (7).png").getImage());// 38
																	// ���--״̬--��������--���
		images.add(new ImageIcon("res/player2 (8).png").getImage());// 39
																	// ���--״̬--��������--�ҽ�
		images.add(new ImageIcon("res/386-�ϰ���˹.png").getImage()); // 40 ս���ез�����
		images.add(new ImageIcon("res/146-������.png").getImage()); // 41 ս���ез�����
		images.add(new ImageIcon("res/151-�λ�.png").getImage()); // 42 ս���ез�����
		images.add(new ImageIcon("res/157-������.png").getImage()); // 43 ս���ез�����
		images.add(new ImageIcon("res/244-�׵�.png").getImage()); // 44 ս���ез�����
		images.add(new ImageIcon("res/245-ˮ��.png").getImage()); // 45 ս���ез�����
		images.add(new ImageIcon("res/243-�׻�.png").getImage()); // 46 ս���ез�����
		images.add(new ImageIcon("res/006-�����.png").getImage()); // 47 ս���ез�����
		images.add(bagMode.getImage()); // 48 ս���ез�����
		images.add(new ImageIcon("res/pionter1.png").getImage()); // 49 ָʾ����
		images.add(dialogueBox.getImage()); // 50 �Ի���
	}
}
