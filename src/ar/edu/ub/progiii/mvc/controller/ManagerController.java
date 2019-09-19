package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

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
    public ModelAndView BanEmployee(@RequestParam("employeeid")int EmployeeNumber){
        int result = clientService.BanEmployee(EmployeeNumber);
        if(result == 1 ){
            RedirectView redirectView = new RedirectView("/manage_employees");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            RedirectView redirectView = new RedirectView("/admin_main");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }
    }

    @GetMapping("/delete_employee")
    public ModelAndView DeleteEmployee(@RequestParam("employeeid")int EmployeeNumber){
        int result = clientService.DeleteEmployee(EmployeeNumber);
        if(result == 1 ){
            RedirectView redirectView = new RedirectView("/manage_employees");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            RedirectView redirectView = new RedirectView("/admin_main");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }
    }

    @GetMapping("/edit_employee")
    public ModelAndView GetEditPage(@RequestParam("employeeid")int EmployeeNumber){
        ModelAndView model = new ModelAndView("EditEmployee");
        EmployeeDTO employeeDTO = clientService.GetEmployee(EmployeeNumber);
        model.addObject("empleado",employeeDTO);
        return model;
    }

    @PostMapping("/update_employee")
    public ModelAndView UpdateEmployee(EmployeeDTO employee){
        clientService.UpdateEmployee(employee);
        RedirectView redirectView = new RedirectView("/manage_employees");
        redirectView.setExposePathVariables(false);
        return new ModelAndView(redirectView);
    }

     @GetMapping("/manage_clients")
    public ModelAndView GetClients(){
        ModelAndView model = new ModelAndView("ManagerClients");
        model.addObject("ClientList",clientService.GetAllClients());
        return model;
    }

    @GetMapping("/ban_client")
    public ModelAndView BanClient(@RequestParam("clientid")int ClientNumber){
        int result = clientService.BanClient(ClientNumber);
        if(result == 1 ){
            RedirectView redirectView = new RedirectView("/manage_clients");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            ModelAndView model = new ModelAndView("ErrorPage");
            model.addObject("Content", Arrays.asList("Error","Ocurrio un error al Banear el cliente"));
            return model;
        }
    }

    @GetMapping("/delete_client")
    public ModelAndView DeleteClient(@RequestParam("clientid")int ClientNumber){
        int result = clientService.DeleteClient(ClientNumber);
        if(result == 1 ){
            RedirectView redirectView = new RedirectView("/manage_clients");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            ModelAndView model = new ModelAndView("ErrorPage");
            model.addObject("Content", Arrays.asList("Error","Ocurrio un error al borrar el cliente"));
            return model;
        }
    }

    @GetMapping("/edit_client")
    public ModelAndView EditClient(@RequestParam("clientid")int ClientNumber){
        ModelAndView model = null;

        return model;
    }
}
