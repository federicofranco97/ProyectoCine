package ar.edu.ub.progiii.mvc.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class RefundController {
	
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
	
	/**
	Metodo que te lleva a la vista de reembolso
	 *@return 
	 */
	@GetMapping("/reembolso")
	public ModelAndView GetRefundView(HttpServletRequest request) throws SQLException {
		if(clientService.IsEmployeeAlowed((int)request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("Refund");
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
	}
	
	/**
	Metodo que devuelve a la vista, una reserva por id
	*@param bookingID
	*@return 
	*/
	@PostMapping("/mostrar_Idreserva")
	public ModelAndView GetBookingByIdView(@RequestParam("reservationCode") String bookingID) throws SQLException {
		if(clientService.GetBookingById(bookingID) != null) {
			LocalDate bookingDate = LocalDate.parse(clientService.GetBookingById(bookingID).getBookingDate());
			LocalDate today = LocalDate.parse(clientService.GetDateToday());
			if(bookingDate.isAfter(today) || bookingDate.isEqual(today)) {
				BookingDTO booking = clientService.GetBookingById(bookingID);
				ModelAndView model = new ModelAndView("Refund");
				model.addObject("msj", "yes");
				model.addObject("booking", booking);
				model.addObject("movie", clientService.GetFilmById(Integer.parseInt(booking.getMovieName())).getFilmName());
				model.addObject("show", clientService.GetCinemaShow(booking.getShow()).getStartTime());
				return model;
			}
			ModelAndView modelError = new ModelAndView("Refund");
	 		modelError.addObject("Content", Arrays.asList("Error","La reserva esta vencida!","1"));
			return modelError;
		}
		ModelAndView modelError = new ModelAndView("Refund");
 		modelError.addObject("Content", Arrays.asList("Error","La reserva no existe!","1"));
		return modelError;
	}
	
	/**
	*Metodo que devuelve a la vista, todas las reservas de un cliente
	*@param idClient
	*@return 
	*/
	@PostMapping("/mostrar_reservas_idCliente")
	public ModelAndView GetBookingsByClientIdView(@RequestParam("idClientCode") String clientID) throws SQLException {
		if(clientService.GetClientByUID(clientID) != null) {
			if(clientService.GetAllBookingsByIdClient(clientID) != null) {
				ModelAndView model = new ModelAndView("Refund");
				model.addObject("msj2", "yes");
				model.addObject("bookings", clientService.GetAllBookingsByIdClient(clientID));
				return model;
			}
			ModelAndView modelError = new ModelAndView("Refund");
	 		modelError.addObject("Content", Arrays.asList("Error","No hay reservas de este cliente!","1"));
			return modelError;
		}
		ModelAndView modelError = new ModelAndView("Refund");
 		modelError.addObject("Content", Arrays.asList("Error","El cliente no existe!","1"));
		return modelError;
	}
}
