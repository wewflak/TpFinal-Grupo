package ar.edu.unju.edm.Controller;

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

@Controller
public class ClientePeliculaController {
    private static final Log SRT = LogFactory.getLog(ClientePeliculaController.class);
    private Integer pos;
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
		view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
		pos= clientePeliculaNuevo.getIdClientePelicula();
		view.setViewName("redirect:/Comprobante");
		return view;
	}
	@GetMapping("/Comprobante")
	ModelAndView Comprobante(@Valid @ModelAttribute("unaEntrada") ClientePelicula clientePeliculaUltimo){
		ModelAndView view = new ModelAndView("Comprobante");
		ClientePelicula entradaComprobante = new ClientePelicula();
		view.addObject("Cliente", entradaComprobante.getCliente());
		view.addObject("Pelicula", entradaComprobante.getPelicula());
		view.addObject("unaEntrada", entradaComprobante.getIdClientePelicula());
		view.setViewName("Comprobante");
		return view;
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
	@GetMapping({ "/resena" })
	public ModelAndView addResenia() {
		// EMILIA.info("ingresando al metodo: Nuevo usuario");
		ModelAndView view = new ModelAndView("cargarResena");
		view.addObject("unaEntrada", clientePeliculaService.nuevoClientePelicula());
		view.addObject("clientes", clienteservice.mostrarClientes());
		view.addObject("peliculas", peliculaservice.mostrarPeliculas());
		view.addObject("band", false);
		return view;
	}

	@PostMapping("/guardarResenia")
	public ModelAndView saveComentario(@Valid @ModelAttribute("unaEntrada") ClientePelicula entradaparaguardar,BindingResult result) {
		ModelAndView view = new ModelAndView();
		if (result.hasErrors()) {
			SRT.fatal("Error durante el comentario");
			view.addObject("unaEntrada", entradaparaguardar);
			view.addObject("band", false);
			view.setViewName("cargarResena");
			return view;
		}
		try {
			clientePeliculaService.guardarClientePelicula(entradaparaguardar);
		} catch (Exception e) {
			view.addObject("formClientePeliculaErrorMessage", e.getMessage());
			view.addObject("unaEntrada", entradaparaguardar);
			view.addObject("band", false);
			view.setViewName("cargarResena");
			return view;
		}
		view.addObject("formClientePeliculaErrorMessage", "Usuario guardado correctamente");
		view.addObject("unCliente", clientePeliculaService.nuevoClientePelicula());
		view.addObject("band", false);
		view.setViewName("cargarResena");
		return view;
	}
}
