package _06_metadata._01_DatabaseMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _11_keys {

	public static void main(String[] args)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		System.out.println(_11_keys.class.getName());
		System.out.println("Description : Using getMetaData to get keys details.");
		System.out.println("Connenecting to Database..");
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			createTableWithKeys(connection);
			printDetails(connection);
			deleteTableWithKeys(connection);
		}
	}

	public static void printDetails(Connection connection)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		DatabaseMetaData dbmd = connection.getMetaData();

		ResultSet rs = dbmd.getImportedKeys(null, null, "departments");
		System.out.println("====================================");
		System.out.println("getImportedKeys");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		
		rs = dbmd.getExportedKeys(null, null, "employees");
		System.out.println("====================================");
		System.out.println("getExportedKeys");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		
		rs = dbmd.getPrimaryKeys(null, null, "employees");
		System.out.println("====================================");
		System.out.println("getPrimaryKeys -> employees");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");

		rs = dbmd.getPrimaryKeys(null, null, "departments");
		System.out.println("====================================");
		System.out.println("getPrimaryKeys -> departments");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");

	}
	
	public static void createTableWithKeys(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP TABLE IF EXISTS departments");
			statement.executeUpdate("DROP TABLE IF EXISTS employees");
			
			statement.executeUpdate("CREATE TABLE employees (" + 
					"    employee_id INT PRIMARY KEY," + 
					"    employee_name VARCHAR(50)," + 
					"    department_id INT" + 
					");");
			System.out.println("Created table successfully -> employees");
			statement.executeUpdate("INSERT INTO employees (employee_id, employee_name, department_id)" + 
					"VALUES (1, 'John Doe', 1)," + 
					"       (2, 'Jane Smith', 2)," + 
					"       (3, 'Mike Johnson', 1)," + 
					"       (4, 'Emily Davis', 2);");
			System.out.println("Data inserted into -> employees table");

			statement.executeUpdate("CREATE TABLE departments (" + 
					"    department_id INT PRIMARY KEY," + 
					"    department_name VARCHAR(50)," + 
					"    manager_id INT," + 
					"    FOREIGN KEY (manager_id) REFERENCES employees(employee_id)" + 
					");");
			System.out.println("Created table successfully -> departments");
			statement.executeUpdate("INSERT INTO departments (department_id, department_name, manager_id)" + 
					"VALUES (1, 'Sales', 1)," + 
					"       (2, 'Marketing', 2)," + 
					"       (3, 'Finance', 3);");
			System.out.println("Data inserted into -> departments table");
		}
	}

	public static void deleteTableWithKeys(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("DROP TABLE departments");
			statement.executeUpdate("DROP TABLE employees");
			System.out.println("tables dropped..");
		}
	}

}
