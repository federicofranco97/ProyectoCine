package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IQueryBuilder {

    /**
     * Metodo para construir querys
     * @return
     */
    public String Build();

    /**
     * Metodo para correr querys
     * @return
     */
    public ResultSet Run() throws SQLException;
}
