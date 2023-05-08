package _03_prepare_statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class _05_BatchUpdate {

	public static void main(String[] args) throws SQLException {
		System.out.println(_05_BatchUpdate.class.getName());
		System.out.println("Description : Using PreparedStatement and perform batch operation.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			_02_Read.readTable(connection);
			batchOperation(connection);
			System.out.println("After batch operations..");
			_02_Read.readTable(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			}
		}
	}
	
	public static void batchOperation(Connection connection) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_TABLE VALUES (?)")){
			connection.setAutoCommit(false);
			preparedStatement.setString(1, "BATCH_INSERT 1");
			preparedStatement.addBatch();
			preparedStatement.setString(1, "BATCH_INSERT 2");
			preparedStatement.addBatch();
			preparedStatement.setString(1, "BATCH_INSERT 3");
			preparedStatement.addBatch();
			int[] result = preparedStatement.executeBatch();
			System.out.println("-> Batch result for (INSERT INTO TEST_TABLE VALUES (?)) ->"+Arrays.toString(result));
			connection.setAutoCommit(true);
		}
		try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE TEST_TABLE SET name = ? WHERE name = ?")){
			connection.setAutoCommit(false);
			preparedStatement.setString(1, "MySQL");
			preparedStatement.setString(2, "SQL");
			preparedStatement.addBatch();
			preparedStatement.setString(1, "JDBC DRIVER");
			preparedStatement.setString(2, "DRIVER");
			preparedStatement.addBatch();
			int[] result = preparedStatement.executeBatch();
			System.out.println("-> Batch result for (UPDATE TEST_TABLE SET name = ? WHERE name = ?) ->"+Arrays.toString(result));		
			connection.setAutoCommit(true);
		}
	}
}
