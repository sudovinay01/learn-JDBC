package _06_metadata._03_ParameterMetaData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class _01_ParameterMetaData {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_ParameterMetaData.class.getName());
		System.out.println("Description : Using ParameterMetaData to get details.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_06_metadata._01_DatabaseMetaData._11_keys.createTableWithKeys(connection);
			printDetails(connection);
			_06_metadata._01_DatabaseMetaData._11_keys.deleteTableWithKeys(connection);
		}
	}

	public static void printDetails(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees where department_id = ?")){
			ParameterMetaData pmd = statement.getParameterMetaData();
			System.out.println("===================================");
			System.out.println("getParameterClassName(1) => "+pmd.getParameterClassName(1));
			System.out.println("getParameterCount() => "+pmd.getParameterCount());
			System.out.println("getParameterMode(1) => "+pmd.getParameterMode(1));
			System.out.println("getParameterType(1) => "+pmd.getParameterType(1));
			System.out.println("getParameterTypeName(1) => "+pmd.getParameterTypeName(1));
			System.out.println("getPrecision(1) => "+pmd.getPrecision(1));
			System.out.println("getScale(1) => "+pmd.getScale(1));
			System.out.println("isNullable(1) => "+pmd.isNullable(1));
			System.out.println("isSigned(1) => "+pmd.isSigned(1));
		}
	}

}
