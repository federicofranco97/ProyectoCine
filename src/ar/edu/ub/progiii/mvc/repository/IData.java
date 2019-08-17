package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;

import java.sql.Connection;

public interface IData {

    /**
     * Metodo para hacer un post generico
     * @param data
     * @return
     */
    public String PostQuery(String data);

    /**
     * Metodo para hacer un get generico
     * @param data
     * @return
     */
    public String GetQuery(String data);

    /**
     * Metodo para traer un cliente x nro cliente
     * @param data
     * @return
     */
    public String GetClientByUId(String data);

    /**
     * Metodo para traer todas las peliculas
     * @return
     */
    public String GetAllFilms();

    /**
     * Metodo para traer una resrva x id
     * @param data
     * @return
     */
    public String GetBookingById(String data);

    /**
     * Metodo postea un cliente nuevo
     * @param data
     * @return
     */
    public String PostNewClient(ClientDTO data);

}
