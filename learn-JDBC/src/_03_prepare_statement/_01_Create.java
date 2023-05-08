package _03_prepare_statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class _01_Create {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_Create.class.getName());
		System.out.println("Description : Using PreparedStatement to create.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			createTable(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			}
		}
	}
	
	public static void createTable(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()){
			statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			statement.executeUpdate("CREATE TABLE TEST_TABLE"+
							  "(NAME VARCHAR(255))");
			System.out.println("Created successfully -> TEST_TABLE");	
		}
		try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_TABLE VALUES (?)")){
			preparedStatement.setString(1, "JAVA");
			preparedStatement.executeUpdate();
			preparedStatement.setString(1, "SQL");
			preparedStatement.executeUpdate();
			preparedStatement.setString(1, "DRIVER");
			preparedStatement.executeUpdate();
			preparedStatement.setString(1, "EXTRA");
			preparedStatement.executeUpdate();
			System.out.println("Data inserted into -> TEST_TABLE");
		}
	}
}
