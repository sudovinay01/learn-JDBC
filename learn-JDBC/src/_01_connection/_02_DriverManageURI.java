package _01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _02_DriverManageURI {

	public static void main(String[] args) throws SQLException {
		System.out.println(_02_DriverManageURI.class.getName());
		System.out.println("Description : Creating a connection to DataBase using URI, username and password with DriverManager.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
		}
	}

	/**
	 * Gets the connection to Database.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(_00_data.ConnectionData.getBaseURI(), 
										   _00_data.ConnectionData.getUserName(),
										   _00_data.ConnectionData.getPassword());
	}

}
