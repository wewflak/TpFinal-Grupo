package ar.edu.unju.edm.Controller;
import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Service.IClientePeliculaService;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Service.IPeliculaService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller //ESTO DEFINE
public class PeliculaController {
    private static final Log SRT = LogFactory.getLog(PeliculaController.class);
    
	@Autowired
	IClienteService clienteservice;
	
    
	@Autowired //Creando un objetos nuevo lo tenes que instalciar 
	Pelicula nuevaPelicula;
	@Autowired
	Pelicula unaResenia;
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
	public ModelAndView showMovies1(Authentication auth) {
		ModelAndView vista= new ModelAndView("peliculas");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = clienteservice.buscarCliente(Long.parseLong(auth.getName()));
		}catch(Exception e) {
			ModelAndView vista1 = new ModelAndView("peliculas");
			vista1.addObject("formPeliculaErrorMessage", e.getMessage());
		}
		vista.addObject("listapeliculas", servicemovie.mostrarPeliculas());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("cliente", clienteEncontrado);
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
	@PostMapping(value="/Modificarpelicula", consumes = "multipart/form-data")
	public ModelAndView subMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculamodificar, @RequestParam("file") MultipartFile file, Model model, BindingResult resultado) {
		SRT.info("Ingresando al metodo guardar pelicula: "+ file.getSize());
		if(resultado.hasErrors()) {
			ModelAndView view = new ModelAndView("cargarpelicula");
			SRT.fatal("Error de validacion"+peliculamodificar.getNombre());
			view.addObject("pelicula", peliculamodificar);
			return view;
		}else {
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculamodificar.setImagen(base64);
			peliculamodificar.setEstado(true);
			servicemovie.modificarPelicula(peliculamodificar); 
			SRT.info(peliculamodificar.getId());
			}
		catch(Exception error){
			ModelAndView view = new ModelAndView("cargarpelicula");
			view.addObject("formPeliculaErrorMessage", error.getMessage());
			view.addObject("pelicula", peliculamodificar); 
			SRT.error("No se pudo cargar"); 
			System.out.println("Problemas");
			return view;
		}
		ModelAndView view = new ModelAndView("mostrarpeliculas");
		view.addObject("formPeliculaErrorMessage", "Pelicula guardado correctamente");
		view.addObject("listapeliculas", servicemovie.mostrarPeliculas());
		System.out.println(peliculamodificar.getNombre()+peliculamodificar.getGenero());
		return view;
	}
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