
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
     * Generate a .java Model call of a corresponding database table.
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
    
    
    
}

class HashMap{
    protected String sqlDataType = "";
    protected String javaDataType = "";
}
class DataTypeMap{
   
    public String getJavaDataType(String sqlDataType){
        ArrayList<HashMap> list = new ArrayList<HashMap>();
        //Numeric datatypes
        HashMap map = new HashMap();
        map.javaDataType = "int";
        map.sqlDataType = "int";
        list.add(map);
        
        map.javaDataType = "float";
        map.sqlDataType = "float";
        list.add(map);
        
        map.javaDataType = "double";
        map.sqlDataType = "double";
        list.add(map);
        
        map.javaDataType = "boolean";
        map.sqlDataType = "boolean";
        list.add(map);
        
        map.javaDataType = "short";
        map.sqlDataType = "tinyint";
        list.add(map);
        
        map.javaDataType = "long";
        map.sqlDataType = "bigint";
        list.add(map);
        
        map.javaDataType = "int";
        map.sqlDataType = "smallint";
        list.add(map);
        
        map.javaDataType = "int";
        map.sqlDataType = "mediumint";
        list.add(map);
        
        map.javaDataType = "float";
        map.sqlDataType = "decimal";
        list.add(map);
        
        map.javaDataType = "double";
        map.sqlDataType = "real";
        list.add(map);
        
        map.javaDataType = "short";
        map.sqlDataType = "bit";
        list.add(map);
        
        map.javaDataType = "long";
        map.sqlDataType = "serial";
        list.add(map);
        
        //All the other will be represented as String
        String javaDataType = "String";
        for(HashMap m:list){
            if(m.sqlDataType.equalsIgnoreCase(sqlDataType)){
                javaDataType = m.javaDataType;
            }
        }
        return javaDataType;
    }
}