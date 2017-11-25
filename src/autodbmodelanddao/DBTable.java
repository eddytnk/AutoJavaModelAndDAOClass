
package autodbmodelanddao;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Edward T. Tanko
 */
public class DBTable {
    public ArrayList<DBAttribute> columns = null;
    public String tableName = "";

    public DBTable(){
        columns = new ArrayList<DBAttribute>();
    }
    public void setColumns(ArrayList<DBAttribute> columns) {
        this.columns = columns;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public ArrayList<DBAttribute> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }
    
    /**
     * Generate the Class name in the Java's Best Practice Formate
     * @param tableName
     * @return String
     */
    private String genClassNameFormat(String tableName){
         String[] splittedClassName = tableName.split("_");
        if(splittedClassName[splittedClassName.length-1].endsWith("s")){
            int strSize = ((splittedClassName[splittedClassName.length-1]).length()-1);
            splittedClassName[splittedClassName.length-1] = splittedClassName[splittedClassName.length-1].substring(0, strSize);
        }
        String className = "";
        for(int i=0;i<splittedClassName.length;i++){
            className += splittedClassName[i].substring(0, 1).toUpperCase() + splittedClassName[i].substring(1);
        }
        return className;
    }
    
    /**
     * Generate the field name in java's best practice format from database attribute name
     * @param attribute
     * @return 
     */
    private String genFieldName(String attribute){
         String[] splittedFieldName = attribute.split("_");
        
        String classFieldName = splittedFieldName[0];
        for(int i=1;i<splittedFieldName.length;i++){
            classFieldName += splittedFieldName[i].substring(0, 1).toUpperCase() + splittedFieldName[i].substring(1);
        }
        return classFieldName;
    }
    /**
     * Generate the ConnectionManager class to manage open connection string
     * @param conn 
     */
    public void generateConnectionManager(ConnectionManager conn){
        String model = "import java.sql.*;\n\n";
        model += "\npublic class ConnectionManager{\n";
        model += "\n";
        model += "\tprivate String DBHost = \""+conn.getDBHost()+"\";\n" +
                    "\tprivate String DBPort = \""+conn.getDBPort()+"\";\n" +
                    "\tprivate String DBName = \""+conn.getDBName()+"\";\n" +
                    "\tprivate String DBUsername = \""+conn.getDBUsername()+"\";\n" +
                    "\tprivate String DBPassword = \""+conn.getDBPassword()+"\";\n" +
                    "\tprivate static final String DRIVER_NAME = \"com.mysql.jdbc.Driver\";\n\n";
          model += "\tpublic Connection getConnection() {\n" +
                    "\t\tConnection connection = null;\n" +
                    "\t\tString urlstring = \"jdbc:mysql://\"+this.DBHost+\":\"+this.DBPort+\"/\"+this.DBName+\"\";\n" +
                    "\t\ttry {\n" +
                    "\t\t\tClass.forName(DRIVER_NAME);\n" +
                    "\t\t\ttry {\n" +
                    "\t\t\t\tconnection = DriverManager.getConnection(urlstring, this.DBUsername, this.DBPassword);\n" +
                    "\t\t\t} catch (SQLException ex) {\n" +
                    "\t\t\t\t//exception:\n" +
                    "\t\t\t\tSystem.out.println(\"Failed to create the database connection.\");\n" +
                    "\t\t\t}\n" +
                    "\t\t}catch (ClassNotFoundException ex) {\n" +
                    "\t\t\t//exception\n" +
                    "\t\t\tSystem.out.println(\"Driver not found.\");\n" +
                    "\t\t}\n" +
                    "\t\treturn connection;\n" +
                    "\t}";
          model += "\n";
          model += "}";
          
           String fileName = "ConnectionManager.java";
        try{
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(model);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /**
     * Generate a .java Model class of a corresponding database table.
     * Generate private fields with getter and setter methods
     */
    public void generateJavaModelClass(){
        
        String className = this.genClassNameFormat(this.tableName);
        String model = "";
        String getters = "";
        String setters = "";
        model += "\npublic class "+className+"{\n";
        model += "\n";
        model += "\t//Fields\n";
        
        for(DBAttribute attr:columns){
            String datatype = new DataTypeMap().getJavaDataType(attr.colDataType);
            String varName = this.genFieldName(attr.colName);
            String methodInit = this.genClassNameFormat(attr.colName);
            model += "\tprivate "+datatype+" "+varName+";\n";
            
            setters += "\tpublic void set"+methodInit+"("+datatype+" "+varName+"){\n";
            setters += "\t\tthis."+varName+" = "+varName+"\n";
            setters += "\t}\n\n";
            
            getters += "\tpublic "+datatype+" get"+methodInit+"(){\n";
            getters += "\t\treturn this."+varName+"\n";
            getters += "\t}\n\n";
        }
        
        model += "\n";
        model += setters+getters;
        model += "\n";
        model += "}";
        String fileName = className+".java";
        try{
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(model);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /**
     * Generate a .java DAO class of a corresponding database table.
     */
    public void generateJavaDAOClass( ConnectionManager conn){
        
        String className = this.genClassNameFormat(this.tableName)+"DAO";
        String model = "";
        model += "\npublic class "+className+"{\n";
        model += "\n";
        model += "\t//Fields\n";
        /*
        for(DBAttribute attr:columns){
            String datatype = new DataTypeMap().getJavaDataType(attr.colDataType);
            String varName = this.genFieldName(attr.colName);
            String methodInit = this.genClassNameFormat(attr.colName);
            model += "\tprivate "+datatype+" "+varName+";\n";
            
            setters += "\tpublic void set"+methodInit+"("+datatype+" "+varName+"){\n";
            setters += "\t\tthis."+varName+" = "+varName+"\n";
            setters += "\t}\n\n";
            
            getters += "\tpublic "+datatype+" get"+methodInit+"(){\n";
            getters += "\t\treturn this."+varName+"\n";
            getters += "\t}\n\n";
        }
        
        model += "\n";
        model += setters+getters;
        model += "\n";
        model += "}";
        
        */
        
        String fileName = className+".java";
        try{
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(model);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
}

class HashMap{
    protected String sqlDataType = "";
    protected String javaDataType = "";
}
class DataTypeMap{
   
    public String getJavaDataType(String sqlDataType){
        ArrayList<HashMap> list = new ArrayList<HashMap>();
        //Numeric datatypes
        HashMap map1 = new HashMap();
        map1.javaDataType = "int";
        map1.sqlDataType = "int";
        list.add(map1);
        
        HashMap map2 = new HashMap();
        map2.javaDataType = "float";
        map2.sqlDataType = "float";
        list.add(map2);
        
        HashMap map3 = new HashMap();
        map3.javaDataType = "double";
        map3.sqlDataType = "double";
        list.add(map3);
        
        HashMap map4 = new HashMap();
        map4.javaDataType = "boolean";
        map4.sqlDataType = "boolean";
        list.add(map4);
        
        HashMap map5 = new HashMap();
        map5.javaDataType = "short";
        map5.sqlDataType = "tinyint";
        list.add(map5);
        
        HashMap map6 = new HashMap();
        map6.javaDataType = "long";
        map6.sqlDataType = "bigint";
        list.add(map6);
        
        HashMap map7 = new HashMap();
        map7.javaDataType = "int";
        map7.sqlDataType = "smallint";
        list.add(map7);
        
        HashMap map8 = new HashMap();
        map8.javaDataType = "int";
        map8.sqlDataType = "mediumint";
        list.add(map8);
        
        HashMap map9 = new HashMap();
        map9.javaDataType = "float";
        map9.sqlDataType = "decimal";
        list.add(map9);
        
        HashMap map10 = new HashMap();
        map10.javaDataType = "double";
        map10.sqlDataType = "real";
        list.add(map10);
        
        HashMap map11 = new HashMap();
        map11.javaDataType = "short";
        map11.sqlDataType = "bit";
        list.add(map11);
        
        HashMap map12 = new HashMap();
        map12.javaDataType = "long";
        map12.sqlDataType = "serial";
        list.add(map12);
        
        //All the other will be represented as String
        String javaDataType = "String";
        for(HashMap m:list){
            if(m.sqlDataType.equalsIgnoreCase(sqlDataType)){
                javaDataType = m.javaDataType;
                break;
            }
            System.out.println(m.javaDataType);
        }
        return javaDataType;
    }
}