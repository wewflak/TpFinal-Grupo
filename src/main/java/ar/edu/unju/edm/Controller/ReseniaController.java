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

import ar.edu.unju.edm.Model.Resenia;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Service.IPeliculaService;
import ar.edu.unju.edm.Service.IReseniaService;

@Controller
public class ReseniaController {
    private static final Log SRT = LogFactory.getLog(ClientePeliculaController.class);

	@Autowired
	Resenia nuevaResenia;
	
	@Autowired
	IReseniaService reviewService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IPeliculaService peliculaservice;

	@GetMapping("/cargarResena")
		public ModelAndView addResenia() {
		
		ModelAndView view = new ModelAndView("cargarResena");
		view.addObject("unaResenia", reviewService.nuevaResenia());
		view.addObject("clientes", clienteservice.mostrarClientes());
		view.addObject("peliculas", peliculaservice.mostrarPeliculas());
		return view;
		}
	@PostMapping("/guardarResenia")
	public ModelAndView saveResenia(@Valid @ModelAttribute("unaResenia") Resenia reseniaNueva, BindingResult resultado) {
		ModelAndView view = new ModelAndView();
		SRT.info("Entrandooo"+reseniaNueva.getFechadeCom());
		if(resultado.hasErrors()) {
			SRT.info("Antes de entrar al error");
			view.setViewName("cargarResena");
			view.addObject("unaResenia", reseniaNueva);
			return view;
		}
		try {
			SRT.info("Hola al try"+reseniaNueva.getFechadeCom());
			reviewService.guardarResenia(reseniaNueva);
			
		}catch(Exception e) {
			SRT.info("Entrandooo al catch"+reseniaNueva.getFechadeCom());
			view.addObject("formReseniaErrorMessage", e.getMessage());
			view.addObject("unaResenia", reviewService.nuevaResenia());
			SRT.error("Saliedno del metodo");
			view.setViewName("cargarResena");
			return view;
		}
			view.addObject("formReseniaErrorMessage", "Comentario guardado correctamente");
			view.addObject("unaResenia", reviewService.nuevaResenia());
			view.setViewName("cargarResena");
			return view;
	}
}
