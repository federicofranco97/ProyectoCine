package ar.edu.ub.progiii.mvc.repository.querys;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class CQuerySelect extends ConditionQueryBuilder {

    private static final String StatementConstant = "Select";
    private static String TableName;
    private static ArrayList<String> Parameters;

    public CQuerySelect(String TableName, ArrayList<String> Parameters) {
        this.TableName = TableName;
        this.Parameters = Parameters;
    }

    @Override
    public ArrayList<String> getStatementConditions() {
        return null;
    }

    @Override
    public void setStatementConditions(ArrayList<String> statementConditions) {
        StatementConditions = statementConditions;
    }

    @Override
    public void addStatementCondition(String Condition) {
        StatementConditions.add(Condition);
    }

    @Override
    public String buildConditionString() {
        String result="";
        for (int i=0;i<StatementConditions.size();i++){
            result += StatementConditions.get(i);
            if(i!=StatementConditions.size()-1){
                result += "and";
            }
        }
        return result;
    }

    public String buildParametersString(){
        String result="";
        for (int i=0;i<Parameters.size();i++){
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
        return StatementConstant+" "+buildParametersString()+" from "+TableName+" where "+buildConditionString();
    }

    /**
     * Metodo para correr querys
     *
     * @return
     */
    @Override
    public String Run() {
        return null;
    }
}
