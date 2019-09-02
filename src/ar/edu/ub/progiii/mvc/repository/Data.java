package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.repository.querys.CQuerySelect;

import org.springframework.stereotype.Repository;


import java.sql.*;
import java.sql.Connection;
import java.util.Arrays;

@Repository
public class Data implements IData{

    public static Connection connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();

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
     * Metodo para traer un empleado x nro de empleado
     *
     * @param data
     * @return
     */
    @Override
	public String GetEmployeeByID(String data) {
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
            	 CQuerySelect querySelect = new CQuerySelect("empleado", "*");
            	 querySelect.addStatementCondition(Arrays.asList("nroempleado="+data));
            	 ResultSet rst = querySelect.Run();
                 while(rst.next()) {
                     result += (rst.getString("NombreCompleto"))+"_";
                     result += (rst.getString("Telefono"))+"_";
                     result += (rst.getString("Email"))+"_";
                     result += (rst.getString("Direccion"))+"_";
                     result += (rst.getString("FechaNac"))+"_";
                     result += (Integer.parseInt(data))+"_";
                     result += (rst.getString("Clave"))+"_";
                     result += (rst.getString("CodRol"));
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
             LogData("ErrorNotFound","No se pudo encontrar el empleado");
             return null;
         }
         //Logeo la informacion de la busqueda, Id de busqueda y resultado
         LogData("SearchCID","Busqueda empleado por id***Data: "+result);
         return result;
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
            	CQuerySelect querySelect = new CQuerySelect("cliente", "*");
            	querySelect.addStatementCondition(Arrays.asList("nrocliente="+data));
            	ResultSet rst = querySelect.Run();
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
            	CQuerySelect querySelect = new CQuerySelect("Pelicula", "*");
            	ResultSet rst = querySelect.Run();
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
            	CQuerySelect querySelect = new CQuerySelect("reserva", "*");
            	querySelect.addStatementCondition(Arrays.asList("codreserva="+data));
            	ResultSet rst = querySelect.Run();
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
                    result += (rst.getString("codsucursal").trim())+"_";
                    result += (rst.getString("PrecioTotal").trim())+"/";
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
    public boolean PostNewClient(ClientDTO data) {
        boolean result=false;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="insert into Cliente (NombreCompleto,Telefono,Email,Direccion,FechaNac) " +
                        " values ('"+data.getFullName()+"','"+data.getPhoneNumber()+"','"+data.getEmail()+"','"+data.getAddress()+"','"+data.getDateOfBirth()+"')";
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
            	CQuerySelect querySelect = new CQuerySelect("cliente c inner join clientstatus cs on c.NroCliente=cs.Nrocliente", "c.Nrocliente,c.NombreCompleto,c.Telefono,c.Email,c.Direccion,c.FechaNac");
            	querySelect.addStatementCondition(Arrays.asList("cs.CodRol=1004 or cs.CodRol=1005"));
            	ResultSet rst = querySelect.Run();
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
            	CQuerySelect querySelect = new CQuerySelect("empleado", "CodRol");
            	querySelect.addStatementCondition(Arrays.asList("nroempleado="+EmployeeNumber));
            	ResultSet rst = querySelect.Run();
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
            	CQuerySelect querySelect = new CQuerySelect("empleado", "*");
            	querySelect.addStatementCondition(Arrays.asList("codrol<>5"));
            	ResultSet rst = querySelect.Run();
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
        return result;
    }

    /**
     * Banear un empleado
     *
     * @param EmployeeNumber
     * @return
     */
    @Override
    public int BanEmployee(int EmployeeNumber) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="update empleado set CodRol=4 where NroEmpleado="+EmployeeNumber;
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("BanEmployee","Banear empleado id:"+EmployeeNumber);
        return result;
    }

