package ar.edu.unju.edm.Controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Model.Resenia;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Service.IPeliculaService;
import ar.edu.unju.edm.Service.IReseniaService;

@Controller
public class ReseniaController {
    private static final Log SRT = LogFactory.getLog(ClientePeliculaController.class);

    @Autowired
    Pelicula pelicula;
    
	@Autowired
	Resenia nuevaResenia;
	
	@Autowired
	IReseniaService reviewService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IPeliculaService peliculaservice;

	@GetMapping("/cargarResena/{id}")
		public ModelAndView addResenia(Model model, @PathVariable(name="id")Integer id, Authentication auth) throws Exception{
		Pelicula peliculaEncontrada = new Pelicula();
		Cliente clienteEncontrado = new Cliente();
		SRT.info("Entrando!!!!!!!!!!!!!!");
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(id);
			clienteEncontrado = clienteservice.buscarCliente(Long.parseLong(auth.getName()));
			SRT.info("Se encontro la pelicula"+clienteEncontrado.getNombre());
		}catch(Exception e) {
			model.addAttribute("formReseniaErrorMessage", e.getMessage());
		}
		ModelAndView view = new ModelAndView("cargarResena");
		SRT.info(peliculaEncontrada.getId());
		SRT.info(auth.getName());
		view.addObject("unaResenia", reviewService.nuevaResenia());
		view.addObject("cliente", clienteEncontrado);
		view.addObject("pelicula", peliculaEncontrada);
		view.addObject("band2", false);
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
			view.addObject("band2", false);
			view.setViewName("cargarResena");
			return view;
		}
			view.addObject("formReseniaErrorMessage", "Comentario guardado correctamente");
			view.addObject("unaResenia", reviewService.nuevaResenia());
			SRT.info("Se logro");
			view.setViewName("mostrarResena");
			return view;
	}
	@GetMapping("/mostrarResena/{id}")
	public ModelAndView showMovies(@PathVariable (name="id") Integer id) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarResena");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(id);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("mostrarpeliculascliente");
			vista.addObject("formReseniaErrorMessage", e.getMessage());
		}
		vista.addObject("listaresenas",reviewService.mostrarReseniasPorPelicula(peliculaEncontrada.getId()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/mostrarResenaAdmin/{id}")
	public ModelAndView showReviewsMovieAdmin(@PathVariable (name="id") Integer id) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarResenaAdmin");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(id);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("index");
			vista.addObject("formReseniaErrorMessage", e.getMessage());
		}
		vista.addObject("listaresenas",reviewService.mostrarReseniasPorPelicula(peliculaEncontrada.getId()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/mostrarResena2/{dni}")
	public ModelAndView showClientReviews(@PathVariable (name="dni") Long dni) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarResena2");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = clienteservice.buscarCliente(dni);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("index");
			vista.addObject("formReseniaErrorMessage", e.getMessage());
		}
		vista.addObject("listaresenas",reviewService.mostrarReseniasPorCliente(clienteEncontrado.getDni()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/mostrarResenaAdmin2/{dni}")
	public ModelAndView showClientReviewsAdmin(@PathVariable (name="dni") Long dni) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarResenaAdmin2");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = clienteservice.buscarCliente(dni);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("index");
			vista.addObject("formReseniaErrorMessage", e.getMessage());
		}
		vista.addObject("listaresenas",reviewService.mostrarReseniasPorCliente(clienteEncontrado.getDni()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/eliminarResena/{idComentario}")
	public String deleteMovie(@PathVariable(name="idComentario")Integer idComentario) {
		try {
		reviewService.eliminarResenia(idComentario);
		}catch(Exception error){
			SRT.error("No se pudo eliminar la resena");
			return "redirect:/cargarResena";
		}
		return "redirect:/mostrarclientes";
	}
	
	@GetMapping("/editarResena/{idComentario}")
	public ModelAndView editReview(Model model, @PathVariable(name="idComentario")Integer idComentario) throws Exception{
		Resenia reseniaEncontrada = new Resenia();
		ModelAndView encontrado = new ModelAndView();
		try {
			reseniaEncontrada = reviewService.buscarResenia(idComentario);
			SRT.info(reseniaEncontrada.getFechadeCom());
		}catch(Exception e) {
			encontrado.addObject("formReseniaErrorMessage", e.getMessage());
		}
		encontrado.setViewName("cargarResena");
		encontrado.addObject("Resena", reseniaEncontrada);
		encontrado.addObject("band2", true);
		SRT.error("Resenia: " + reseniaEncontrada);
		return encontrado;
	}
	
	@PostMapping("ModificarResena")
	public ModelAndView subReview(@Valid @ModelAttribute ("resenia") Resenia reseniamodificar, Model model, Authentication auth) {
		reviewService.modificarResenias(reseniamodificar);
		SRT.info("Ingresando al metodo guardar Resena: "+reseniamodificar.getFechadeCom());
		ModelAndView vista = new ModelAndView ("mostrarResena2");
		vista.addObject("listaresenas", reviewService.mostrarReseniasPorCliente(Long.parseLong(auth.getName())));
		vista.addObject("formReseniaErrorMessage", "Resenia guardada correctamente");
		return vista;
	}
}
