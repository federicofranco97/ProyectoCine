package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedeemTicketsController {

    @Autowired
    ClientService clientService;
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
     */
    @GetMapping("/buscar_reserva")
    public ModelAndView GetBuscarReserva(@RequestParam("bonumber")String BookingNumber){
        ModelAndView model = new ModelAndView("RedeemTickets");
        clientService.GetBookingById(BookingNumber);
        return model;
    }

}
