package ar.edu.ub.progiii.mvc.repository.querys;

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
    public String Run();
}
