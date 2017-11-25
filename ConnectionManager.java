import java.sql.*;


public class ConnectionManager{

	private static final String DBHost = "localhost";
	private static final String DBPort = "3306";
	private static final String DBName = "automodel";
	private static final String DBUsername = "root";
	private static final String DBPassword = "";
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		Connection connection = null;
		String urlstring = "jdbc:mysql://"+this.DBHost+":"+this.DBPort+"/"+this.DBName+"";
		try {
			Class.forName(DRIVER_NAME);
			try {
				connection = DriverManager.getConnection(urlstring, this.DBUsername, this.DBPassword);
			} catch (SQLException ex) {
				//exception:
				System.out.println("Failed to create the database connection.");
			}
		}catch (ClassNotFoundException ex) {
			//exception
			System.out.println("Driver not found.");
		}
		return connection;
	}
}
