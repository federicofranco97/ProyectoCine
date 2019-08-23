package ar.edu.ub.progiii.mvc.repository;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;

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
    public String PostNewClient(ClientDTO data);

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
}
