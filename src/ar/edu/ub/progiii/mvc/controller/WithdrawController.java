package ar.edu.ub.progiii.mvc.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WithdrawController {
	
	@GetMapping("/")
	public ModelAndView GetView() {
		ModelAndView model = new ModelAndView("index.html");
		return model;
	}
	
	@PostMapping("/")
	public ModelAndView GetView2() {
		ModelAndView model = new ModelAndView("index.html");
		return model;
	}

}
