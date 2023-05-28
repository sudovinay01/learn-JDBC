package _06_metadata._01_DatabaseMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _12_typeInfo {

	public static void main(String[] args) throws SQLException {
		System.out.println(_12_typeInfo.class.getName());
		System.out.println("Description : Using getMetaData to get typeInfo.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_06_metadata._01_DatabaseMetaData._11_keys.createTableWithKeys(connection);
			printDetails(connection);
			_06_metadata._01_DatabaseMetaData._11_keys.deleteTableWithKeys(connection);
		}
	}

	public static void printDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getTypeInfo();
		System.out.println("====================================");
		System.out.println("getTypeInfo");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
	}
}