    /**
     * "Elmina" un empleado del sistema.
     *
     * @param EmployeeNumber
     * @return
     */
    @Override
    public int DeleteEmployee(int EmployeeNumber) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="update empleado set CodRol=5 where NroEmpleado="+EmployeeNumber;
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("DeleteEmployee","Borrar empleado "+EmployeeNumber);
        return result;
    }

    /**
     * Actualizar perfil
     * @param employeeDTO
     */
    @Override
    public int UpdateProfile(EmployeeDTO employeeDTO) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query="exec ActualizarEmpleado '"+employeeDTO.getAddress()+"','"+employeeDTO.getEmail()+"','"
                        +employeeDTO.getPhoneNumber()+"',"+employeeDTO.getEmployeeNumber();
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("UpdateEmployee","Actualizar empleado "+employeeDTO.getEmployeeNumber()+"\n"+employeeDTO.getAddress()+
                "\n"+employeeDTO.getEmail()+"\n"+employeeDTO.getPhoneNumber());
        return result;
    }

    /**
     * Trae la lista de todos los tickets
     *
     * @return
     */
    @Override
    public String GetAllTickets() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from tickets";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("ID").trim())+"_";
                    result += (rst.getString("Title").trim())+"_";
                    result += (rst.getString("Employee").trim())+"_";
                    result += (rst.getString("Mensaje").trim())+"_";
                    result += (rst.getString("Fecha").trim()+"_");
                    result += (rst.getString("Estado").trim()+"/");
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
        return result;
    }

    @Override
    public String GetActiveTickets() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                String query="select * from tickets where estado='activo'";
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("ID").trim())+"_";
                    result += (rst.getString("Title").trim())+"_";
                    result += (rst.getString("Employee").trim())+"_";
                    result += (rst.getString("Mensaje").trim())+"_";
                    result += (rst.getString("Fecha").trim()+"_");
                    result += (rst.getString("Estado").trim()+"/");
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
        return result;
    }

    /**
     * Metodo para agregar un ticket a la base de datos
     *
     * @return
     */
    @Override
    public int AddTicket(TicketDTO ticketDTO) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec CrearTicket '"+ticketDTO.getTicketTitle()+"', '"+ticketDTO.getTicketAuthor()+"', '"+ticketDTO.getTicketContent()+"' ";
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("CreateTicket","crear ticket "+ticketDTO.getTicketTitle()+"\n"+ticketDTO.getTicketAuthor()+
                "\n"+ticketDTO.getTicketContent());
        return result;
    }

    /**
     * Banear un cliente de las compras
     *
     * @param ClientNumber
     * @return
     */
    @Override
    public int BanClient(int ClientNumber) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query ="update ClientStatus set codrol=1005 where Nrocliente="+ClientNumber;
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("BanClient","Banear estaod de cliente "+ClientNumber);
        return result;
    }

    /**
     * Borrado logico de un cliente.
     *
     * @param ClientNumber
     * @return
     */
    @Override
    public int DeleteClient(int ClientNumber) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query ="update ClientStatus set codrol=1006 where Nrocliente="+ClientNumber;
                PreparedStatement stm = connection.prepareStatement(query);
                result = stm.executeUpdate();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("DeleteClient","Borrado de cliente "+ClientNumber);
        return result;
    }

    /**
     * Trae el total de ventas del dia del empleado
     *
     * @param EmployeeNumber
     * @return
     */
    @Override
    public String GetEmployeeDailySales(int EmployeeNumber) {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec ventasempleado "+EmployeeNumber;
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("VentasEmpleado").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Trae el total de ventas de todo el dia
     *
     * @return
     */
    @Override
    public String GetGeneralDailySales() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec ventastotaldia ";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("VentasDia").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Trae los empleados que estuvieron activos durante el dia
     *
     * @return
     */
    @Override
    public String GetEployeesActive() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "select count(e.nroempleado) as CantidadEmpleados from Empleado e inner join VentaPresencial v " +
                        "on e.NroEmpleado=v.NroEmpleado inner join Reserva r on v.CodReserva=r.CodReserva " +
                        "where datediff(day,getdate(),r.fecha)=0";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CantidadEmpleados").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Devuelve la cantidad de entradas sacadas por internet en el dia.
     *
     * @return
     */
    @Override
    public String GetOnlineBooQuantity() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "select count(codreserva)  as ReservasOnline from Reserva where codcanal=2 and CodEstadoReserva=3";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("ReservasOnline").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Trae la pelicula mas comprada en el dia
     *
     * @return
     */
    @Override
    public String GetDayMostViewed() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec peliculapromediodia";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CodPelicula").trim())+"_";
                    result += (rst.getString("NombrePelicula").trim())+"_";
                    result += (rst.getString("DuracionMinutos").trim())+"_";
                    result += (rst.getString("Sinopsis").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Trae la pelicula mas comprada en el mes
     *
     * @return
     */
    @Override
    public String GetMonthMostViewed() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec peliculapromediomes";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("CodPelicula").trim())+"_";
                    result += (rst.getString("NombrePelicula").trim())+"_";
                    result += (rst.getString("DuracionMinutos").trim())+"_";
                    result += (rst.getString("Sinopsis").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }

    /**
     * Trae la tarifa promedio del dia
     *
     * @return
     */
    @Override
    public String GetCategoryDay() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                String query = "exec tarifapromediodia";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query);
                while(rst.next()) {
                    result += (rst.getString("NombreTarifa").trim())+"_";
                    result += (rst.getString("Precio").trim());
                }
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        return result;
    }
    public static void main(String[] args) throws SQLException{
    	Data data = new Data();
    	System.out.println(data.GetAllEmployees());
    }
}
