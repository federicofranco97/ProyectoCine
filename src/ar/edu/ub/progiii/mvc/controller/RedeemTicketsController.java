package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.OnlineBookingDTO;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class RedeemTicketsController {

    @Autowired
    ClientService clientService;
    Data data = new Data();

    /**
    Metodo que te lleva a la vista para retirar entradas
     */
    @GetMapping("/retiroentradas")
    public ModelAndView GetRedeemTickets(){
        ModelAndView model = new ModelAndView("RedeemTickets");
        return model;
    }
    
    /**
    Metodo que te lleva a la vista para byscar reservas
    @param BookingNumber
     */
    @GetMapping("/buscar_reserva")
    public ModelAndView GetBuscarReserva(@RequestParam("bookingId")String BookingNumber){
    	ModelAndView model = new ModelAndView("RedeemTickets");
        if(clientService.GetBookingById(BookingNumber) == null || clientService.GetBookingById(BookingNumber).getChannelCode() != 2){
            model.addObject("Content", Arrays.asList("Error","No se ha encontrado la reserva","1"));
            return model;
        }
        OnlineBookingDTO booking = new OnlineBookingDTO(clientService.GetBookingById(BookingNumber));
        FilmDTO film = clientService.GetFilmById(Integer.parseInt(booking.getMovieName()));
        model.addObject("Content", null);
        model.addObject("Booking",booking);
        model.addObject("pelicula",film);
        model.addObject("funcion",clientService.GetCinemaShow(booking.getShow()).getStartTime());
        model.addObject("msj","yes");
        return model;
    }
    
    /**
    Metodo para imprimir reserva
    @param BookingNumber
     */
    @GetMapping("/imprimir_reserva")
    public ModelAndView getImprimirReserva(@RequestParam("bookingId")String BookingNumber){
        clientService.RedeemBooking(BookingNumber);
        ModelAndView model = new ModelAndView("PrintBooking");
        return model;
    }

}
