package ar.edu.unju.edm.Controller;

import java.util.List;

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
import ar.edu.unju.edm.Model.ClientePelicula;
import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Service.IClientePeliculaService;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Service.IPeliculaService;
import ar.edu.unju.edm.Util.ListadoClientePelicula;

@Controller
public class ClientePeliculaController {
    private static final Log SRT = LogFactory.getLog(ClientePeliculaController.class);
    private Integer pos;
    
    @Autowired
    ListadoClientePelicula listClientMovie;
	@Autowired
	IClientePeliculaService clientePeliculaService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IPeliculaService peliculaservice;

	@GetMapping("/cargarentrada/{id}")
	public ModelAndView addEntrada(Model model, @PathVariable(name="id") Integer id, Authentication auth) throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
		Cliente clienteEncontrado = new Cliente();
		SRT.info("Ingresando al metodo");
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(id);
			clienteEncontrado = clienteservice.buscarCliente(Long.parseLong(auth.getName()));
			SRT.info("Se encontro la pelicula"+clienteEncontrado.getNombre());
		}catch(Exception e) {
			ModelAndView view = new ModelAndView("cargarentrada");
			view.addObject("formClientePeliculaErrorMessage", e.getMessage());
		}
		ModelAndView view = new ModelAndView("cargarentrada");
		view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
		view.addObject("cliente", clienteEncontrado);
		view.addObject("pelicula", peliculaEncontrada);
		return view;
	}
	@PostMapping("/guardarEntrada")
	public ModelAndView saveEntrada(@Valid @ModelAttribute("unaEntrada") ClientePelicula clientePeliculaNuevo, BindingResult resultado, Authentication auth) {
		ModelAndView view = new ModelAndView();
		Pelicula peliculaEncontrada = new Pelicula();
		Cliente clienteEncontrado = new Cliente();
		//clientePeliculaService.guardarClientePelicula(clientePeliculaNuevo);
		SRT.info("holaaaaaaaaaaaaaa"+clientePeliculaNuevo.getFechaCompra());
		if(resultado.hasErrors()) {
			SRT.info("holaaaaaaaaaaaaaa antes de guardar");
			view.setViewName("cargarentrada");
			view.addObject("unaEntrada", clientePeliculaNuevo);
			return view;
		}
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(clientePeliculaNuevo.getPelicula().getId());
			clienteEncontrado = clienteservice.buscarCliente(Long.parseLong(auth.getName()));
			SRT.info("holaaaaaaaaaaaaaa"+clientePeliculaNuevo.getFechaCompra());
			clientePeliculaService.guardarClientePelicula(clientePeliculaNuevo);
		}catch(Exception e) {
			SRT.info("holaaaaaaaaaaaaaa error al guardar");
			view.addObject("formClientePeliculaErrorMessage", e.getMessage());
			view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
			SRT.error("Saliendo del metodo");
			view.setViewName("cargarentrada");
			return view;
		}
		view.addObject("formClientePeliculaErrorMessage", "Relacion guardada correctamente");
		view.addObject("unaEntrada", clientePeliculaNuevo);
		view.addObject("cliente", clienteEncontrado);
		view.addObject("pelicula", peliculaEncontrada);
		view.setViewName("generadoComprobante");
		return view;
	}
	@GetMapping("/mostrarEntradas/{dni}")
	public ModelAndView showClientTickets(@PathVariable (name="dni") Long dni) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarEntradas");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = clienteservice.buscarCliente(dni);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("mostrarpeliculascliente");
			vista.addObject("formClientePeliculaErrorMessage", e.getMessage());
		}
		vista.addObject("listaEntradas",clientePeliculaService.mostrarEntradasPorCliente(clienteEncontrado.getDni()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/mostrarEntradasPelicula/{id}")
	public ModelAndView showTicketsMovieAdmin(@PathVariable (name="id") Integer id) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarEntradasAdmin");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = peliculaservice.buscarPelicula(id);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("index");
			vista.addObject("formClientePeliculaErrorMessage", e.getMessage());
		}
		vista.addObject("listaEntradas",clientePeliculaService.mostrarEntradasPorPelicula(peliculaEncontrada.getId()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/mostrarEntradasCliente/{dni}")
	public ModelAndView showClientTicketsAdmin(@PathVariable (name="dni") Long dni) throws Exception {
		ModelAndView vista= new ModelAndView("mostrarEntradasAdmin2");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = clienteservice.buscarCliente(dni);
			SRT.info("Se encontro la pelicula");
			
		}catch(Exception e) {
			vista.setViewName("index");
			vista.addObject("formClientePeliculaErrorMessage", e.getMessage());
		}
		vista.addObject("listaEntradas",clientePeliculaService.mostrarEntradasPorCliente(clienteEncontrado.getDni()));
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/editarPelicula/{idClientePelicula}")
	public ModelAndView editRelation(Model model, @PathVariable(name="idClientePelicula")Integer idClientePelicula)throws Exception{
		ClientePelicula relacionEncontrada = new ClientePelicula();
		try{
			relacionEncontrada = clientePeliculaService.buscarClientePelicula(idClientePelicula);
		}catch (Exception error){
			model.addAttribute("formClientePeliculaErrorMessage", error.getMessage());
		}
		ModelAndView encontrado = new ModelAndView("Resena");
		encontrado.addObject("relacion", relacionEncontrada);
		encontrado.addObject("clientes", clienteservice.mostrarClientes());
		encontrado.addObject("peliculas", peliculaservice.mostrarPeliculas());
		SRT.error("Relacion: " + relacionEncontrada);
		return encontrado;
	}
	@PostMapping("Modificarrelacion")
	public ModelAndView subRelation(@Valid @ModelAttribute ("unaEntrada") ClientePelicula clientePeliculamodificar, Model model){
		clientePeliculaService.modificarClientePelicula(clientePeliculamodificar);
		SRT.info("Ingresando al metodo guardar entrada: " + clientePeliculamodificar.getPelicula().getNombre());
		ModelAndView vista = new ModelAndView("Resena");
		vista.addObject("formClientePeliculaErrorMessage", "Resena guardada correctamente");
		return vista;
		
	}
}