package _00_data;

import java.sql.Connection;
import java.sql.SQLException;

public class templete {

	public static void main(String[] args) throws SQLException {
		System.out.println(templete.class.getName());
		System.out.println("Description : Using statement to create.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
		}
	}

}
