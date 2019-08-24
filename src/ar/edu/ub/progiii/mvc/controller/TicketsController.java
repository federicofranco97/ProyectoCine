package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addObject("Valor","Hola");
        return model;
    }

    @PostMapping("/create_ticket")
    public ModelAndView CreateTicket(TicketDTO ticketDTO){
        RedirectView redirectView = new RedirectView("/tickets");
        redirectView.setExposePathVariables(false);
        int result = clientService.CreateTicket(ticketDTO);
        if(result == 1)return new ModelAndView(redirectView);
        else return new ModelAndView("error");

    }
}
