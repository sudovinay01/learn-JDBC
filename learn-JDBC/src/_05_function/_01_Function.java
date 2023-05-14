package _05_function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import _00_data.templete;
import _03_prepare_statement._01_Create;
import _03_prepare_statement._02_Read;
import _03_prepare_statement._05_BatchUpdate;

public class _01_Function {
	public static void main(String[] args) throws SQLException {
		System.out.println(templete.class.getName());
		System.out.println("Description : Creating and calling function.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			_05_BatchUpdate.batchOperation(connection);
			_02_Read.readTable(connection);
			createFunction(connection);
			callFunction(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE TEST_TABLE");
				statement.executeUpdate("DROP FUNCTION IF EXISTS my_function");
			}
		}
	}

	public static void createFunction(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP FUNCTION IF EXISTS my_function");
			String myFunction = "CREATE FUNCTION my_function(myname VARCHAR(255)) RETURNS int DETERMINISTIC " +
			         "BEGIN "+
					 "DECLARE counts INT;"+
			         "SELECT COUNT(*) INTO counts FROM TEST_TABLE WHERE name LIKE CONCAT('%', myname, '%');"+
					 "RETURN counts;"+
			         "END";
			System.out.println("Function : "+myFunction);
			statement.executeUpdate(myFunction);
			System.out.println("Function created ...");
		}
	}

	public static void callFunction(Connection connection) throws SQLException {
		String myProcedure = "SELECT my_function(?)";
		try(PreparedStatement preparedStatement = connection.prepareStatement(myProcedure)){
			String searchLetters = "B";
			preparedStatement.setNString(1, searchLetters);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("=================================================================================");
			System.out.println("Count of table values containing letters '"+searchLetters+"' is retrived through fuuncftion call..");
			System.out.println("=================================================================================");
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
			}
			searchLetters = "My";
			preparedStatement.setNString(1, searchLetters);
			resultSet = preparedStatement.executeQuery();
			System.out.println("=================================================================================");
			System.out.println("Count of table values containing letters '"+searchLetters+"' is retrived through fuuncftion call..");
			System.out.println("=================================================================================");
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
			}
		}
	}
	
}
