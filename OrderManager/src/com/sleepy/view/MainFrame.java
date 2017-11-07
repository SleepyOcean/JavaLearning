package com.sleepy.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sleepy.main.Test;
import com.sleepy.sql.DBOperator;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static DBOperator operator = DBOperator.getInstance();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// new Test().test();
					operator.connectDB();
					operator.useDB();
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					operator.disconnectDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MainFrame() throws SQLException {
		setFont(new Font("Consolas", Font.PLAIN, 16));
		setBackground(Color.WHITE);
		setTitle("订单管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 546);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("新建");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("打开");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("设置");
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("编辑");
		menuBar.add(mnNewMenu_1);

		JMenuItem menuItem = new JMenuItem("撤销");
		mnNewMenu_1.add(menuItem);

		JMenuItem menuItem_0 = new JMenuItem("重做");
		mnNewMenu_1.add(menuItem_0);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("查找");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("修改");
		mntmNewMenuItem_4.setSelected(true);
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("关于");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("使用帮助");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("检查更新");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("关于我……");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(DBOperator.getInstance().getAllDataInTheTable(),
				DBOperator.getInstance().getColumnNameList());
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnNewButton = new JButton("更新订单数据");
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("修改");
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		panel.add(btnNewButton_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("命令：");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("执行");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command = textField.getText().trim();
				try {
					operator.queryForResult(command);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(btnNewButton_4);
		
	}

}
