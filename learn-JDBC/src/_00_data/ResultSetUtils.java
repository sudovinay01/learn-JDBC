package _00_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtils {

	private static String tableHeaderSeperator = "=";
	private static String tableBorderHorizontal = "-";
	private static String tableValueSeperator = "|";

	public static void printResultSet(ResultSet rs) throws SQLException {
		int columnCount = rs.getMetaData().getColumnCount(), rowCount, maxValueLength = 0;
		String[] columnNames = new String[columnCount];
		List<String[]> data = new ArrayList<String[]>();

		// Store column names in array
		for (int i = 0; i < columnCount; i++) {
			columnNames[i] = rs.getMetaData().getColumnName(i+1);
			maxValueLength = returnGreatest(maxValueLength, columnNames[i].length());
		}

		// Store table data in list of arrays
		while (rs.next()) {
			String[] row = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				row[i] = rs.getString(i + 1);
				maxValueLength = returnGreatest(maxValueLength, (row[i] != null ? row[i].length() : 0));
			}
			data.add(row);
		}

		// Print column names
		int tablelength = (maxValueLength + 2) * columnCount;
		printSymbolInLine(tablelength, tableBorderHorizontal);
		for (int i = 0; i < columnCount; i++) {
			System.out.print(tableValueSeperator + String.format("%1$-" + maxValueLength + "s", columnNames[i])
					+ tableValueSeperator);
		}
		System.out.println();
		printSymbolInLine(tablelength, tableHeaderSeperator);

		// Print data rows
		if (data.size() == 0) {
			System.out.println(tableValueSeperator
					+ String.format("%1$-" + (tablelength - 2) + "s", "No data returned...") + tableValueSeperator);
		} else {
			for (String[] row : data) {
				for (int i = 0; i < row.length; i++) {
					System.out.print(tableValueSeperator + String.format("%1$-" + maxValueLength + "s", row[i])
							+ tableValueSeperator);
				}
				System.out.println();
			}
		}

		printSymbolInLine(tablelength, tableBorderHorizontal);
	}

	private static int returnGreatest(int value1, int value2) {
		return value1 > value2 ? value1 : value2;
	}

	private static void printSymbolInLine(int printCount, String symbol) {
		for (int i = 0; i < printCount; i++)
			System.out.print(symbol);
		System.out.println();
	}

}
