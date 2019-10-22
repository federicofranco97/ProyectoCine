package ar.edu.ub.progiii.mvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.codehaus.groovy.runtime.metaclass.NewMetaMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class OnSiteSaleController {
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
		
	/**
	 * Llama a la vista venta presencial, sus peliculas y funciones
	 * @return
	 */
	@GetMapping("/venta_presencial")
	public ModelAndView GetOnSiteSaleView(HttpServletRequest request) {
		if(clientService.IsEmployeeAlowed((int)request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("OnSiteSale");
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.GetServerDate());
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
	}
	
	/**
	 * Suma a la fecha de la pagina un dia, de no poderse activa un mensaje de aviso
	 * @param datePage
	 * @return
	 */
	@GetMapping("/sumar_fecha")
	public ModelAndView GetAddDate(@RequestParam("datePage") String datePage) {
		ModelAndView model = new ModelAndView("OnSiteSale");
		if(clientService.CanDaysBeAdded(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetAllShows());
	        model.addObject("date",clientService.AddDays(datePage, 1));
	        return model;
		}
		if(!clientService.CanDaysBeRemoved(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.GetServerDate());
			model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
			return model;
		}
		model.addObject("films",clientService.GetAllFilms());
		model.addObject("shows" ,clientService.GetAllShows());
		model.addObject("date",datePage);
		model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
		return model;
	}
	
	/**
	 * Resta a la fecha de la pagina un dia, de no poderse activa un mensaje de aviso
	 * @param datePage
	 * @return
	 */
	@GetMapping("/restar_fecha")
	public ModelAndView GetRemoveDate(@RequestParam("datePage") String datePage) {
		ModelAndView model = new ModelAndView("OnSiteSale");
		if(clientService.CanDaysBeRemoved(datePage) && !clientService.RedirectToBeginning(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetAllShows());
			model.addObject("date",clientService.RemoveDays(datePage, 1));
			return model;
		}
		if(clientService.RedirectToBeginning(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.RemoveDays(datePage, 1));
			return model;
		}
		model.addObject("films",clientService.GetAllFilms());
		model.addObject("shows" ,clientService.GetShowsByHour());
		model.addObject("date",clientService.GetServerDate());
		model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
		return model;
	}
	
	/**
	 * Realiza una reserva inicial con los valores de los parametros 
	 * y devuelve la pagna cantidad de enetradas, cargandole los datos
	 * @param showId
	 * @param movieId
	 * @param dateShow
	 * @return
	 */
	@GetMapping("/presencial_cantidadEntradas")
	public ModelAndView GetOnsiteAmountTicketsView(@RequestParam("functionId") String showId, @RequestParam("movieId") String movieId, @RequestParam("dateShow") String dateShow,  HttpServletRequest request) {
		if (clientService.InsertInitialBooking(movieId, showId, dateShow, (int) request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("AmountTickets");
			model.addObject("categories", clientService.GetAllRateCategories());
			return model;
		}
		ModelAndView modelError = new ModelAndView("OnSiteSale");
 		modelError.addObject("Contenido", Arrays.asList("Error","Se ha producido un error!","1"));
		return modelError;
		
	}
	
	/**
	 * Realiza una reserva inicial con los valores de los parametros 
	 * y devuelve la pagna cantidad de enetradas, cargandole los datos
	 * @param clientDNI 
	 * @return
	 */
	@PostMapping("/buscarCliente_traerInfo")
	public ModelAndView GetClientDataById(@RequestParam("clientDNI") String clientDNI, HttpServletRequest request) {
		if (clientService.GetClientByDNI(clientDNI) != null) {
			ModelAndView model = new ModelAndView("AmountTickets");
			data.UpdateLastBooking("NroCliente", clientService.GetClientByDNI(clientDNI).getClientNumber(), data.GetLastBookingByEmployeeId((int)request.getSession().getAttribute("EmployeeId")));
			model.addObject("categories", clientService.GetAllRateCategories());
			model.addObject("msj",clientService.GetClientByDNI(clientDNI));
			model.addObject("clientInfo", clientService.GetClientByDNI(clientDNI));
			return model;
		}
		ModelAndView modelError = new ModelAndView("AmountTickets");
 		modelError.addObject("Content", Arrays.asList("Error","No se ha encontrado el cliente!","1"));
 		modelError.addObject("categories", clientService.GetAllRateCategories());
		return modelError;
	}
	
	/**
	 * Registra a un cliente 
	 * @param fullName
	 * @param phone
	 * @param email
	 * @param adress
	 * @param birthDate
	 * @param dni
	 * @return
	 */
	@PostMapping("/registrar_cliente")
	public ModelAndView RegisterClient(@RequestParam("Name") String fullName, @RequestParam("Tel") String phone, @RequestParam("Email") String email, @RequestParam("Adress") String adress, @RequestParam("Date") String birthDate, @RequestParam("DNI") String dni, HttpServletRequest request) {
		if (clientService.GetClientByDNI(dni) == null) {
			ClientDTO clientRegisteredtDTO = clientService.RegisterClient(fullName, email, birthDate, dni, phone, adress);
			if(clientRegisteredtDTO != null && clientService.IsTheSameDay(clientRegisteredtDTO.getCreationDate())) {
				data.UpdateLastBooking("NroCliente", clientRegisteredtDTO.getClientNumber(), data.GetLastBookingByEmployeeId((int)request.getSession().getAttribute("EmployeeId")));
				ModelAndView model = new ModelAndView("AmountTickets");
				model.addObject("categories", clientService.GetAllRateCategories());
				model.addObject("msj",clientRegisteredtDTO);
				model.addObject("clientInfo", clientRegisteredtDTO); 
				return model;
			}
			ModelAndView modelError = new ModelAndView("AmountTickets");
	 		modelError.addObject("Content", Arrays.asList("Error","Ocurrio un error!","1"));
	 		modelError.addObject("categories", clientService.GetAllRateCategories());
			return modelError;
		}
		ModelAndView modelError = new ModelAndView("AmountTickets");
		modelError.addObject("Content", Arrays.asList("Error","El cliente ya existe1!","1"));
	 	modelError.addObject("categories", clientService.GetAllRateCategories());
		return modelError;
	}
	
	/**
	 * Realiza el registro de entradas y envia el precio total a la proxima vista
	 * @param underAge
	 * @param retired
	 * @param adult
	 * @param promo
	 * @param registeredAdult
	 * @param registeredUnderAge
	 * @param registeredOlder
     * @param total
	 * @return
	 */
	@GetMapping("/presencial_pagar")
	public ModelAndView GetPayView(@RequestParam("underAge") String underAge, @RequestParam("retired") String retired, @RequestParam("adult") String adult, @RequestParam("promo") String promo, @RequestParam("registeredAdult") String registeredAdult, @RequestParam("registeredUnderAge") String registeredUnderAge, @RequestParam("registeredOlder") String registeredOlder, @RequestParam("total") String total, HttpServletRequest request) {
		int employeeId = (int)request.getSession().getAttribute("EmployeeId");
		int amountTickets = Integer.parseInt(underAge) + Integer.parseInt(retired) + Integer.parseInt(adult) + Integer.parseInt(promo) + Integer.parseInt(registeredAdult) + Integer.parseInt(registeredUnderAge) + Integer.parseInt(registeredOlder); 
		data.UpdateLastBooking("PrecioTotal", Integer.parseInt(total), data.GetLastBookingByEmployeeId(employeeId));
		data.UpdateLastBooking("CantEntradas", amountTickets, data.GetLastBookingByEmployeeId(employeeId));
		data.RegisterTickets(employeeId, "1", clientService.GetRateById("1").getValue(), underAge);
		data.RegisterTickets(employeeId, "2", clientService.GetRateById("2").getValue(), retired);
		data.RegisterTickets(employeeId, "3", clientService.GetRateById("3").getValue(), adult);
		data.RegisterTickets(employeeId, "4", clientService.GetRateById("4").getValue(), promo);
		data.RegisterTickets(employeeId, "6", clientService.GetRateById("6").getValue(), registeredAdult);
		data.RegisterTickets(employeeId, "7", clientService.GetRateById("7").getValue(), registeredUnderAge);
		data.RegisterTickets(employeeId, "8", clientService.GetRateById("8").getValue(), registeredOlder);
		ModelAndView modelPay = new ModelAndView("pagina_de_pago");
		modelPay.addObject("total", amountTickets);
		return modelPay;
	}
}