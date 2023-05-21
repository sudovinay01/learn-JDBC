package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _05_Columns {

	public static void main(String[] args) throws SQLException {
		System.out.println(_05_Columns.class.getName());
		System.out.println("Description : Using getMetaData to get details about columns.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_02_statement._01_Create.createTable(connection);
			_04_procedure._01_Procedure.createProcedure(connection);
			_05_function._01_Function.createFunction(connection);
			printTableDetails(connection);
			_02_statement._01_Create.deleteTable(connection);
			_05_function._01_Function.dropFunction(connection);
		}
	}

	public static void printTableDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getColumns(null, null, null, null);
		System.out.println("====================================");
		System.out.println("getColumns");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		System.out.println("getMaxColumnNameLength() => "+dbmd.getMaxColumnNameLength());
		System.out.println("getMaxColumnsInGroupBy() => "+dbmd.getMaxColumnsInGroupBy());
		System.out.println("getMaxColumnsInIndex() => "+dbmd.getMaxColumnsInIndex());
		System.out.println("getMaxColumnsInOrderBy() => "+dbmd.getMaxColumnsInOrderBy());
		System.out.println("getMaxColumnsInSelect() => "+dbmd.getMaxColumnsInSelect());
		rs = dbmd.getFunctionColumns(null, null, null, null);
		System.out.println("====================================");
		System.out.println("getFunctionColumns");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");

		rs = dbmd.getProcedureColumns(null, null, null, null);
		System.out.println("====================================");
		System.out.println("getProcedureColumns");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		
		rs = dbmd.getVersionColumns(null, null, null);
		System.out.println("====================================");
		System.out.println("getVersionColumns");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
	}

}
