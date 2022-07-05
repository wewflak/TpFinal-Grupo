package ar.edu.unju.edm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.Model.Cliente;
@Controller
public class PrincipalController {
	
	@Autowired
	Cliente Client;
	
	@GetMapping({"/", "/index", "/home"})
	public String getIndex() {

		return "index";
	}
	@GetMapping({"/login", "/formLogin", "/ingreso"})
	public String getLogin() {
		return "formLogin";
	}
}
