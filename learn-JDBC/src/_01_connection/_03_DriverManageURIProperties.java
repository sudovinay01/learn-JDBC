package _01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class _03_DriverManageURIProperties {

	public static void main(String[] args) throws SQLException {
		System.out.println(_03_DriverManageURIProperties.class.getName());
		System.out.println("Description : Creating a connection to DataBase using URI and properties with DriverManager.");
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
		Properties properties = new Properties() ;
		properties.put("user", _00_data.ConnectionData.getUserName());
		properties.put("password", _00_data.ConnectionData.getPassword());
		properties.put("DatabaseName", _00_data.ConnectionData.getDatabaseName());
		return DriverManager.getConnection(_00_data.ConnectionData.getBaseURI(), properties);
	}

}
