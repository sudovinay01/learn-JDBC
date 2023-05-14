package _04_procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import _00_data.templete;
import _03_prepare_statement._01_Create;
import _03_prepare_statement._02_Read;
import _03_prepare_statement._05_BatchUpdate;

public class _01_Procedure {
	public static void main(String[] args) throws SQLException {
		System.out.println(templete.class.getName());
		System.out.println("Description : Using CallableStatement to call stored procedure.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_01_Create.createTable(connection);
			_05_BatchUpdate.batchOperation(connection);
			_02_Read.readTable(connection);
			createProcedure(connection);
			callProcedure(connection);
			try (Statement statement = connection.createStatement()){
				statement.executeUpdate("DROP TABLE TEST_TABLE");
				statement.executeUpdate("DROP PROCEDURE my_procedure");
			}
		}
	}

	public static void createProcedure(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP PROCEDURE IF EXISTS my_procedure");
			String myProcedure = "CREATE PROCEDURE my_procedure() " +
								 "BEGIN "+
								 "SELECT * FROM TEST_TABLE WHERE name LIKE '%B%';"+
								 "END";
			System.out.println("Procedure : "+myProcedure);
			statement.executeUpdate(myProcedure);
			System.out.println("Procedure created ...");
		}
	}

	public static void callProcedure(Connection connection) throws SQLException {
		String myProcedure = "{call my_procedure()}";
		try(CallableStatement callableStatement = connection.prepareCall(myProcedure)){
			ResultSet resultSet = callableStatement.executeQuery();
			System.out.println("========================================================================");
			System.out.println("Table values containing letter 'B' are retrived through procedure call..");
			System.out.println("========================================================================");
			while(resultSet.next()){
				System.out.println(resultSet.getString("name"));
			}
		}
	}
	
}
