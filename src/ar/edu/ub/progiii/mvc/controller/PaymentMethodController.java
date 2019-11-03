package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentMethodController {
    @Autowired
    ClientService clientService;

}
