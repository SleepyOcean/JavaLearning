package com.sleepy.manager;

import java.sql.SQLException;
import java.util.Random;

public class DataAlterM  implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			synchronized (SqlManager.getInstance()) {
				int num = new Random().nextInt(100);
//				SqlManager.getInstance().updateCharDataByID("2", "quantity", String.valueOf(num));
				System.out.println("data changed: " + SqlManager.getInstance().getColumnValue("quantity", "id", "2"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
