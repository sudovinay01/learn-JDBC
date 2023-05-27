package _06_metadata._01_DatabaseMetaDataa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _06_Procedures {

	public static void main(String[] args) throws SQLException {
		System.out.println(_06_Procedures.class.getName());
		System.out.println("Description : Using getMetaData to get details about procedures.");
		System.out.println("Connenecting to Database..");	
		try (Connection connection = _01_connection._01_DriverManagerFullURI.getConnection()) {
			System.out.println("Connection established? " + connection.isValid(0));
			_02_statement._01_Create.createTable(connection);
			_04_procedure._01_Procedure.createProcedure(connection);
			printProceduresDetails(connection);
			_02_statement._01_Create.deleteTable(connection);
			_04_procedure._01_Procedure.dropProcedure(connection);
		}
	}

	public static void printProceduresDetails(Connection connection) throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getProcedures("jdbc", null, null);
		System.out.println("====================================");
		System.out.println("getProcedures");
		System.out.println("====================================");
		_00_data.ResultSetUtils.printResultSet(rs);
		System.out.println("====================================");
		System.out.println("getMaxProcedureNameLength() => "+dbmd.getMaxProcedureNameLength());
		System.out.println("getProcedureTerm() => "+dbmd.getProcedureTerm());
		System.out.println("allProceduresAreCallable() => "+dbmd.allProceduresAreCallable());
	}

}
