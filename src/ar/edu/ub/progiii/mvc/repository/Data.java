package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

import java.sql.*;
import java.sql.Connection;

@Repository
public class Data implements IData{

    Connection connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();

    /**
     * Metodo que logea informacion a la base de datos.
     * @param Tipo Tipo de log
     * @param Mensaje Mensaje que contiene el log
     */
    private void LogData(String Tipo,String Mensaje){
        String SPsql = "EXEC GenerarLog '"+Tipo+"' , '"+Mensaje+"' ";
        try {
            PreparedStatement stm = connection.prepareStatement(SPsql);
            boolean rst = stm.execute();
        }catch (Exception ex){
            System.out.println("Envio de log no realizado. "+ex.getMessage());
        }
    }

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
                LogData("ConError","No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Si no encontro nada devuelvo null.
        if((result.isEmpty())) {
            LogData("ErrorNotFound","No se pudo encontrar el cliente");
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("SearchCID","Busqueda cliente por id***Data: "+result);
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
