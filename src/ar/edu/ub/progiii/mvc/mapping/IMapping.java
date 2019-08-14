package ar.edu.ub.progiii.mvc.mapping;

import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.model.*;

public interface IMapping {

    /**
     * Mapeo de cliente a dto
     * @param cliente cliente que ingresa
     * @return dto de cliente
     */
    public ClientDTO MapDTOClient(Client cliente);

    /**
     * Mapeo de cleinte dto a cliente
     * @param clientDTO cliente dto que ingresa
     * @return retorno de cliente mapeado
     */
    public Client MapClientDTO(ClientDTO clientDTO);

    /**
     * Mape de emplado de modle a empleado dto
     * @param eployee empleado de model entrante
     * @return employee de dto
     */
    public EmployeeDTO MapDTOEmployee (Employee eployee);

    /**
     * Mapeo de empleado dto a empleado de model
     * @param eployeeDTO emplado dto entrante
     * @return empleado de model
     */
    public Employee MapEmployeeDTO (EmployeeDTO eployeeDTO);

    /**
     * Mapea booking a booking dto
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    public OnlineBookingDTO MapDTOBooking(OnlineBooking booking);

    /**
     * Mapea booking dto a booking de model.
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    public OnSiteBooking MapBookingDTO(OnSiteBookingDTO bookingDTO);

    /**
     * Mapea booking a booking dto
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    public OnSiteBookingDTO MapDTOBooking(OnSiteBooking booking);

    /**
     * Mapea booking dto a booking de model.
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    public OnlineBooking MapBookingDTO(OnlineBookingDTO bookingDTO);

    /**
     * Mapeo de respuesta sql a dto
     * @param SQLData data proveniente del sql
     * @return modelo de dto
     */
    public OnlineBookingDTO MapDTOSql(String SQLData);
}