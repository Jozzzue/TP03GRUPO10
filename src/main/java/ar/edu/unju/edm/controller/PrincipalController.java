package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

	@GetMapping({"/index","/home","/"})
		//es el unico que va con String, los demas van con ModelAndView
		//String es el tipo de retorno de la funcion
		public String getIndex() {
			return "index";
	}
	
}
