package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
    @GetMapping("/menu")
    public ModelAndView GetMenu(){
        ModelAndView model = new ModelAndView("menu");
        model.addObject("modelDisplay",false);
        return model;
    }
}
