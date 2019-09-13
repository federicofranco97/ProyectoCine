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
    /*
    Método que te lleva a la vista Menu principal en el caso de estar autorizado
     */
    @GetMapping("/menu")
    public ModelAndView GetMenu(){
        ModelAndView model = new ModelAndView("menu");
        if(clientService.currentEmployee.getEmployeeNumber() > 0 ){
            int employeeCatNum = clientService.GetEmployeeCategory(clientService.currentEmployee.getEmployeeNumber());
            boolean isEmployeeAllowed = employeeCatNum==1 || employeeCatNum==3;
            model.addObject("modelDisplay",isEmployeeAllowed);
        }

        return model;
    }
}
