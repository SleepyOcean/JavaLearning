package com.sleepy.sql;

public interface SqlCommandFactory {
	
	public String selectCommand();
	public String showCommand();
	public String createCommand(String dbName);
	public String createCommand(String tableName,String[][] properties);
	public String dropDBCommand(String dbName);
	public String dropTableCommand(String tableName);
	public String deleteCommand(String tableName,String[][] conditions);
	public String useDBCommand(String dbName);
	public String altCommand(String tableName,String name,String type);
	public String altCommand(String tableName,String primaryKey,boolean isAdd);
	public String insertCommand(String tableName,String[] fields,String[] values);
	public String insertCommand(String tableName,String[] values);
	public String updateCommand();
	public String condition();
//	public String Command();
	
	

}
