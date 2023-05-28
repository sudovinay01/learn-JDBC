package _06_metadata._01_DatabaseMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _03_Catalogs {

	public static void main(String[] args) throws SQLException {
		System.out.println(_03_Catalogs.class.getName());
		System.out.println("Description : Using getMetaData to get details about catalogs.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printCatalogDetails(connection);
		}
	}

	public static void printCatalogDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getCatalogs();
		System.out.println("====================================");
		System.out.println("getCatalogs");
		System.out.println("====================================");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		System.out.println("====================================");
		System.out.println("getCatalogSeparator() => "+dbmd.getCatalogSeparator());
		System.out.println("getCatalogTerm() => "+dbmd.getCatalogTerm());
		System.out.println("getMaxCatalogNameLength() => "+dbmd.getMaxCatalogNameLength());
		System.out.println("isCatalogAtStart() => "+dbmd.isCatalogAtStart());
	}

}
