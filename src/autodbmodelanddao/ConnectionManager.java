package autodbmodelanddao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Edward T. Tanko
 */
public class ConnectionManager {

    private String DBHost = "localhost";
    private String DBPort = "3306";
    private String DBName = "database_name";
    private String DBUsername = "root";
    private String DBPassword = "triala";
    private Connection connection;
    
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public ConnectionManager(String DBHost, String DBPort, String DBName, String DBUsername, String DBPassword) {
        //url = "jdbc:mysql://localhost:port/db_name";
        String urlstring = "jdbc:mysql://"+DBHost+":"+DBPort+"/"+DBName+"";
        this.DBHost = DBHost;
        this.DBPort = DBPort;
        this.DBName = DBName;
        this.DBUsername = DBUsername;
        this.DBPassword = DBPassword;
       
        try {
            Class.forName(DRIVER_NAME);
            try {
                this.connection = DriverManager.getConnection(urlstring, DBUsername, DBPassword);
            } catch (SQLException ex) {
                //exception:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            //exception
            System.out.println("Driver not found.");
        }
    }
    
    public ResultSet query(Connection connection, String sql){
        ResultSet rs = null;
        try{
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
         return rs;
    }

    public String getDBHost() {
        return DBHost;
    }

    public String getDBPort() {
        return DBPort;
    }

    public String getDBName() {
        return DBName;
    }

    public String getDBUsername() {
        return DBUsername;
    }

    public String getDBPassword() {
        return DBPassword;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void setDBHost(String DBHost) {
        this.DBHost = DBHost;
    }

    public void setDBPort(String DBPort) {
        this.DBPort = DBPort;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public void setDBUsername(String DBUsername) {
        this.DBUsername = DBUsername;
    }

    public void setDBPassword(String DBPassword) {
        this.DBPassword = DBPassword;
    }
  
}
