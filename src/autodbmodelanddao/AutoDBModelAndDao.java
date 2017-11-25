
package autodbmodelanddao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Edward T. Tanko
 */
public class AutoDBModelAndDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String DBHost = "localhost";
        String DBPort = "3306";
        String DBName = "automodel";
        String DBUsername = "root";
        String DBPassword = "";
        ConnectionManager conMan = new ConnectionManager(DBHost, DBPort, DBName, DBUsername, DBPassword);
//        Connection conn = conMan.getConnection();
//        try{
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
//            while(rs.next()){
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("first_name"));
//                System.out.println(rs.getString("Last_name"));
//                System.out.println(rs.getString("email"));
//            }
//         }catch(SQLException ex){
//            //ex.printStackTrace();
//        }
//        }
            ModelManager mm = new ModelManager(conMan);
            //mm.createModel("users");
            mm.createModel("user_approves_letters");
       
        
    } 
    
}
