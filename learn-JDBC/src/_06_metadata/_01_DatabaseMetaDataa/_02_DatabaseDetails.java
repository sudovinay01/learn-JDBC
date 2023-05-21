package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class _02_DatabaseDetails {

	public static void main(String[] args) throws SQLException {
		System.out.println(_02_DatabaseDetails.class.getName());
		System.out.println("Description : Using getMetaData to get databse details.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printDatabaseDetails(connection);
		}
	}

	public static void printDatabaseDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		System.out.println("====================================");
		System.out.println("Database Details");
		System.out.println("====================================");
		System.out.println(
				"Database Name : "+dbmd.getDatabaseProductName()+
				"\nDatabase Version : "+dbmd.getDatabaseProductVersion()+
				"\nDatabase major version : "+dbmd.getDatabaseMajorVersion()+
				"\nDatabase minor version : "+dbmd.getDatabaseMinorVersion());
		System.out.println("====================================");
	}

}
