package _06_metadata._02_ResultSetMetaData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class _01_ResultSetMetaData {

	public static void main(String[] args) throws SQLException {
		System.out.println(_01_ResultSetMetaData.class.getName());
		System.out.println("Description : Using ResultSetMetaData to get details.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_06_metadata._01_DatabaseMetaData._11_keys.createTableWithKeys(connection);
			printDetails(connection);
			_06_metadata._01_DatabaseMetaData._11_keys.deleteTableWithKeys(connection);
		}
	}

	public static void printDetails(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()){
			ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
			_00_data.ResultSetUtils.printResultSet(resultSet);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			System.out.println("===================================");
			System.out.println("getCatalogName(1) => "+rsmd.getCatalogName(1));
			System.out.println("getColumnClassName(1) => "+rsmd.getColumnClassName(1));
			System.out.println("getColumnCount() => "+rsmd.getColumnCount());
			System.out.println("getColumnDisplaySize(2) => "+rsmd.getColumnDisplaySize(2));
			System.out.println("getColumnLabel(3) => "+rsmd.getColumnLabel(3));
			System.out.println("getColumnName(3) => "+rsmd.getColumnName(3));
			System.out.println("getColumnType(2) => "+rsmd.getColumnType(2));
			System.out.println("getColumnTypeName(2) => "+rsmd.getColumnTypeName(2));
			System.out.println("getPrecision(1) => "+rsmd.getPrecision(1));
			System.out.println("getScale(1) => "+rsmd.getScale(1));
			System.out.println("getSchemaName(2) => "+rsmd.getSchemaName(2));
			System.out.println("getTableName(3) => "+rsmd.getTableName(3));
			System.out.println("isNullable(1) => "+rsmd.isNullable(1));
			System.out.println("isAutoIncrement(1) => "+rsmd.isAutoIncrement(1));
			System.out.println("isCaseSensitive(2) => "+rsmd.isCaseSensitive(2));
			System.out.println("isCurrency(2) => "+rsmd.isCurrency(2));
			System.out.println("isDefinitelyWritable(3) => "+rsmd.isDefinitelyWritable(3));
			System.out.println("isReadOnly(2) => "+rsmd.isReadOnly(2));
			System.out.println("isSearchable(1) => "+rsmd.isSearchable(1));
			System.out.println("isSigned(2) => "+rsmd.isSigned(2));
			System.out.println("isWritable(3) => "+rsmd.isWritable(3));
		}
	}

}
