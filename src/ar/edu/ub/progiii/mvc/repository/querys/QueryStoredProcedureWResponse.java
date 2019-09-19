package ar.edu.ub.progiii.mvc.repository.querys;

import ar.edu.ub.progiii.mvc.repository.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryStoredProcedureWResponse implements IQueryBuilder<ResultSet> {

    private static final String StatementContant = "EXEC";
    private String Command;
    private static ArrayList<String> Parameters = new ArrayList<>();

    public QueryStoredProcedureWResponse(String command) {
        Command = command;
        Parameters.clear();
    }

    public void addParameter(List<String> parameters){
        Parameters.addAll(parameters);
    }

    public void addParameter(String parameter){
        Parameters.add(parameter);
    }

    public QueryStoredProcedureWResponse(String command, List<String> parameters) {
        Command = command;
        Parameters.clear();
        Parameters.addAll(parameters);
    }

    public static String getStatementContant() {
        return StatementContant;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public static ArrayList<String> getParameters() {
        return Parameters;
    }

    public static void setParameters(ArrayList<String> parameters) {
        Parameters = parameters;
    }

    public String BuildParameters(){
        String result = "";
        if(Parameters.size()==0){
            return "";
        }
        for (int i = 0; i < Parameters.size(); i++) {
            result += Parameters.get(i);
            if(i!=Parameters.size()-1){
                result += ",";
            }
        }
        return result;
    }

    /**
     * Metodo para construir querys
     *
     * @return
     */
    @Override
    public String Build() {
        return StatementContant + " "+ Command +" "+BuildParameters();
    }

    /**
     * Metodo para correr querys
     *
     * @return
     */
    @Override
    public ResultSet Run() throws SQLException {
        Statement stm = Data.connection.createStatement();
        ResultSet rst = stm.executeQuery(Build());
        return rst;
    }
}
