package ar.edu.ub.progiii.mvc.mapping;
import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.model.*;

import java.sql.Date;

public class MappingTool implements IMapping{

    /**
     * Mapeo de cliente a dto
     *
     * @param cliente cliente que ingresa
     * @return dto de cliente
     */
    @Override
    public ClientDTO MapDTOClient(Client cliente) {
        return null;
    }

    /**
     * Mapeo de cleinte dto a cliente
     *
     * @param clientDTO cliente dto que ingresa
     * @return retorno de cliente mapeado
     */
    @Override
    public Client MapClientDTO(ClientDTO clientDTO) {
        return null;
    }

    /**
     * Mape de emplado de modle a empleado dto
     *
     * @param eployee empleado de model entrante
     * @return employee de dto
     */
    @Override
    public EmployeeDTO MapDTOEmployee(Employee eployee) {
        return null;
    }

    /**
     * Mapeo de empleado dto a empleado de model
     *
     * @param eployeeDTO emplado dto entrante
     * @return empleado de model
     */
    @Override
    public Employee MapEmployeeDTO(EmployeeDTO eployeeDTO) {
        return null;
    }

    /**
     * Mapea booking a booking dto
     *
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    @Override
    public OnlineBookingDTO MapDTOBooking(OnlineBooking booking) {
        return null;
    }

    /**
     * Mapea booking dto a booking de model.
     *
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    @Override
    public OnSiteBooking MapBookingDTO(OnSiteBookingDTO bookingDTO) {
        return null;
    }

    /**
     * Mapea booking a booking dto
     *
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    @Override
    public OnSiteBookingDTO MapDTOBooking(OnSiteBooking booking) {
        return null;
    }

    /**
     * Mapea booking dto a booking de model.
     *
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    @Override
    public OnlineBooking MapBookingDTO(OnlineBookingDTO bookingDTO) {
        return null;
    }

    /**
     * Mapeo de respuesta sql a dto
     *
     * @param SQLData data proveniente del sql
     * @return modelo de dto
     */
    @Override
    public OnlineBookingDTO MapOBookingDTOSql(String SQLData) {
        return null;
    }

    /**
     * Mapea la rta del sql a un cliente dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public ClientDTO MapDTOClientSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        ClientDTO clientDTO;
        //si ocurre un error en el mapeo vuelve el cliente null
        try {
            String [] splitDate = aux[5].split(" ");
            clientDTO = new ClientDTO(aux[0],aux[4],aux[2], aux[3],(splitDate[0]),Integer.parseInt(aux[1]));
        }catch (Exception ex){
            clientDTO=null;
            System.out.println("Ocurrio un error en el mapeo");
        }
        return clientDTO;
    }

    /**
     * Mapeo de string de sql a film dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public FilmDTO MapDTOFilmSQL(String SQLData) {
        return null;
    }

    /**
     * Mapea string de sql a employee sql
     *
     * @param SQLData
     * @return
     */
    @Override
    public EmployeeDTO MapDTOEmployeeSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName(aux[0]);
        employeeDTO.setPhoneNumber(aux[1]);
        employeeDTO.setEmail(aux[2]);
        employeeDTO.setAddress(aux[3]);
        String [] auxDOB = aux[4].split(" ");
        employeeDTO.setDateOfBirth(auxDOB[0]);
        employeeDTO.setEmployeeNumber(Integer.parseInt(aux[5]));
        employeeDTO.setRank(aux[6]);
        return employeeDTO;
    }

    /**
     * Mapea data proveniente del sql como ticket dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public TicketDTO MapDTOTicketSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketID(aux[0]);
        ticketDTO.setTicketTitle(aux[1]);
        ticketDTO.setTicketAuthor(aux[2]);
        ticketDTO.setTicketContent(aux[3]);
        ticketDTO.setTicketDate(aux[4]);
        ticketDTO.setTicketStatus(aux[5]);
        return ticketDTO;
    }


}
