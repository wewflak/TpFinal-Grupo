package ar.edu.unju.edm.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.Model.ClientePelicula;
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

		//clientePeliculaService.guardarClientePelicula(clientePeliculaNuevo);
		SRT.info("holaaaaaaaaaaaaaa"+clientePeliculaNuevo.getFechaCompra());
		if(resultado.hasErrors()) {
			SRT.info("holaaaaaaaaaaaaaa antes de guardar");
			view.setViewName("cargarentrada");
			view.addObject("unaEntrada", clientePeliculaNuevo);
			return view;
		}
		try {
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
		view.setViewName("generadoComprobante");
		return view;
	}
//	@GetMapping("/generadoComprobante")
//	ModelAndView Comprobante(@Valid @ModelAttribute("unaEntrada") ClientePelicula clientePeliculaNuevo, Model model){
//		ModelAndView view = new ModelAndView("generadoComprobante");
//		view.addObject("Cliente", clientePeliculaNuevo.getCliente());
//		SRT.error(clientePeliculaNuevo.getCliente().getDni());
//		view.addObject("Pelicula", clientePeliculaNuevo.getPelicula());
//		SRT.error(clientePeliculaNuevo.getPelicula().getNombre());
//		view.addObject("unaEntrada", clientePelicula);
//		view.addObject("band2", false);
//		view.setViewName("generadoComprobante");
//		return view;
//	}

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