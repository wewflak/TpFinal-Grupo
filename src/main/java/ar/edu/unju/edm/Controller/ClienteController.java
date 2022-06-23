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

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Service.IClienteService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller
public class ClienteController {
	private static final Log SRT = LogFactory.getLog(ClienteController.class);
	@Autowired
	Cliente nuevoCliente;
	@Autowired
	IClienteService serviceclient;
	@GetMapping("/cargarcliente")//entrega clientes
	public ModelAndView addClient() {
		ModelAndView vista = new ModelAndView("cargarcliente");
		vista.addObject("cliente", nuevoCliente);

		vista.addObject("band", false);
		return vista;
		
	}
	
	@PostMapping("/guardarcliente")//recibe datos
	public String saveClient(@Valid @ModelAttribute ("cliente") Cliente clienteparaguardar, BindingResult resultado, Model model) {
		SRT.info("Ingresando al metodo guardar Cliente: "+clienteparaguardar.getDni());

		if(resultado.hasErrors()) {
			SRT.fatal("Error de validacion"+clienteparaguardar.getApellido()+clienteparaguardar.getContrasena()+clienteparaguardar.getFechadeN()+clienteparaguardar.getEmail()+clienteparaguardar.getNombre());
			model.addAttribute("cliente", clienteparaguardar);
			return "cargarcliente";
		}else {
		try {

			serviceclient.guardarCliente(clienteparaguardar);
			}
		catch(Exception error){
			model.addAttribute("formClienteErrorMessage", error.getMessage());
			model.addAttribute("cliente", clienteparaguardar); 
			SRT.error("No se pudo cargar"); 
			return "cargarcliente";
		}
		model.addAttribute("formClienteErrorMessage", "Cliente guardado correctamente");
		model.addAttribute("cliente", nuevoCliente);
		System.out.println(clienteparaguardar.getApellido()+clienteparaguardar.getEmail());
		return "index";
	}
	}
	
	@GetMapping("/mostrarclientes")
	public ModelAndView showclient() {
		ModelAndView vista = new ModelAndView("mostrarclientes");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("listaclientes", serviceclient.mostrarClientes());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/editarCliente/{IdCliente}")
	public ModelAndView editclient(Model model, @PathVariable(name="IdCliente")Integer IdCliente) throws Exception {
		Cliente clienteEncontrado = new Cliente();
		try {
			clienteEncontrado = serviceclient.buscarCliente(IdCliente);
		}catch(Exception e) {
			model.addAttribute("formClienteErrorMessage", e.getMessage());
		}
		ModelAndView encontrado = new ModelAndView("cargarcliente");
		encontrado.addObject("cliente", clienteEncontrado);
		SRT.error("cliente: " + clienteEncontrado);
		encontrado.addObject("band", true);
		return encontrado;
	}
	@PostMapping("Modificarcliente")
	public ModelAndView subClient(@Valid @ModelAttribute ("cliente") Cliente clientemodificar, Model model) {
		serviceclient.modificarCliente(clientemodificar);
		SRT.info("Ingresando al metodo guardar Cliente: "+clientemodificar.getApellido());
		ModelAndView vista = new ModelAndView ("mostrarclientes");
		vista.addObject("listclientes", serviceclient.mostrarClientes());
		vista.addObject("formClienteErrorMessage", "Cliente guardado correctamente");
		return vista;
	}
	@GetMapping("/eliminarCliente/{IdCliente}")
	public String deleteClient(@PathVariable(name="IdCliente")Integer IdCliente) {
		try {
		serviceclient.eliminarCliente(IdCliente);
		}catch(Exception error){
			SRT.error("No se pudo eliminar el Cliente");
			return "redirect:/cargarcliente";
		}
		return "redirect:/mostrarclientes";
	}
	
}
