package com.sleepy.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBOperator extends MysqlOperator {
	String dbName;
	String tableName;
	String primaryKey;

	public DBOperator() {
		dbName = "goodsrepo";
		tableName = "goods";
		primaryKey = "id";
	}
	
	private static DBOperator instance = null;
	public static DBOperator getInstance() {
		if(instance==null)
			instance = new DBOperator();
		return instance;
	}

	public boolean useDB() throws SQLException {
		return super.useDB(dbName);
	}
	
	@Override
	public void createDatabase(String dbName) throws SQLException {
		if (checkDBExist(dbName))
			return;
		super.createDatabase(dbName);
		this.dbName = dbName;
	}

	@Override
	public void createTable(String tableName, String primaryKey, String[] args) throws SQLException {
		if (checkTableExist(tableName))
			return;
		super.createTable(tableName, primaryKey, args);
		this.tableName = tableName;
		this.primaryKey = primaryKey;
	}

	public boolean checkTableExist(String tableName) throws SQLException {
		return super.checkTableExist(tableName, dbName);
	}

	public boolean checkItemExist(String itemName, String itemValue) throws SQLException {
		return super.checkItemExist(tableName, itemName, itemValue);
	}

	public void addColumn(String name) throws SQLException {
		super.addColumn(tableName, name);
	}

	public void addData(String[] args) throws SQLException {
		super.addData(tableName, primaryKey, args);
	}

	public void updateDataByPrimaryKey(String primaryKeyValue, String name, String value) throws SQLException {
		super.updateDataByPrimaryKey(tableName, primaryKey, primaryKeyValue, name, value);
	}

	public void deleteItemByPrimaryKey(String primaryKeyValue) throws SQLException {
		super.deleteItemByPrimaryKey(tableName, primaryKey, primaryKeyValue);
	}
	
	public List<String> serach(String condition) throws SQLException {
		return super.serach(tableName, condition);
	}

	public List<String> getTables() throws SQLException {
		return super.getTables(dbName);
	}
	public void displayData() throws SQLException {
		String queryStr = "SELECT * FROM " + tableName + ";";
		String[] columnIndex = getColumnNameList(tableName);

		ResultSet resultSet = statement.executeQuery(queryStr);
		while (resultSet.next()) {
			for (int i = 0; i < columnIndex.length; i++) {
				System.out.print(columnIndex[i] + ": " + resultSet.getString(columnIndex[i]) + "\t");
			}
			System.out.println();
		}
	}
	
	
	public String[] getColumnNameList() throws SQLException {
		return super.getColumnNameList(tableName);
	}

	public Object[][] getAllDataInTheTable() throws SQLException {
		return super.getAllDataInTheTable(tableName);
	}
	
	
}
