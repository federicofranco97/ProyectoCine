package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
                System.out.println("ConError No se pudo conectar con el sql server");
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
     * @return
     */
    @Override
    public String GetAllFilms() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from Pelicula";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CodPelicula").trim())+"_";
                    result += (rst.getString("NombrePelicula").trim())+"_";
                    result += (rst.getString("DuracionMinutos").trim())+"_";
                    result += (rst.getString("Sinopsis").trim())+"/";
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Si no encontro nada devuelvo null.
        if((result.isEmpty())) {
            LogData("ErrorNotFound","No se pudo encontrar la tabla");
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("SearchAllFilms","Busqueda de todas las peliculas "+result);
        return result;
    }

    /**
     * Metodo para traer una resrva x id
     *
     * @param data
     * @return
     */
    @Override
    public String GetBookingById(String data) {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from reserva where codreserva='"+data+"'";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CodReserva").trim())+"_";
                    result += (rst.getString("codpelicula").trim())+"_";
                    result += (rst.getString("codfuncion").trim())+"_";
                    result += (rst.getString("fecha").trim())+"_";
                    result += (rst.getString("nrosala").trim())+"_";
                    result += (rst.getString("cantentradas").trim())+"_";
                    result += (rst.getString("nrocliente").trim())+"_";
                    result += (rst.getString("codestadoreserva").trim())+"_";
                    result += (rst.getString("codcanal").trim())+"_";
                    result += (rst.getString("codsucursal").trim())+"/";
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Si no encontro nada devuelvo null.
        if((result.isEmpty())) {
            LogData("ErrorNotFound","No se pudo encontrar la reserva "+data);
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("SearchBID","Busqueda de reserva por codigo  "+data+"---"+result);
        return result;
    }

    /**
     * Metodo postea un cliente nuevo
     *
     * @param data
     * @return
     */
    @Override
    public String PostNewClient(ClientDTO data) {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="insert into Cliente (NombreCompleto,Telefono,Email,Direccion,FechaNac) " +
                        " values ('"+data.getFullName()+"','"+data.getPhoneNumber()+"','"+data.getEmail()+"','"+data.getAddress()+"','"+data.getDateOfBirth()+"')";
                PreparedStatement stm = connection.prepareStatement(query);
                boolean rst = stm.execute();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("AddNewClient","Agregado nuevo cliente a la bd---"+result);
        return result;
    }

    /**
     * Gets list o all clients
     *
     * @return
     */
    @Override
    public String GetAllClients() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select Nrocliente,NombreCompleto,Telefono,Email,Direccion,FechaNac from cliente";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("NombreCompleto").trim())+"_";
                    result += (rst.getString("Nrocliente").trim())+"_";
                    result += (rst.getString("Telefono").trim())+"_";
                    result += (rst.getString("Email").trim())+"_";
                    result += (rst.getString("Direccion").trim())+"_";
                    result += (rst.getString("FechaNac")+"/");
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        if(result.isEmpty()){
            LogData("NotFound","No se encontro la lista de clientes");
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("GetAllClients","Pedido de lista de clientes");
        return result;
    }

    /**
     * Consulto con la base de dato el rol del empleado
     *
     * @param EmployeeNumber
     * @return
     */
    @Override
    public String CheckEmployeeCategory(int EmployeeNumber) {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select CodRol from empleado where nroempleado="+EmployeeNumber;
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CodRol")).trim();
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        if(result.isEmpty()){
            LogData("NotFound","No se encontro el cliente con id "+EmployeeNumber);
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("GetRolByEID","Pedido de estado de empleado");
        return result;
    }

    /**
     * Trae la lista de todos los empleados
     *
     * @return
     */
    @Override
    public String GetAllEmployees() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from empleado where codRol<>4";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("NombreCompleto").trim())+"_";
                    result += (rst.getString("Telefono").trim())+"_";
                    result += (rst.getString("Email").trim())+"_";
                    result += (rst.getString("Direccion").trim())+"_";
                    result += (rst.getString("FechaNac").trim())+"_";
                    result += (rst.getString("NroEmpleado").trim())+"_";
                    result += (rst.getString("CodRol").trim()+"/");
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Si no encontro nada devuelvo null.
        if((result.isEmpty())) {
            LogData("ErrorNotFound","No se pudo encontrar la tabla");
            return null;
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("GetAllEmployees","Busqueda de todos los empleados");
        return result;
    }

    /**
     * Banear un empleado
     *
     * @param EmployeeNumber
     * @return
     */
    @Override
    public Boolean BanEmployee(int EmployeeNumber) {
        Boolean result= false;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="update empleado set CodRol=4 where NroEmpleado="+EmployeeNumber;
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.execute();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("BanEmployee","Banear empleado "+EmployeeNumber);
        return result;
    }
}
