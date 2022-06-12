package ar.edu.unju.edm.Controller;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Service.IPeliculaService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller
public class PeliculaController {
    private static final Log SRT = LogFactory.getLog(ClienteController.class);
	@Autowired
	Pelicula nuevaPelicula;
	@Autowired
	IPeliculaService servicemovie;
	@GetMapping("/cargarpelicula")//entrega clientes
	public ModelAndView addMovie() {
		ModelAndView vista = new ModelAndView("cargarpelicula");
		vista.addObject("pelicula", nuevaPelicula);

		vista.addObject("band", false);
		return vista;
		
	}
	
	@PostMapping("/guardarpelicula")//recibe datos
	public String saveMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculaparaguardar, BindingResult resultado, Model model) {
		SRT.info("Ingresando al metodo guardar Cliente: "+peliculaparaguardar.getId());
		if(resultado.hasErrors()) {

			servicemovie.guardarPelicula(peliculaparaguardar);
			SRT.fatal("Error de validacion"+peliculaparaguardar.getNombre());
			model.addAttribute("pelicula", peliculaparaguardar);
			return "cargarpelicula";
		}else {
		try {

			servicemovie.guardarPelicula(peliculaparaguardar);
			}
		catch(Exception error){
			model.addAttribute("formclienteErrorMessage", error.getMessage());
			model.addAttribute("pelicula", peliculaparaguardar); 
			SRT.error("No se pudo cargar"); 
			return "cargarpelicula";
		}
		model.addAttribute("formclienteErrorMessage", "Pelicula guardado correctamente");
		model.addAttribute("pelicula", nuevaPelicula);
		System.out.println(peliculaparaguardar.getNombre()+peliculaparaguardar.getGenero());
		return "index";
	}
	}
	
	@GetMapping("/mostrarpeliculas")
	public ModelAndView showMovies() {
		ModelAndView vista = new ModelAndView("mostrarpeliculas");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("listapeliculas", servicemovie.mostrarPeliculas());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/editarpeliculas/{id}")
	public ModelAndView editclient(Model model, @PathVariable(name="id")Integer id) throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = servicemovie.buscarPelicula(id);
		}catch(Exception e) {
			model.addAttribute("formClienteErrorMessage", e.getMessage());
		}
		ModelAndView encontrado = new ModelAndView("cargarpelicula");
		encontrado.addObject("cliente", peliculaEncontrada);
		SRT.error("pelicula: " + peliculaEncontrada);
		encontrado.addObject("band", true);
		return encontrado;
	}
	@PostMapping("Modificarpelicula")
	public ModelAndView subMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculamodificar, Model model) {
		servicemovie.modificarPelicula(peliculamodificar);
		SRT.info("Ingresando al metodo guardar Pelicula: "+peliculamodificar.getNombre());
		ModelAndView vista = new ModelAndView ("mostrarpeliculas");
		vista.addObject("listadoPeliculas", servicemovie.mostrarPeliculas ());
		vista.addObject("formclienteErrorMessage", "Cliente guardado correctamente");
		return vista;
	}
	@GetMapping("/eliminarpelicula/{id}")
	public String deleteuser(@PathVariable(name="id")Integer id) {
		try {
		servicemovie.eliminarPelicula(id);
		}catch(Exception error){
			SRT.error("No se pudo eliminar la pelicula");
			return "redirect:/cargarpelicula";
		}
		return "redirect:/mostrarpeliculas";
	}
	
}
