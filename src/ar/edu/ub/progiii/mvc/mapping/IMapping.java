package ar.edu.ub.progiii.mvc.mapping;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.OnSiteBookingDTO;
import ar.edu.ub.progiii.mvc.dto.OnlineBookingDTO;
import ar.edu.ub.progiii.mvc.model.Booking;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.model.OnSiteBooking;
import ar.edu.ub.progiii.mvc.model.OnlineBooking;

public interface IMapping {

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

}
