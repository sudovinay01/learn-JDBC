package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

public class _09_Support {

	public static void main(String[] args) throws SQLException {
		System.out.println(_09_Support.class.getName());
		System.out.println("Description : Using getMetaData to get supported operations.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			printSupportedOerationsDetails(connection);
		}
	}

	public static void printSupportedOerationsDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		List<Method> supportMethods = new ArrayList<>();

		// Get all methods of the class
//		Method[] methods = dbmd.getClass().getDeclaredMethods();
		Method[] methods = dbmd.getClass().getMethods();

		// Loop through the methods and find the ones starting with "support"
		for (Method method : methods) {
//			System.out.println(method);
			if (method.getName().startsWith("support")) {
				supportMethods.add(method);
			}
		}

		// Call the support methods
		for (Method supportMethod : supportMethods) {
            try {
                // Check if the method has a return value
                if (supportMethod.getReturnType() != void.class) {
                    // Invoke the method and capture the return value
                    Object result = supportMethod.invoke(dbmd);
                    System.out.println(supportMethod.getName() + "() => " + result);
                } else {
                    // Invoke the method with no return value
                    supportMethod.invoke(dbmd);
                }
            } catch (Exception e) {
            	System.out.println("==> "+supportMethod);
                e.printStackTrace();
            }
        }
	}

}
