package ar.edu.unju.edm.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PrincipalController {
	@GetMapping({"/", "/index", "/home"})
	public String getIndex() {
		return "index";
	}
}
