package ar.edu.ub.progiii.mvc.mapping;
import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.model.*;

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
    public OnlineBookingDTO MapDTOSql(String SQLData) {
        return null;
    }


}
