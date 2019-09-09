package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.repository.querys.CQuerySelect;
import ar.edu.ub.progiii.mvc.repository.querys.CQueryUpdate;
import ar.edu.ub.progiii.mvc.repository.querys.QueryInsert;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedure;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedureWResponse;

import ar.edu.ub.progiii.mvc.service.ClientService;
import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class Data implements IData{

    public static Connection connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();

    /**
     * Metodo que logea informacion a la base de datos.
     * @param Tipo Tipo de log
     * @param Mensaje Mensaje que contiene el log
     */
    public void LogData(String Tipo,String Mensaje){
        String SPsql = "EXEC GenerarLog '"+Tipo+"' , '"+Mensaje+"' ";
        try {
            PreparedStatement stm = connection.prepareStatement(SPsql);
            boolean rst = stm.execute();
        }catch (Exception ex){
            System.out.println("Envio de log no realizado. "+ex.getMessage());
        }
    }

    /**
     * Transforma un resultset en una cadena de texto
     * @param resultSet
     * @param ParameterAmount
     * @return
     * @throws SQLException
     */
    private String ParseResultSet(ResultSet resultSet,int ParameterAmount) throws SQLException {
        String result = "";
        while(resultSet.next()){
            for (int i = 1; i < ParameterAmount; i++) {
                result += resultSet.getString(i).trim();
                if(i != ParameterAmount-1){
                    result += "_";
                }
            }
            result += "/";
        }
        String [] aux = result.split("/");
        if(aux.length == 1){
            result = result.replaceAll("/","");
        }
        return result;
    }

    /**
     * Transforma el result set en una cadena, acorde a las columnas que piden.
     * @param resultSet
     * @param SelectedColumnns
     * @return
     * @throws SQLException
     */
    private String ParseSpecificResultSet(ResultSet resultSet, List<String> SelectedColumnns) throws SQLException {
        String result = "";
        while(resultSet.next()){
            for (int i = 0; i < SelectedColumnns.size(); i++) {
                String ColumnName = SelectedColumnns.get(i);
                result += resultSet.getString(ColumnName).trim();
                if(i != SelectedColumnns.size()-1){
                    result += "_";
                }
            }
            result += "/";
        }
        String [] aux = result.split("/");
        if(aux.length == 1){
            result = result.replaceAll("/","");
        }
        return result;
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
            	 result = ParseSpecificResultSet(rst,Arrays.asList("NombreCompleto","Telefono","Email","Direccion","FechaNac",
                         "NroEmpleado","Clave","CodRol"));
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("NombreCompleto","NroCliente","Telefono","Email","Direccion","FechaNac"));
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("CodPelicula","NombrePelicula","DuracionMinutos","Sinopsis"));
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("CodReserva", "codpelicula", "codfuncion", "fecha", "nrosala", "cantentradas", "nrocliente", "codestadoreserva", "codcanal", "codsucursal", "PrecioTotal"));
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
            	QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "'"+data.getFullName()+"','"+data.getPhoneNumber()+"','"+data.getEmail()+"','"+data.getAddress()+"','"+data.getDateOfBirth()+"'");
            	result = queryInsert.Run();
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("NombreCompleto", "Nrocliente", "Telefono", "Email", "Direccion", "FechaNac"));
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("CodRol"));
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
            	result = ParseSpecificResultSet(rst,Arrays.asList("NombreCompleto", "Telefono", "Email", "Direccion", "FechaNac", "NroEmpleado", "CodRol"));
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
            	CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "CodRol=4");
            	cQueryUpdate.addStatementCondition("NroEmpleado="+EmployeeNumber);
            	result = cQueryUpdate.Run();
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
     * Cambiar la clave de un empleado
     *
     * @param EmployeeNumber
     * @param newPass
     * @return
     */
    @Override
    public int ChangePassEmployee(int EmployeeNumber, String newPass) {
        int result= -1;
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
            	CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "Clave='"+newPass+"'");
            	cQueryUpdate.addStatementCondition("NroEmpleado="+EmployeeNumber);
            	result = cQueryUpdate.Run();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
        //Logeo la informacion de la busqueda, Id de busqueda y resultado
        LogData("BanEmployee","Cambiar clave empleado id:"+EmployeeNumber);
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
            	CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "CodRol=5");
            	cQueryUpdate.addStatementCondition("NroEmpleado="+EmployeeNumber);
            	result = cQueryUpdate.Run();
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
                QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("ActualizarEmpleado");
                queryStoredProcedure.addParameter(Arrays.asList("'"+employeeDTO.getAddress()+"'","'"+employeeDTO.getEmail()+"'",
                        "'"+employeeDTO.getPhoneNumber()+"'",String.valueOf(employeeDTO.getEmployeeNumber())));
                queryStoredProcedure.BuildParameters();
                queryStoredProcedure.Build();
                result = queryStoredProcedure.Run();
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
            	CQuerySelect querySelect = new CQuerySelect("tickets", "*");
            	ResultSet rst = querySelect.Run();
               	result = ParseSpecificResultSet(rst,Arrays.asList("ID", "Title", "Employee", "Mensaje", "Fecha", "Estado"));
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
            	CQuerySelect querySelect = new CQuerySelect("tickets", "*");
            	querySelect.addStatementCondition(Arrays.asList("estado='activo'"));
            	ResultSet rst = querySelect.Run();
              	result = ParseSpecificResultSet(rst,Arrays.asList("ID", "Title", "Employee", "Mensaje", "Fecha", "Estado"));
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
            	QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("CrearTicket" ,Arrays.asList("'"+ticketDTO.getTicketTitle()+"', '"+ticketDTO.getTicketAuthor()+"', '"+ticketDTO.getTicketContent()+"'"));
            	result = queryStoredProcedure.Run();
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
            	CQueryUpdate cQueryUpdate = new CQueryUpdate("ClientStatus", "codrol=1005");
            	cQueryUpdate.addStatementCondition("Nrocliente="+ClientNumber);
            	result = cQueryUpdate.Run();
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
            	CQueryUpdate cQueryUpdate = new CQueryUpdate("ClientStatus", "codrol=1006");
            	cQueryUpdate.addStatementCondition("Nrocliente="+ClientNumber);
            	result = cQueryUpdate.Run();
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
            	QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventasempleado",Arrays.asList(String.valueOf(EmployeeNumber)));
            	ResultSet rst = queryStoredProcedureWResponse.Run();
              	result = ParseSpecificResultSet(rst,Arrays.asList("VentasEmpleado"));
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
            	QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventastotaldia");
            	ResultSet rst = queryStoredProcedureWResponse.Run();
            	result = ParseSpecificResultSet(rst,Arrays.asList("VentasDia"));
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
            	CQuerySelect querySelect = new CQuerySelect("Empleado e inner join VentaPresencial v on e.NroEmpleado=v.NroEmpleado inner join Reserva r on v.CodReserva=r.CodReserva ", "count(e.nroempleado) as CantidadEmpleados");
            	querySelect.addStatementCondition(Arrays.asList("datediff(day,getdate(),r.fecha)=0"));
            	ResultSet rst = querySelect.Run();
            	result = ParseSpecificResultSet(rst,Arrays.asList("CantidadEmpleados"));
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
            	CQuerySelect querySelect = new CQuerySelect("reserva", "count(codreserva)  as ReservasOnline");
            	querySelect.addStatementCondition(Arrays.asList("codcanal=2","CodEstadoReserva=3"));
            	ResultSet rst = querySelect.Run();
            	result = ParseSpecificResultSet(rst,Arrays.asList("ReservasOnline"));
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
            	QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("peliculapromediodia");
                ResultSet rst = queryStoredProcedureWResponse.Run();
                result = ParseSpecificResultSet(rst,Arrays.asList("CodPelicula", "NombrePelicula", "DuracionMinutos", "Sinopsis"));
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
            	QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("peliculapromediomes");
                ResultSet rst = queryStoredProcedureWResponse.Run();
                result = ParseSpecificResultSet(rst,Arrays.asList("CodPelicula", "NombrePelicula", "DuracionMinutos", "Sinopsis"));
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
            	QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("tarifapromediodia");
                ResultSet rst = queryStoredProcedureWResponse.Run();
                result = ParseSpecificResultSet(rst,Arrays.asList("NombreTarifa", "Precio"));
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
     * Trae el numero de llegadas tarde que hubo.
     *
     * @return
     */
    @Override
    public String GetSupervisorsActive() {
        String result="";
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("supervisoresonline");
                ResultSet rst = queryStoredProcedureWResponse.Run();
                result = ParseSpecificResultSet(rst,Arrays.asList("Online"));
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
     * Registrar el login del empleado.
     *
     * @param NroEmpleado
     * @param CodRol
     */
    @Override
    public void RegistrarLog(String NroEmpleado, String CodRol) {
        //empiezo la conexion y recibo el resultado de la query
        try {
            if(connection != null) {
                QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("RegistrarLogin");
                queryStoredProcedure.addParameter(Arrays.asList(NroEmpleado,CodRol));
                queryStoredProcedure.Run();
            }
            else {
                System.out.println("ConError No se pudo conectar con el sql server");
            }
        }catch (Exception ex){
            LogData("DataException","Ocurrio una exception al procesar el pedido***"+ex.getMessage());
        }
    }

    /**
     * Metodo para marcar un ticket como cerrado
     *
     * @param TicketNumber
     */
    @Override
    public void CloseTicket(int TicketNumber) {
        int result;
        try {
            CQueryUpdate cQueryUpdate = new CQueryUpdate("tickets", "Estado='Cerrado'");
            cQueryUpdate.addStatementCondition("ID="+TicketNumber);
            result = cQueryUpdate.Run();
        }catch(Exception ex){
            System.out.println("Ocurrio una excepcion al cerrar el ticket "+TicketNumber+" "+ex.getMessage());
        }
        LogData("TicketClose","Se cerro el ticket "+TicketNumber+" por empleado"+ ClientService.currentEmployee.getEmployeeNumber());
    }
}
