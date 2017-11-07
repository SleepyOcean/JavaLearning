package com.sleepy.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlOperator extends SqlOperator {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_URL_PROPERTIES = "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";

	static final String USER = "root";
	static final String PASS = "kb123789";

	@Override
	public void connectDB() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL + DB_URL_PROPERTIES, USER, PASS);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("connect successful..." + this.getClass().getName());
	}

	@Override
	public boolean useDB(String dbName) throws SQLException {
		if (!checkDBExist(dbName)) {
			return false;
		}
		String useDBQuery = "USE " + dbName + ";";
		statement.execute(useDBQuery);
		return true;
	}

	@Override
	public Object[][] queryForResult(String sql) throws SQLException {
		int endIndex = sql.indexOf(" ");
		String operator = sql.substring(0, endIndex);
		switch (operator) {
		case "SELECT" :
		case "select" :
			
			break;
//		case "SELECT" :
//		case "select" :
//			
//			break;

		default:
			break;
		}
		
		
		
		
		
		return null;
	}
	
	@Override
	public boolean checkDBExist(String dbName) throws SQLException {
		String checkExist = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME='" + dbName + "';";
		String databaseName = null;
		ResultSet resultSet;
		resultSet = statement.executeQuery(checkExist);
		while (resultSet.next()) {
			databaseName = resultSet.getString("SCHEMA_NAME");
		}
		if (dbName.equals(databaseName)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkTableExist(String tableName, String dbName) throws SQLException {
		String checkExist = "SELECT DISTINCT t.table_name, n.SCHEMA_NAME FROM information_schema.TABLES t, information_schema.SCHEMATA n WHERE t.table_name = '"
				+ tableName + "' AND n.SCHEMA_NAME = '" + dbName + "';";
		String name = null;
		ResultSet resultSet = statement.executeQuery(checkExist);
		while (resultSet.next()) {
			name = resultSet.getString("TABLE_NAME");
		}

		if (tableName.equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkItemExist(String tableName, String itemName, String itemValue) throws SQLException {
		String query = "SELECT " + itemName + " FROM " + tableName + " WHERE " + itemName + "='" + itemValue + "';";
		String result = null;
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			result = resultSet.getString(itemName);
		}
		if (itemValue.equals(result)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void createDatabase(String dbName) throws SQLException {
		String createDBQuery = "CREATE DATABASE " + dbName + ";";
		statement.execute(createDBQuery);
	}

	@Override
	public void createTable(String tableName, String primaryKey, String[] args) throws SQLException {
		String createTableQuery = "CREATE TABLE " + tableName + "(";
		int i = 0;
		while (i == args.length - 1) {
			createTableQuery += args[i++] + " " + args[i++] + " NOT NULL,";
		}
		createTableQuery += "PRIMARY KEY(" + primaryKey + ")" + ");";
		statement.execute(createTableQuery);
	}

	@Override
	public void addColumn(String tableName, String name) throws SQLException {
		String alterQ = "ALTER TABLE " + tableName + " ADD COLUMN " + name + ";";
		statement.execute(alterQ);
	}

	@Override
	public void addData(String tableName, String primaryKey, String[] args) throws SQLException {
		if (!checkItemExist(tableName, primaryKey, args[0])) {
			String insertQ = "INSERT INTO " + tableName + " VALUES('";
			for (int i = 0; i < args.length - 1; i++) {
				insertQ += args[i] + "','";
			}
			insertQ += args[args.length - 1] + "');";
			statement.executeUpdate(insertQ);
		} else {
			System.out.println("ID already exist!");
		}
	}

	@Override
	public void updateDataByPrimaryKey(String tableName, String primaryKey, String primaryKeyValue, String name,
			String value) throws SQLException {
		String updateQ = "UPDATE " + tableName + " SET " + name + "='" + value + "' WHERE " + primaryKey + "="
				+ primaryKeyValue + ";";
		statement.executeUpdate(updateQ);
	}

	@Override
	public List<String> serach(String tableName, String condition) throws SQLException {
		String query = "SELECT * FROM " + tableName;
		if (condition == null) {
			query += ";";
		} else {
			query += " WHERE " + condition + ";";
		}
		String[] columnIndex = getColumnNameList(tableName);
		List<String> result = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			for (int i = 0; i < columnIndex.length; i++) {
				result.add(resultSet.getString(columnIndex[i]));
			}
		}
		return result;
	}

	@Override
	public void deleteItemByPrimaryKey(String tableName, String primaryKey, String primaryKeyValue)
			throws SQLException {
		String deleteQ = "DELETE FROM " + tableName + " WHERE " + primaryKey + "=" + primaryKeyValue + ";";
		statement.executeUpdate(deleteQ);
	}

	@Override
	public boolean dropTable(String tableName, String dbName) throws SQLException {
		useDB(dbName);
		String query = "DROP TABLE " + tableName + ";";
		return statement.execute(query);
	}

	@Override
	public boolean dropDatabase(String dbName) throws SQLException {
		String query = "DROP DATABASE " + dbName + ";";
		return statement.execute(query);
	}

	@Override
	public String[] getColumnNameList(String tableName) throws SQLException {
		String query = "DESC " + tableName + ";";
		ResultSet resultSet = statement.executeQuery(query);
		ArrayList<String> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(resultSet.getString("Field"));
		}
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}

		return result;
	}

	@Override
	public List<String> getDatabases() throws SQLException {
		List<String> result = new ArrayList<>();
		String sql = "SHOW DATABASES;";
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			result.add(resultSet.getString("database"));
		}
		return result;
	}

	@Override
	public List<String> getTables(String dbName) throws SQLException {
		List<String> result = new ArrayList<>();
		useDB(dbName);
		String sql = "SHOW TABLES;";
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			result.add(resultSet.getString("tables_in_" + dbName));
		}
		return result;
	}

	@Override
	public Object[][] getAllDataInTheTable(String tableName) throws SQLException {
		List<String> list = serach(tableName, null);
		Object[][] result = new Object[list.size()
				/ getColumnNameList(tableName).length][getColumnNameList(tableName).length];
		for (int i = 0; i < list.size() / getColumnNameList(tableName).length; i++) {
			for (int j = 0; j < getColumnNameList(tableName).length; j++) {
				result[i][j] = list.get(i * getColumnNameList(tableName).length + j);
			}
		}
		return result;
	}



}
