package com.sleepy.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.sleepy.data.*;

public class SqlManager {

	static final String DATABASE_NAME = "goodsrepo";
	static final String TABLE_NAME = "goods";
	static final String ID = "id";
	static final String NAME = "name";
	static final String CATEGORY = "category";
	static final String PRICE = "price";
	static final String QUANTITY = "quantity";

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_URL_PROPERTIES = "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";

	static final String USER = "root";
	static final String PASS = "kb123789";

	Connection connection = null;
	Statement statement = null;

	private static SqlManager instance = null;

	public static SqlManager getInstance() {
		if (instance == null) {
			instance = new SqlManager();
		}
		return instance;
	}

	public void connectDB() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL + DB_URL_PROPERTIES, USER, PASS);
		statement = connection.createStatement();
		System.out.println("connect successful...");
	}

	public void disconnectDB() throws SQLException {
		statement.close();
		connection.close();
		System.out.println("disconnected...");
	}

	private boolean checkDBExist() throws SQLException {
		String checkExist = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME='" + DATABASE_NAME + "';";
		String databaseName = null;
		ResultSet resultSet = statement.executeQuery(checkExist);
		while (resultSet.next()) {
			databaseName = resultSet.getString("SCHEMA_NAME");
			// System.out.println("name: "+databaseName+"!");
		}

		if (DATABASE_NAME.equals(databaseName)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkTableExist() throws SQLException {
		String checkExist = "SELECT DISTINCT t.table_name, n.SCHEMA_NAME FROM information_schema.TABLES t, information_schema.SCHEMATA n WHERE t.table_name = '"
				+ TABLE_NAME + "' AND n.SCHEMA_NAME = '" + DATABASE_NAME + "';";
		String tableName = null;

		ResultSet resultSet = statement.executeQuery(checkExist);
		while (resultSet.next()) {
			tableName = resultSet.getString("TABLE_NAME");
			// System.out.println("name: " + tableName + "!");
		}

		if (TABLE_NAME.equals(tableName)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkColumnExist(int id) throws SQLException {
		String query = "SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " + ID + "=" + id + ";";
		int result = -1;
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			result = resultSet.getInt(ID);
		}
		if (result == id) {
			return true;
		} else {
			return false;
		}
	}

	public void init() throws SQLException {
		String useDBQuery = "USE " + DATABASE_NAME + ";";

		if (!checkDBExist()) {
			createDatabase();
			System.out.println("database not exist..." + "\nCreating...");
		}
		System.out.println("use database");
		statement.execute(useDBQuery);

		if (!checkTableExist()) {
			createTable();
			System.out.println("table not exist...\ncreating...");
		}
	}

	public void createDatabase() throws SQLException {
		String createDBQuery = "CREATE DATABASE " + DATABASE_NAME + ";";
		statement.execute(createDBQuery);
	}

	public void createTable() throws SQLException {
		String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INT NOT NULL," + NAME
				+ " VARCHAR(255) NOT NULL," + CATEGORY + " VARCHAR(255) NOT NULL," + PRICE + " FLOAT NOT NULL,"
				+ QUANTITY + " INT NOT NULL," + "PRIMARY KEY(" + ID + ")" + ");";
		statement.execute(createTableQuery);
	}

	public void alterColumn(String propertity) throws SQLException {
		String alterQ = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + propertity + ";";
		statement.execute(alterQ);
	}

	public void addData(Goods data) throws SQLException {
		if (!checkColumnExist(data.id)) {
			String insertQ = "INSERT INTO " + TABLE_NAME + " VALUES(" + "'" + data.id + "'" + "," + "'" + data.name
					+ "'" + "," + "'" + data.category + "'" + "," + "'" + data.price + "'" + "," + "'" + data.quantity
					+ "'" + ");";
			statement.executeUpdate(insertQ);
		} else {
			System.out.println("ID already exist!");
		}
	}

	public void updateData(Goods data) throws SQLException {
		String updateQ = "UPDATE " + TABLE_NAME + " SET " + NAME + "='" + data.name + "'" + "," + CATEGORY + "='"
				+ data.category + "'" + "," + PRICE + "='" + data.price + "'" + "," + QUANTITY + "='" + data.quantity
				+ "'" + " WHERE " + ID + "=" + data.id + ";";
		statement.executeUpdate(updateQ);
	}


	public void updateDataByID(String id, String name, String data) throws SQLException {
		String updateQ = "UPDATE " + TABLE_NAME + " SET " + name + "=" + data + " WHERE " + ID + "=" + id + ";";
		statement.executeUpdate(updateQ);
	}

	public void deleteData(Goods data) throws SQLException {
		String deleteQ = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + "=" + data.id + ";";
		statement.executeUpdate(deleteQ);
	}

	public void deleteData(int id) throws SQLException {
		String deleteQ = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + "=" + id + ";";
		statement.executeUpdate(deleteQ);
	}

	public void displayData() throws SQLException {
		String queryStr = "SELECT * FROM " + TABLE_NAME + ";";

		ResultSet resultSet = statement.executeQuery(queryStr);

		while (resultSet.next()) {
			int id = resultSet.getInt(ID);
			float price = resultSet.getFloat(PRICE);
			String name = resultSet.getString(NAME);
			String category = resultSet.getString(CATEGORY);
			int quantity = resultSet.getInt(QUANTITY);

			System.out.println(ID + ": " + id + "\t" + NAME + ": " + name + "\t" + CATEGORY + ": " + category + "\t"
					+ PRICE + ": " + price + "\t" + QUANTITY + ": " + quantity);
		}
	}

	public Object[][] getData() throws SQLException {
		String queryStr = "SELECT * FROM " + TABLE_NAME + ";";

		ResultSet resultSet = statement.executeQuery(queryStr);

		ArrayList<Object> tmp = new ArrayList<>();
		while (resultSet.next()) {
			tmp.add(resultSet.getInt(ID));
			tmp.add(resultSet.getString(NAME));
			tmp.add(resultSet.getString(CATEGORY));
			tmp.add(resultSet.getFloat(PRICE));
			tmp.add(resultSet.getInt(QUANTITY));
		}

		Object[][] data = new Object[tmp.size()/5][5];
		for (int i=0;i<tmp.size()/5;i++) {
			for (int j = 0; j < 5; j++)
					data[i][j] = tmp.get(i*5 + j);
		}
		return data;
	}

	public Object[] getColumnNames() {
		Object[] names = { ID, NAME, CATEGORY, PRICE, QUANTITY };
		return names;
	}

	public Object[][] queryData(String name,String value) throws SQLException{
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+name+"="+value+";";
		ResultSet resultSet = statement.executeQuery(query);
		ArrayList<Object> tmp = new ArrayList<>();
		while (resultSet.next()) {
			tmp.add(resultSet.getInt(ID));
			tmp.add(resultSet.getString(NAME));
			tmp.add(resultSet.getString(CATEGORY));
			tmp.add(resultSet.getFloat(PRICE));
			tmp.add(resultSet.getInt(QUANTITY));
		}

		Object[][] data = new Object[tmp.size()/5][5];
		for (int i=0;i<tmp.size()/5;i++) {
			for (int j = 0; j < 5; j++)
					data[i][j] = tmp.get(i*5 + j);
		}
		return data;
	}
	
	public String getColumnValue(String queriedName,String queryName , String value) throws SQLException {
		String query = "SELECT "+queriedName+" FROM "+TABLE_NAME+" WHERE "+queryName+"="+value+";";
		ResultSet resultSet = statement.executeQuery(query);
		String result =null;
		while (resultSet.next()) {
			result = resultSet.getString(queriedName);
		}
		return result;
	}
}
