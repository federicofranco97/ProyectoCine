package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @Autowired
    ClientService clientService;

    @GetMapping("/menu")
    public ModelAndView GetMenu(){
        ModelAndView model = new ModelAndView("menu");
        int employeeCatNum = clientService.GetEmployeeCategory(5);
        model.addObject("modelDisplay",employeeCatNum==1);
        return model;
    }
}
