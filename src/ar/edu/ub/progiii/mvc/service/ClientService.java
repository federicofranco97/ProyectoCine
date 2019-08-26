package ar.edu.ub.progiii.mvc.service;

import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.repository.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {

    Data dataManager = new Data();
    MappingTool mappingTool = new MappingTool();
    public static EmployeeDTO currentEmployee = new EmployeeDTO();  

    /**
     * Metodo booleano que checkea si el empleado que este logeado en la sesion de trabajo
     * Esta autorizado o esta baneado del sistema.
     * @param EmployeeNumber
     * @return
     */
    public boolean IsEmployeeAlowed(int EmployeeNumber){
        if(GetEmployeeCategory(EmployeeNumber)==4 || GetEmployeeCategory(EmployeeNumber)==5)return false;
        return true;
    }
    
    /**
     * Verifica que el empleado exista, valida su clave
     * y si su rango es valido 
     * @param EmployeeId
     * @param EmployeePass
     * @return
     */
    public boolean verifyEmployeeLogin(String EmployeeId, String EmployeePass) {
    	String response = dataManager.GetEmployeeByID(EmployeeId);
    	Employee Employee = mappingTool.MapEmployeeSQL(response);
    	//Retorna true o false si se cumple la condicion dentro del return
    	if((IsEmployeeAlowed(Integer.parseInt(EmployeeId)) && (Employee.getHashedPassword().equals(EmployeePass)))) {
    		currentEmployee = mappingTool.MapDTOEmployee(Employee);
    		return true;
    	}
    	return false;
    	 
    }

    /**
     * Busqueda de cliente por id de usuario
     * @param UID
     * @return
     */
    public ClientDTO GetClientByUID(String UID){
            String response = dataManager.GetClientByUId(UID);
            if(response==null){
                //No se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
            }
            ClientDTO clientDTO = mappingTool.MapDTOClientSQL(response);
            return clientDTO;
    }

    /**
     * Busqueda de todas las peliculas
     */
    public ArrayList<FilmDTO> GetAllFilms(){
        String response = dataManager.GetAllFilms();
        String [] aux = response.split("/");
        ArrayList<FilmDTO> filmList = new ArrayList<>();
        for (String item:aux) {
            filmList.add(mappingTool.MapDTOFilmSQL(item));
        }
        return filmList;
    }

    /**
     * Busqueda de reserva por id
     * @param id
     */
    public BookingDTO GetBookingById(String id){
        BookingDTO bookingDTO = new BookingDTO();
        String response = dataManager.GetBookingById(id);
        if(response==null){
            //No se encontro el usuario
            System.out.println("No se encontro la reserva");
            return null;
        }
        bookingDTO = mappingTool.MapSQLBookingDTO(response);
        return bookingDTO;
    }

    /**
     * Agreagar un nuevo cliente dado de alta en la pagina a la base de datos
     * @param clientDTO
     * @return string dataManager
     */
    public boolean CreateNewClient(ClientDTO clientDTO){
        boolean response = dataManager.PostNewClient(clientDTO);
        return response;
    }

    /**
     * Get list of all clients
     * @return arrayList de ClientDTO
     */
    public ArrayList<ClientDTO> GetAllClients(){
        String response = dataManager.GetAllClients();
        ArrayList<ClientDTO> clientList = new ArrayList<>();
        String [] aux = response.split("/");
        for (String item:aux) {
            clientList.add(mappingTool.MapDTOClientSQL(item));
        }
        return clientList;
    }

    /**
     * Obtiene la categoria de un empleado
     * @return int categoria
     */
    public int GetEmployeeCategory(int EmployeeNumber){
        String response = dataManager.CheckEmployeeCategory(EmployeeNumber);
        return Integer.parseInt(response);
    }

    /**
     * Trae todos los empleados que esten activos
     * @return arrayList EmployeeDTO
     */
    public ArrayList<EmployeeDTO> GetAllEmployees(){
        String response = dataManager.GetAllEmployees();
        ArrayList<EmployeeDTO> list = new ArrayList<>();
        String [] aux = response.split("/");
        for (String item : aux) {
            list.add(mappingTool.MapDTOEmployeeSQL(item));
        }
        return list;
    }

    /**
     * Banea a un empleado por id
     * @return int result
     */
    public int BanEmployee(int EmployeeNumber){
        int result = dataManager.BanEmployee(EmployeeNumber);
        return result;
    }

    /**
     * Borra a un empleado por id
     * @return int result
     */
    public int DeleteEmployee(int EmployeeNumber){
        int result = dataManager.DeleteEmployee(EmployeeNumber);
        return result;
    }

    /**
     * Traea un empleado por id
     * @return EmployeeDTO
     */
    public EmployeeDTO GetEmployee(int EmployeeNumber){
        ArrayList<EmployeeDTO> list = GetAllEmployees();
        for (EmployeeDTO item : list) {
            if(item.getEmployeeNumber() == EmployeeNumber)return item;
        }
        return null;
    }

    /**
     * Actualiza un empeado
     */
    public void UpdateEmployee(EmployeeDTO employee) {
        ArrayList<EmployeeDTO> list = GetAllEmployees();
        for (EmployeeDTO emp:list) {
            if(emp.equals(employee)){
                emp.setAddress(employee.getAddress());
                emp.setEmail(employee.getEmail());
                emp.setPhoneNumber(employee.getPhoneNumber());
                dataManager.UpdateProfile(emp);
            }
        }
    }

    public ArrayList<TicketDTO> GetAllTickets(){
        ArrayList<TicketDTO> list = new ArrayList<>();
        String [] response = dataManager.GetAllTickets().split("/");
        for (String item: response) {
            list.add(mappingTool.MapDTOTicketSQL(item));
        }
        return list;
    }

    public ArrayList<TicketDTO> GetActiveTickets(){
        ArrayList<TicketDTO> list = new ArrayList<>();
        String [] response = dataManager.GetActiveTickets().split("/");
        for (String item: response) {
            list.add(mappingTool.MapDTOTicketSQL(item));
        }
        return list;
    }

    /**
     * Limpio el usuario que esta en la sesion.
     */
    public void ClearCurrentUser(){
        currentEmployee = new EmployeeDTO();
    }

    /**
     * Verificar si hay un usuario cargado como current
     * @return
     */
    public boolean IsActiveUser(){
        if(currentEmployee.getEmployeeNumber()<0 || currentEmployee.getPhoneNumber() == null || currentEmployee.getFullName() == null
        || currentEmployee.getFullName().equals("") || currentEmployee.getEmail().isEmpty() || currentEmployee.getEmail() == null){
            return false;
        }
        return true;
    }

    /**
     * agregar el ticket a la base
     * @param ticketDTO
     */
    public int CreateTicket(TicketDTO ticketDTO){
        int result = dataManager.AddTicket(ticketDTO);
        return result;
    }

    /**
     * Banear cliente en base de datos
     * @param ClientNumber
     * @return
     */
    public int BanClient(int ClientNumber){
        int result = dataManager.BanClient(ClientNumber);
        return result;
    }

    /**
     * Borrado logico del cliente
     * @param ClientNumber
     * @return
     */
    public int DeleteClient(int ClientNumber){
        int result = dataManager.DeleteClient(ClientNumber);
        return result;
    }
}
