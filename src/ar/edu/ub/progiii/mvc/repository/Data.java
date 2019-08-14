package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class Data implements IData{

    /**
     * Metodo para hacer un post generico
     *
     * @param data
     * @return
     */
    @Override
    public String PostQuery(String data) {
        return null;
    }

    /**
     * Metodo para hacer un get generico
     *
     * @param data
     * @return
     */
    @Override
    public String GetQuery(String data) {
        return null;
    }

    /**
     * Metodo para traer un cliente x id
     *
     * @param data
     * @return
     */
    @Override
    public String GetClientById(String data) {
        return null;
    }

    /**
     * Metodo para traer un cliente x nro cliente
     *
     * @param data
     * @return
     */
    @Override
    public String GetClientByUId(String data) {
        String result="";
       //Validacion para asegurar que solo pasa un entero como parametro
        try {
           Integer.parseInt(data);
       }catch (NumberFormatException ex){
           System.out.println("El numero ingresado no es valido");
           return null;
       }
        //empiezo la conexion y recibo el resultado de la query
        try {
            Connection connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from cliente where nrocliente="+data;
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("NombreCompleto"))+"_";
                    result += (Integer.parseInt(data))+"_";
                    result += (rst.getString("Telefono"))+"_";
                    result += (rst.getString("Email"))+"_";
                    result += (rst.getString("Direccion"))+"_";
                    result += (rst.getString("FechaNac"));
                }
            }
            else {
                System.out.println("OFFLINE");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        //Si no encontro nada devuelvo null.
        if((result == null || result.isEmpty()))return null;
        return result;
    }

    /**
     * Metodo para traer todas las peliculas
     *
     * @param data
     * @return
     */
    @Override
    public String GetAllFilms(String data) {
        return null;
    }

    /**
     * Metodo para traer una resrva x id
     *
     * @param data
     * @return
     */
    @Override
    public String GetBookingById(String data) {
        return null;
    }

    /**
     * Metodo postea un cliente nuevo
     *
     * @param data
     * @return
     */
    @Override
    public String PostNewClient(String data) {
        return null;
    }
}
