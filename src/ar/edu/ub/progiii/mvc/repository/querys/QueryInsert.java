package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.ub.progiii.mvc.repository.Data;

public class QueryInsert implements IQueryBuilder<Boolean> {
	
	private static final String StatementConstant = "INSERT INTO";
	private static String TableName;
	private static ArrayList<String> Columns = new ArrayList<>();
	private static ArrayList<String> Values = new ArrayList<>();
	
	/**
     * Metodo para construir la/s Columna/s
     * a ser insertadas
     *
     * @return string result
     */
    public String buildColumnString(){
        String result="";
        if(Columns.size()==0 || Columns.size() != Values.size()){
            return "";
        }
        for (int i=0;i<Columns.size();i++){
            result += Columns.get(i);
            if(i!=Columns.size()-1){
                result += ",";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir lo/s Valor/es
     * a ser insertados
     *
     * @return string result
     */
    public String buildValueString(){
        String result="";
        if(Values.size()==0 || Columns.size() != Values.size()){
            return "";
        }
        for (int i=0;i<Values.size();i++){
            result += Values.get(i);
            if(i!=Values.size()-1){
                result += ",";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir querys
     *
     * @return string query
     */
    @Override
    public String Build() {
        return StatementConstant+" "+TableName+" "+"("+buildColumnString()+")"+" "+"("+buildValueString()+")";
    }

    /**
     * Metodo para correr querys
     *
     * @return boolean result
     * @throws SQLException 
     */
    @Override
    public Boolean Run() throws SQLException {
    	boolean result= false;
        PreparedStatement stm = Data.connection.prepareStatement(Build());
        result = stm.execute();
        return result;
    }
}
