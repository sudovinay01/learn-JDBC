package _03_prepare_statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class _04_Delete {

	public static void main(String[] args) throws SQLException {
		System.out.println(_04_Delete.class.getName());
		System.out.println("Description : Using statement to Delete data.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			_03_Update.updateTable(connection);
			System.out.println("Table before deletion..");
			_02_Read.readTable(connection);
			deleteRowInTable(connection);
			System.out.println("Table after deleting row with name ECLIPSE..");
			_02_Read.readTable(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE IF EXISTS TEST_TABLE");
			}
		}
	}
	
	public static void deleteRowInTable(Connection connection) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TEST_TABLE WHERE name = ?")){
			preparedStatement.setString(1, "ECLIPSE");
			preparedStatement.executeUpdate();
		}
	}
}
