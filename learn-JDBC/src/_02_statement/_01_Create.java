package _02_statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class _01_Create {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_Create.class.getName());
		System.out.println("Description : Using statement to create.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			createTable(connection);
			deleteTable(connection);
		}
	}

	public static void createTable(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			statement.executeUpdate("CREATE TABLE TEST_TABLE" + "(NAME VARCHAR(255), ID  INTEGER)");
			System.out.println("Created successfully -> TEST_TABLE");
			statement.executeUpdate("INSERT INTO TEST_TABLE VALUES ('JAVA', 101)");
			statement.executeUpdate("INSERT INTO TEST_TABLE VALUES ('SQL', 102)");
			statement.executeUpdate("INSERT INTO TEST_TABLE VALUES ('DRIVER', 103)");
			System.out.println("Data inserted into -> TEST_TABLE");
		}
	}

	public static void deleteTable(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			System.out.println("TEST_TABLE dropped if exists.");
		}
	}
}
