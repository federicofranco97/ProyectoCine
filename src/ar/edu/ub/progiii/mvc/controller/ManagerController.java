package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {

    @Autowired
    ClientService clientService;

    @GetMapping("/admin_main")
    public ModelAndView GetMain(){
        ModelAndView model = new ModelAndView("ManagerPage");
        return model;
    }

    @GetMapping("/manage_employees")
    public ModelAndView GetEmployeesView(){
        ModelAndView model = new ModelAndView("ManagerEmployees");
        model.addObject("EmployeeList",clientService.GetAllEmployees());
        return model;
    }

    @GetMapping("/ban_employee")
    public void BanEmployee(@RequestParam("employeeid")int EmployeeNumber){
        Boolean result = clientService.BanEmployee(EmployeeNumber);


    }
}
