package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _07_Functions {

	public static void main(String[] args) throws SQLException {
		System.out.println(_07_Functions.class.getName());
		System.out.println("Description : Using getMetaData to get details about functions.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_02_statement._01_Create.createTable(connection);
			_05_function._01_Function.createFunction(connection);
			printFunctionsDetails(connection);
			_02_statement._01_Create.deleteTable(connection);
			_05_function._01_Function.dropFunction(connection);
		}
	}

	public static void printFunctionsDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getFunctions(null, null, null);
		System.out.println("====================================");
		System.out.println("getFunctions");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		System.out.println("getNumericFunctions() => "+dbmd.getNumericFunctions());
		System.out.println("getStringFunctions() => "+dbmd.getStringFunctions());
		System.out.println("getSystemFunctions() => "+dbmd.getSystemFunctions());
		System.out.println("getTimeDateFunctions() => "+dbmd.getTimeDateFunctions());
		rs = dbmd.getFunctionColumns(null, null, null, null);
		System.out.println("====================================");
		System.out.println("getFunctionColumns");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
	}

}
