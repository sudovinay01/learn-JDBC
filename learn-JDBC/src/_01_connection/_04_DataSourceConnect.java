package _01_connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.ddtek.jdbcx.mysql.MySQLDataSource;

import _00_data.ConnectionData;

public class _04_DataSourceConnect {

	public static void main(String[] args) throws SQLException {
		System.out.println(_03_DriverManageURIProperties.class.getName());
		System.out.println("Description : Creating a connection to DataBase using MySQLDataSource.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
		}
	}

	public static Connection getConnection() throws SQLException {
		MySQLDataSource dataSource = new MySQLDataSource();
		dataSource.setServerName(ConnectionData.getHost());
		dataSource.setPortNumber(Integer.parseInt(ConnectionData.getPort()));
		dataSource.setUser(ConnectionData.getUserName());
		dataSource.setPassword(ConnectionData.getPassword());
		dataSource.setDatabaseName(ConnectionData.getDatabaseName());
		return dataSource.getConnection();
	}

}
