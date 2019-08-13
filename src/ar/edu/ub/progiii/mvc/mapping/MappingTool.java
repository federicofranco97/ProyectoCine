package ar.edu.ub.progiii.mvc.mapping;
import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.OnSiteBookingDTO;
import ar.edu.ub.progiii.mvc.dto.OnlineBookingDTO;
import ar.edu.ub.progiii.mvc.model.Booking;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.model.OnSiteBooking;
import ar.edu.ub.progiii.mvc.model.OnlineBooking;

public class MappingTool implements IMapping{

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


}
