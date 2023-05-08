package _01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _01_DriverManagerFullURI {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_DriverManagerFullURI.class.getName());
		System.out.println("Description : Creating a connection to DataBase using full URI with DriverManager.");
		System.out.println("Connenecting to Database..");	
		/*
		 * Using try-with-resources will close all resource at the end If
		 * try-with-resources is not used. Need to call connection.close() to close the
		 * connection below.
		 */
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
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(_00_data.ConnectionData.getFullURI());
	}

}
