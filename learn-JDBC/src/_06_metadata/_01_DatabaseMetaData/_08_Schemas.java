package _06_metadata._01_DatabaseMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _08_Schemas {

	public static void main(String[] args) throws SQLException {
		System.out.println(_08_Schemas.class.getName());
		System.out.println("Description : Using getMetaData to get details about Schemass.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printSchemaDetails(connection);
		}
	}

	public static void printSchemaDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getSchemas();
		System.out.println("====================================");
		System.out.println("getSchemas");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		System.out.println("getSchemaTerm() => "+dbmd.getSchemaTerm());
		System.out.println("getMaxSchemaNameLength() => "+dbmd.getMaxSchemaNameLength());
		rs = dbmd.getSchemas(null, null);
		System.out.println("====================================");
		System.out.println("getSchemas");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
	}

}
