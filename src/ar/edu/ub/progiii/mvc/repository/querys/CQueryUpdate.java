package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CQueryUpdate extends ConditionQueryBuilder {
    @Override
    public ArrayList<String> getStatementConditions() {
        return null;
    }

    @Override
    public void setStatementConditions(ArrayList<String> statementConditions) {

    }

    @Override
    public void addStatementCondition(String Condition) {

    }

    @Override
    public void addStatementCondition(List<String> Conditions) {

    }

    @Override
    public String buildConditionString() {
        return null;
    }

    /**
     * Metodo para construir querys
     *
     * @return
     */
    @Override
    public String Build() {
        return null;
    }

    /**
     * Metodo para correr querys
     *
     * @return
     */
    @Override
    public ResultSet Run() {
        return null;
    }
}
