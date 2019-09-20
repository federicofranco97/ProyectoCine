package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;

import java.sql.Connection;

public interface IData {


    /**
     * Metodo para traer un cliente con pass
     * @param data
     * @return
     */
    public String GetEmployeeByID(String data);

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
    public boolean PostNewClient(ClientDTO data);

    /**
     * Gets list o all clients
     * @return
     */
    public String GetAllClients();

    /**
     * Consulto con la base de dato el rol del empleado
     * @param EmployeeNumber
     * @return
     */
    public String CheckEmployeeCategory(int EmployeeNumber);

    /**
     * Trae la lista de todos los empleados
     * @return
     */
    public String GetAllEmployees();

    /**
     * Banear un empleado
     * @param EmployeeNumber
     * @return
     */
    public int BanEmployee(int EmployeeNumber);

    /**
     * Cambiar la clave de un empleado
     *
     * @param EmployeeNumber
     * @param newPass
     * @return
     */
    public int ChangePassEmployee(int EmployeeNumber, String newPass);
    
    /**
     * "Elmina" un empleado del sistema.
     * @param EmployeeNumber
     * @return
     */
    public int DeleteEmployee(int EmployeeNumber);

    /**
     * Actualizar perfil
     * @param employeeDTO
     */
    public int UpdateProfile(EmployeeDTO employeeDTO);

    /**
     * Trae la lista de todos los tickets
     * @return
     */
    public String GetAllTickets();

    /**
     * Trae solo los tickets activos
     * @return
     */
    public String GetActiveTickets();

    /**
     * Metodo para agregar un ticket a la base de datos
     * @return
     */
    public int AddTicket(TicketDTO ticketDTO);

    /**
     * Banear un cliente de las compras
     * @param ClientNumber
     * @return
     */
    public int BanClient(int ClientNumber );

    /**
     * Borrado logico de un cliente.
     * @param ClientNumber
     * @return
     */
    public int DeleteClient(int ClientNumber);

    /**
     * Trae el total de ventas del dia del empleado
     * @param EmployeeNumber
     * @return
     */
    public String GetEmployeeDailySales(int EmployeeNumber);

    /**
     * Trae el total de ventas de todo el dia
     * @return
     */
    public String GetGeneralDailySales();

    /**
     * Trae los empleados que estuvieron activos durante el dia
     * @return
     */
    public String GetEployeesActive();

    /**
     * Devuelve la cantidad de entradas sacadas por internet en el dia.
     * @return
     */
    public String GetOnlineBooQuantity();

    /**
     * Trae la pelicula mas comprada en el mes
     * @return
     */
    public String GetDayMostViewed();

    /**
     * Trae la pelicula mas comprada en el mes
     * @return
     */
    public String GetMonthMostViewed();


    /**
     * Trae el total de ventas de todo el mes
     * @return
     */
    public String GetGeneralMonthlySales();

    /**
     * Trae los empleados que estuvieron activos durante el mes
     * @return
     */
    public String GetEployeesActiveMonth();

    /**
     * Devuelve la cantidad de entradas sacadas por internet en el mes
     * @return
     */
    public String GetOnlineBooQuantityMonth();

    /**
     * Trae la tarifa promedio del mes
     * @return
     */
    public String GetCategoryMonth();

    /**
     * Trae el numero de supervisores que hubo logeados
     * @return
     */
    public String GetSupervisorsActiveMonth();

    /**
     * Trae la tarifa promedio del dia
     * @return
     */
    public String GetCategoryDay();

    /**
     * Trae el numero de supervisores que hubo logeados
     * @return
     */
    public String GetSupervisorsActive();

    /**
     * Registrar el login del empleado.
     * @param NroEmpleado
     * @param CodRol
     */
    public void RegistrarLog(String NroEmpleado,String CodRol);

    /**
     * Metodo para marcar un ticket como cerrado
     * @param TicketNumber
     */
    public void CloseTicket(int TicketNumber);

    /**
     * Pregunta la fecha del sql server
     * @return
     */
    public String GetServerDate();

    /**
     * Trae la lista de todas las sucursales.
     * @return
     */
    public String GetAllBranches();

    /**
     * Monto total de ventas del empleado en el dia.
     * @return
     */
    public String EmployeeDaySales(String EmployeeNumber);

    /**
     * Cantidad de ventas presenciales que realizo un empleado
     * @return
     */
    public String EmployeeDayBookings(String EmployeeNumber);

    /**
     * CAntidad de reservas que retiro un empleado en el dia
     * @return
     */
    public String EmployeeDayOnlineBookings(String EmployeeNumber);

    /**
     * Monto total de dinero que se alivio del empleado.
     * @return
     */
    public String EmployeeDayWithdraw(String EmployeeNumber);

    /**
     * Actualiza el campo Logeado en la base de datos.
     * @param EmployeeNumber
     */
    public void UpdateLoginStatus(String EmployeeNumber);
}
