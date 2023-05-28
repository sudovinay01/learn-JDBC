package _06_metadata._01_DatabaseMetaData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class _09_Support {

	public static void main(String[] args)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		System.out.println(_09_Support.class.getName());
		System.out.println("Description : Using getMetaData to get supported operations.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printSupportedOerationsDetails(connection);
		}
	}

	public static void printSupportedOerationsDetails(Connection connection)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		DatabaseMetaData dbmd = connection.getMetaData();
		List<Method> supportMethods = new ArrayList<>();

		// Get all methods of the class
		Method[] methods = dbmd.getClass().getMethods();

		// Loop through the methods and find the ones starting with "support"
		for (Method method : methods) {
			if (method.getName().startsWith("support")) {
				supportMethods.add(method);
			}
		}

		// Call the support methods
		for (Method supportMethod : supportMethods) {
			try {
				// Check if the method has a return value
				if (supportMethod.getReturnType() != void.class && supportMethod.getParameterCount() == 0) {
					// Invoke the method and capture the return value
					Object result = supportMethod.invoke(dbmd);
					System.out.println(supportMethod.getName() + "() => " + result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE) => "
				+ dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE));
		System.out.println("supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED) => "
				+ dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED));
		System.out.println("supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED) => "
				+ dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
		System.out.println("supportsTransactionIsolationLevel(Connection.REPEATABLE_READ) => "
				+ dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
		System.out.println("supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE) => "
				+ dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));

		System.out.println("supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT) => "
				+ dbmd.supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT));
		System.out.println("supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT) => "
				+ dbmd.supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT));

		System.out.println("===============================\n" + "Supported ResultSet types\n"
				+ "===============================");
		for (Field type : ResultSet.class.getFields()) {
			if (type.getName().startsWith("TYPE")) {
				System.out.println("supportsResultSetType(" + type.getName() + ") => "
						+ dbmd.supportsResultSetType(type.getInt(type)));
			}
		}

		System.out.println("===============================\n" + "Supported ResultSet Concurrency\n"
				+ "===============================");
		for (Field type : ResultSet.class.getFields()) {
			if (type.getName().startsWith("TYPE")) {
				for (Field consurrency : ResultSet.class.getFields()) {
					if (consurrency.getName().startsWith("CONCUR")) {
						System.out.println("supportsResultSetConcurrency(" + type.getName() + ", "
								+ consurrency.getName() + ") => " + dbmd.supportsResultSetConcurrency(type.getInt(type),
										consurrency.getInt(consurrency)));
					}
				}
			}
		}

		System.out.println(
				"==========================\n" + "Supported type conversions\n" + "==========================");
		for (Field fromType : Types.class.getFields()) {
			for (Field toType : Types.class.getFields()) {
				System.out.println("supportsConvert(" + fromType.getName() + "," + toType.getName() + ") => "
						+ dbmd.supportsConvert(fromType.getInt(fromType), toType.getInt(toType)));
			}
		}
	}

}
