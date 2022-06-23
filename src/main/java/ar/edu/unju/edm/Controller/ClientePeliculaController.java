package ar.edu.unju.edm.Controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.ClientePelicula;
import ar.edu.unju.edm.Service.IClientePeliculaService;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Service.IPeliculaService;

@Controller
public class ClientePeliculaController {
    private static final Log SRT = LogFactory.getLog(ClientePeliculaController.class);
	
	@Autowired
	IClientePeliculaService clientePeliculaService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IPeliculaService peliculaservice;
	
	@GetMapping("/cargarentrada")
	public ModelAndView addEntrada() {
		SRT.info("Ingresando al metodo");

		ModelAndView view = new ModelAndView("cargarentrada");
		view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
		view.addObject("clientes", clienteservice.mostrarClientes());
		view.addObject("peliculas", peliculaservice.mostrarPeliculas());
		return view;
	}
	@PostMapping("/guardarEntrada")
	public ModelAndView saveEntrada(@Valid @ModelAttribute("unaEntrada") ClientePelicula clientePeliculaNuevo, BindingResult resultado) {
		ModelAndView view = new ModelAndView();

		clientePeliculaService.guardarClientePelicula(clientePeliculaNuevo);
		if(resultado.hasErrors()) {
			view.setViewName("cargarentrada");
			view.addObject("unaEntrada", clientePeliculaNuevo);
			return view;
		}
		try {
			clientePeliculaService.guardarClientePelicula(clientePeliculaNuevo);
		}catch(Exception e) {
			view.addObject("formClientePeliculaErrorMessage", e.getMessage());
			view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
			SRT.error("Saliendo del metodo");
			view.setViewName("cargarentrada");
			return view;
		}
		view.addObject("formClientePeliculaErrorMessage", "Relacion guardada correctamete");
		view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
		view.setViewName("cargarentrada");
		return view;
	}
}
