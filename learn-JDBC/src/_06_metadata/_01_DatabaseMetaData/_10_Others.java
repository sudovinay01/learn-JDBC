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

public class _10_Others {

	public static void main(String[] args)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		System.out.println(_10_Others.class.getName());
		System.out.println("Description : Using getMetaData to get supported operations.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printDetails(connection);
		}
	}

	public static void printDetails(Connection connection)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		DatabaseMetaData dbmd = connection.getMetaData();

		for (Method method : dbmd.getClass().getMethods()) {
			try {
				// Check if the method has a return value
				if (method.getReturnType() != void.class 
						&& method.toString().contains("DatabaseMetaData")
						// skipping support methods as already covered in Support class
						&& !method.toString().contains("support")
						&& method.getParameterCount() == 0) {
					// Invoke the method and capture the return value
					Object result = method.invoke(dbmd);
					System.out.println(method.getName() + "() => " + result);
				}
			} catch (Exception e) {
				System.out.println("==> "+method+" "+method.isAccessible());
				e.printStackTrace();
			}
		}

	}

}
