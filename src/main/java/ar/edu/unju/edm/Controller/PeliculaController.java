package ar.edu.unju.edm.Controller;
import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Service.IPeliculaService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller
public class PeliculaController {
    private static final Log SRT = LogFactory.getLog(PeliculaController.class);
	@Autowired
	Pelicula nuevaPelicula;
	@Autowired
	IPeliculaService servicemovie;
	@GetMapping("/cargarpelicula")//entrega Peliculas
	public ModelAndView addMovie() {
		ModelAndView vista = new ModelAndView("cargarpelicula");
		vista.addObject("pelicula", nuevaPelicula);
		vista.addObject("band", false);
		SRT.info("Cont");
		return vista;
		
	}
	
	@PostMapping(value="/guardarpelicula", consumes = "multipart/form-data")//recibe datos
	public String saveMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculaparaguardar, BindingResult resultado, @RequestParam("file") MultipartFile file, Model model) {
		SRT.info("Ingresando al metodo guardar pelicula: "+ file.getSize());
		if(resultado.hasErrors()) {
			
			SRT.fatal("Error de validacion"+peliculaparaguardar.getNombre());
			model.addAttribute("pelicula", peliculaparaguardar);
			return "cargarpelicula";
		}else {
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculaparaguardar.setImagen(base64);
			peliculaparaguardar.setEstado(true);
			servicemovie.guardarPelicula(peliculaparaguardar); 
			SRT.info(peliculaparaguardar.getId());
			}
		catch(Exception error){
			model.addAttribute("formPeliculaErrorMessage", error.getMessage());
			model.addAttribute("pelicula", peliculaparaguardar); 
			SRT.error("No se pudo cargar"); 
			System.out.println("PRoblemas");
			return "cargarpelicula";
		}
		model.addAttribute("formPeliculaErrorMessage", "Pelicula guardado correctamente");
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
	
//    AGREGUE ESTO PARA PODER MOSTRAR las peliculas sin editar
	//Basicamente hice lo mismo que la anterior getMapping pero ahora esto conecta a las vistas
	//peliculas y mostrarpeliculasclientes
	@GetMapping("/mostrarpeliculasclientes")
	public ModelAndView showMovies1() {
		ModelAndView vista= new ModelAndView("peliculas");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("listapeliculas", servicemovie.mostrarPeliculas());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/peliculasclientes")
	public ModelAndView showMovies2() {
		ModelAndView vista= new ModelAndView("mostrarpeliculasclientes");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("listapeliculas", servicemovie.mostrarPeliculas());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	
	@GetMapping("/editarpeliculas/{id}")
	public ModelAndView editmovie(Model model, @PathVariable(name="id")Integer id) throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = servicemovie.buscarPelicula(id);
		}catch(Exception e) {
			model.addAttribute("formPeliculaErrorMessage", e.getMessage());
		}
		SRT.info("los jajas");
		ModelAndView encontrado = new ModelAndView("cargarpelicula");
		encontrado.addObject("pelicula", peliculaEncontrada);
		SRT.error("pelicula: " + peliculaEncontrada);
		encontrado.addObject("band", true);
		return encontrado;
	}
	@PostMapping("Modificarpelicula")
	public ModelAndView subMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculamodificar, Model model) {
		servicemovie.modificarPelicula(peliculamodificar);
		SRT.info("Ingresando al metodo guardar Pelicula: "+peliculamodificar.getNombre());
		ModelAndView vista = new ModelAndView ("mostrarpeliculas");
		vista.addObject("listapeliculas", servicemovie.mostrarPeliculas ());
		SRT.info(peliculamodificar.getNombre());
		vista.addObject("formPeliculaErrorMessage", "Pelicula guardado correctamente");
		return vista;
	}
	@GetMapping("/eliminarPelicula/{id}")
	public String deleteMovie(@PathVariable(name="id")Integer id) {
		try {
		servicemovie.eliminarPelicula(id);
		}catch(Exception error){
			SRT.error("No se pudo eliminar la pelicula");
			return "redirect:/cargarpelicula";
		}
		return "redirect:/mostrarpeliculas";
	}
	
}