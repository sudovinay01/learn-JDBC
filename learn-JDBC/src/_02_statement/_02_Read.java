package _02_statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _02_Read {

	public static void main(String[] args) throws SQLException {
		System.out.println(_02_Read.class.getName());
		System.out.println("Description : Using statement to Read.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			readTable(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			}
		}
	}
	
	public static void readTable(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()){
			ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_TABLE");
			System.out.println("==========\nTEST_TABLE\n==========");
			while(resultSet.next()) {
				System.out.println(resultSet.getString("NAME"));
			}
			System.out.println("==========");
		}
	}
}
