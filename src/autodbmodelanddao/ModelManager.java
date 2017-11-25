
package autodbmodelanddao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Edward T. Tanko
 */
public class ModelManager {

    private ConnectionManager conMan = null;
    public ModelManager(ConnectionManager conMan) {
        this.conMan = conMan;
    }

    public void createModel(String tableName){
        //select table attribute and key
        String sql = "SELECT COLUMN_NAME, COLUMN_DEFAULT, "
                + "DATA_TYPE, CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY  "
                + "FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = '"+conMan.getDBName()+"' AND "
                + "TABLE_NAME='"+tableName+"' ;";
        ResultSet rs = conMan.query(conMan.getConnection(), sql);
        DBTable table = new DBTable();
        table.setTableName(tableName);
        try{
        while(rs.next()){
            //get foreign keys
            String colName = rs.getString("COLUMN_NAME"); 
            String colDefault = rs.getString("COLUMN_DEFAULT"); 
            String colCharLength = rs.getString("CHARACTER_MAXIMUM_LENGTH"); 
            String colDataType = rs.getString("DATA_TYPE"); 
            boolean priKeyCol = false;
            if(rs.getString("COLUMN_KEY").equals("PRI")){
                priKeyCol = true;
            }
            DBAttribute attr = new DBAttribute();
            attr.colName = colName;
            attr.colDefault = colDefault;
            attr.colCharLength = colCharLength;
            attr.priKeyCol = priKeyCol;
            attr.colDataType = colDataType;
            table.getColumns().add(attr);
            
           
          /*
            String refTableSql = "REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME  "
                + "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
                + "WHERE TABLE_SCHEMA = '"+conMan.getDBName()+"' AND "
                + "TABLE_NAME='"+tableName+"' AND"
                + "COLUMN_NAME='"+colName+"' ;";
            ResultSet refTableRs = conMan.query(conMan.getConnection(), refTableSql);
            
            //The reference/foreign key column model
            boolean refKeyCol = false;
            String refKeyTable = "";
            String refKeyTableCol = "";
            while(refTableRs.next()){
                if(refTableRs.getString("COLUMN_KEY").equals("PRI")){
                    priKeyCol = true;
                    refKeyTable = refTableRs.getString("REFERENCED_TABLE_NAME");
                    refKeyTableCol = refTableRs.getString("REFERENCED_COLUMN_NAME");
                }
            }
            */
            
        }
         table.generateJavaModelClass();
        }catch(SQLException ex){
            
        }
        
        
        
    }
}
