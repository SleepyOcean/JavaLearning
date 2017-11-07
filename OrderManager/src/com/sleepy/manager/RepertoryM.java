package com.sleepy.manager;

import java.sql.SQLException;

public class RepertoryM {
	private static RepertoryM instance = null;
	
	public static Object[][] data ;
	public static Object[] names = SqlManager.getInstance().getColumnNames();
	
	public static RepertoryM getInstance() {
		if (instance == null) {
			instance = new RepertoryM();
		}
		return instance;
	}
	
	public void updateData() {
		try {
			data = SqlManager.getInstance().getData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void queryData(String name,String value) {
		try {
			data = SqlManager.getInstance().queryData(name, value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}