package _02_statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class _03_Update {

	public static void main(String[] args) throws SQLException {
		System.out.println(_03_Update.class.getName());
		System.out.println("Description : Using statement to update data in table.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			System.out.println("Table before updating..");
			_02_Read.readTable(connection);
			updateTable(connection);
			System.out.println("Table after updating row with name SQL to MySQL..");
			_02_Read.readTable(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			}
		}
	}
	
	public static void updateTable(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()){
			statement.executeUpdate("UPDATE TEST_TABLE SET name = 'MySQL' WHERE name = 'SQL'");
		}
	}
}
