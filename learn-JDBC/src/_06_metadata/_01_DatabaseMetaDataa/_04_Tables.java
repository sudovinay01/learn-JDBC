package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _04_Tables {

	public static void main(String[] args) throws SQLException {
		System.out.println(_04_Tables.class.getName());
		System.out.println("Description : Using getMetaData to get details about Tables.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_02_statement._01_Create.createTable(connection);
			printTableDetails(connection);
			_02_statement._01_Create.deleteTable(connection);
		}
	}

	public static void printTableDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getTables(null, null, null, null);
		System.out.println("====================================");
		System.out.println("getTables");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		System.out.println("getMaxColumnsInTable() => "+dbmd.getMaxColumnsInTable());
		System.out.println("getMaxTableNameLength() => "+dbmd.getMaxTableNameLength());
		System.out.println("getMaxTablesInSelect() => "+dbmd.getMaxTablesInSelect());
		System.out.println("allTablesAreSelectable() => "+dbmd.allTablesAreSelectable());
		System.out.println("getTableTypes() => ");
		rs = dbmd.getTableTypes();
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("usesLocalFilePerTable() => "+dbmd.usesLocalFilePerTable());
	}

}
