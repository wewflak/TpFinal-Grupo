package ar.edu.unju.edm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Repository.PeliculaRepository;
import ar.edu.unju.edm.Service.IPeliculaService;
@Controller
public class PrincipalController {
	
	@Autowired
	Pelicula Movie;
	@Autowired
	IPeliculaService movieserv;
	
	@GetMapping({"/", "/index", "/home"})
	public ModelAndView getIndex() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("listapeliculas",movieserv.mostrarPeliculas());
		return view;
	}
	@GetMapping({"/login", "/formLogin", "/ingreso"})
	public String getLogin() {
		return "formLogin";
	}
}
