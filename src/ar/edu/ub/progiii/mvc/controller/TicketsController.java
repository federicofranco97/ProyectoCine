package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class TicketsController {

    @Autowired
    ClientService clientService;

    @GetMapping("/tickets")
    public ModelAndView GetTickets(){
        ModelAndView model = new ModelAndView("Tickets");
        model.addObject("Tickets",clientService.GetActiveTickets());
        return model;
    }

    @GetMapping("/tickets_all")
    public ModelAndView GetTicketsAll(){
        ModelAndView model = new ModelAndView("TicketsAll");
        model.addObject("Tickets",clientService.GetAllTickets());
        return model;
    }

    @GetMapping("/add_ticket")
    public ModelAndView AddTicket(){
        ModelAndView model = new ModelAndView("TicketAdd");
        return model;
    }

    @PostMapping("/create_ticket")
    public ModelAndView CreateTicket(TicketDTO ticketDTO){
        ModelAndView model = new ModelAndView("Menu");
        int result = clientService.CreateTicket(ticketDTO);
        return model;
    }
}
