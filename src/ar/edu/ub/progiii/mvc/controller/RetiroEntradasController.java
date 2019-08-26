package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RetiroEntradasController {

    @GetMapping("/retiroentradas")
    public ModelAndView GetRetiroEntradas(){
        ModelAndView model = new ModelAndView("retiroentradas");
        return model;
    }
}
