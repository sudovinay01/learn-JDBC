package _06_metadata._01_getMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class _01_DriverDetails {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_DriverDetails.class.getName());
		System.out.println("Description : Using getMetaData to get driver details.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printDriverDetails(connection);
		}
	}

	public static void printDriverDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		System.out.println("====================================");
		System.out.println("Driver Details");
		System.out.println("====================================");
		System.out.println(
				"Driver Name : "+dbmd.getDriverName()+
				"\nDriver Version : "+dbmd.getDriverVersion()+
				"\nDriver major version : "+dbmd.getDriverMajorVersion()+
				"\nDriver minor version : "+dbmd.getDriverMinorVersion());
		System.out.println("====================================");
	}

}
