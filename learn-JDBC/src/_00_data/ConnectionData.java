package _00_data;

/**
 * The Class Data is used to get connection details.
 */
public class ConnectionData {

	private ConnectionData() {
	}

	private static final String PROTOCOL = "jdbc:datadirect:mysql";
	private static final String HOST = "localhost";
	private static final String PORT = "3306";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DATABASE_NAME = "jdbc";
	private static final String EXTRA_PARAMETERS = "generateSimpleParameterMetadata=true";

	private static final String SEPARATOR_HOST = ";";
	private static final String SEPARATOR_PARAMETER = ";";
	
	private static final String FULL_URI = PROTOCOL + "://" + HOST + ":" + PORT + SEPARATOR_HOST 
			+ "user=" + USERNAME 
			+ SEPARATOR_PARAMETER + "password=" + PASSWORD 
			+ SEPARATOR_PARAMETER + "DatabaseName=" + DATABASE_NAME
			+ SEPARATOR_PARAMETER + EXTRA_PARAMETERS;

	/**
	 * Gets the full URI connection string.
	 *
	 * @return the full URI
	 */
	public static String getFullURI() {
		return ConnectionData.FULL_URI;
	}

	/**
	 * Gets the base URI.
	 *
	 * @return the URI
	 */
	public static String getBaseURI() {
		return PROTOCOL + "://" + HOST + ":" + PORT + SEPARATOR_HOST 
				+"DatabaseName=" + DATABASE_NAME
				+ SEPARATOR_PARAMETER + EXTRA_PARAMETERS;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public static String getUserName() {
		return USERNAME;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public static String getPassword() {
		return PASSWORD;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public static String getHost() {
		return HOST;
	}

	/**
	 * Gets the port number.
	 *
	 * @return the port
	 */
	public static String getPort() {
		return PORT;
	}

	/**
	 * Gets the database name.
	 *
	 * @return the database name
	 */
	public static String getDatabaseName() {
		return DATABASE_NAME;
	}

}
