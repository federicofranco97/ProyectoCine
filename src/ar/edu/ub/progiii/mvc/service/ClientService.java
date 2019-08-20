package ar.edu.ub.progiii.mvc.service;

import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {

    Data dataManager = new Data();
    MappingTool mappingTool = new MappingTool();

    /**
     * Metodo booleano que checkea si el empleado que este logeado en la sesion de trabajo
     * Esta autorizado o esta baneado del sistema.
     * @param EmployeeNumber
     * @return
     */
    public boolean IsEmployeeAlowed(int EmployeeNumber){
        if(GetEmployeeCategory(EmployeeNumber)==4)return false;
        return true;
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
            //Falta implementar el mapping tool.
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
        //Falta hacer el mapeo de la response a una booking dto
        //Falta mapear todos los valores que vengan numericos como el cod sucursal, etc etc.
        return bookingDTO;
    }

    /**
     * Agreagar un nuevo cliente dado de alta en la pagina a la base de datos
     * @param clientDTO
     * @return
     */
    public String CreateNewClient(ClientDTO clientDTO){
        String response = dataManager.PostNewClient(clientDTO);
        return response;
    }

    /**
     * Get list of all clients
     * @return
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

    public int GetEmployeeCategory(int EmployeeNumber){
        String response = dataManager.CheckEmployeeCategory(EmployeeNumber);
        return Integer.parseInt(response);
    }

    public ArrayList<EmployeeDTO> GetAllEmployees(){
        String response = dataManager.GetAllEmployees();
        ArrayList<EmployeeDTO> list = new ArrayList<>();
        String [] aux = response.split("/");
        for (String item : aux) {
            list.add(mappingTool.MapDTOEmployeeSQL(item));
        }
        return list;
    }

    public int BanEmployee(int EmployeeNumber){
        int result = dataManager.BanEmployee(EmployeeNumber);
        return result;
    }

    public int DeleteEmployee(int EmployeeNumber){
        int result = dataManager.DeleteEmployee(EmployeeNumber);
        return result;
    }

    public EmployeeDTO GetEmployee(int EmployeeNumber){
        ArrayList<EmployeeDTO> list = GetAllEmployees();
        for (EmployeeDTO item : list) {
            if(item.getEmployeeNumber() == EmployeeNumber)return item;
        }
        return null;
    }

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
}
