package _02_statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class _05_BatchUpdate {

	public static void main(String[] args) throws SQLException {
		System.out.println(_05_BatchUpdate.class.getName());
		System.out.println("Description : Using statement and perform batch operation.");
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
		try (Statement statement = connection.createStatement()){
			connection.setAutoCommit(false);
			statement.addBatch("INSERT INTO TEST_TABLE VALUES ('BATCH_INSERT 1',123)");
			statement.addBatch("INSERT INTO TEST_TABLE VALUES ('BATCH_INSERT 2',124)");
			statement.addBatch("INSERT INTO TEST_TABLE VALUES ('BATCH_INSERT 3',125)");
			statement.addBatch("UPDATE TEST_TABLE SET name = 'MySQL' WHERE name = 'SQL'");
			statement.addBatch("UPDATE TEST_TABLE SET name = 'JDBC DRIVER' WHERE name = 'DRIVER'");
			statement.executeBatch();			
			connection.setAutoCommit(true);
		}
	}
}
