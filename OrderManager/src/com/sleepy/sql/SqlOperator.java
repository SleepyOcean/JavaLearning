package com.sleepy.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlOperator {

	Connection connection = null;
	Statement statement = null;

	public abstract void connectDB();

	public void disconnectDB() throws SQLException {
		statement.close();
		connection.close();
//		System.out.println("disconnected..."+this.getClass().getName());
	}

	public abstract boolean useDB(String dbName) throws SQLException;
	
	public abstract Object[][] queryForResult(String sql) throws SQLException;

	public abstract boolean checkDBExist(String dbName) throws SQLException;

	public abstract boolean checkTableExist(String tableName, String dbName) throws SQLException;

	public abstract boolean checkItemExist(String tableName,String itemName, String itemValue) throws SQLException;

	public abstract void createDatabase(String dbName) throws SQLException;

	public abstract void createTable(String tableName, String primaryKey, String[] args) throws SQLException;

	public abstract void addColumn(String tableName,String name) throws SQLException;

	public abstract void addData(String tableName,String primaryKey,String[] args) throws SQLException;

	public abstract void updateDataByPrimaryKey(String tableName, String primaryKey, String primaryKeyValue,String name, String value) throws SQLException;

	public abstract List<String> serach(String tableName,String condition) throws SQLException;
	
	public abstract void deleteItemByPrimaryKey(String tableName,String primaryKey,String primaryKeyValue) throws SQLException;

	public abstract boolean dropTable(String tableName,String dbName)throws SQLException;
	
	public abstract boolean dropDatabase(String dbName) throws SQLException;
	
	public abstract List<String> getDatabases() throws SQLException;
	
	public abstract List<String> getTables(String dbName) throws SQLException;
	
	public abstract String[] getColumnNameList(String tableName) throws SQLException;
	
	public abstract Object[][] getAllDataInTheTable(String tableName) throws SQLException;
}
