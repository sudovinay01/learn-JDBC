package _00_data;


/**
 * The Class SQLData contain data related to SQL queries for example.
 */
public class SQLData {

	private SQLData() {
	}
	
	public class SmallTable{
		
		private SmallTable() {
			throw new IllegalStateException("Utility class");
		}
		
		public static final String TABLE_NAME = "SMALL_TABLE";
	}
	

}
