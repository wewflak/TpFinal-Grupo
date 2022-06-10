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

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class ClienteController {
	private static final Log SRT = LogFactory.getLog(ClienteController.class);
	@Autowired
	Cliente nuevoCliente;
	@Autowired
	//IUsuarioService serviceuser;
	@GetMapping("/cargarusuario")//entrega usuarios
	public ModelAndView addUser() {
		ModelAndView vista = new ModelAndView("cargarusuario");
		vista.addObject("usuario", nuevoUsuario);

		vista.addObject("band", false);
		return vista;
		
	}
	
	@PostMapping("/guardarusuario")//recibe datos
	public String saveUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparaguardar, BindingResult resultado, Model model) {
		SRT.info("Ingresando al metodo guardar Usuario: "+usuarioparaguardar.getDni());
		if(resultado.hasErrors()) {
			SRT.fatal("Error de validacion"+usuarioparaguardar.getApellido());
			model.addAttribute("usuario", usuarioparaguardar);
			return "cargarusuario";
		}else {
		try {

			serviceuser.guardarUsuario(usuarioparaguardar);
			}
		catch(Exception error){
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			model.addAttribute("usuario", usuarioparaguardar); 
			SRT.error("No se pudo cargar"); 
			return "cargarusuario";
		}
		model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
		model.addAttribute("usuario", nuevoUsuario);
		System.out.println(usuarioparaguardar.getApellido()+usuarioparaguardar.getEmail());
		return "index";
	}
	}
	
	@GetMapping("/mostrarusuario")
	public ModelAndView showuser() {
		ModelAndView vista = new ModelAndView("mostrarusuario");
		SRT.error("ENTRANDOOOOOOOOOOOOOOOOOOOOO");
		vista.addObject("listausuarios", serviceuser.mostrarUsuarios());
		SRT.error("SALIENDOOOOOOOOOOOOOOOOOOOOOO");
		return vista;
	}
	@GetMapping("/editarUsuario/{dni}")
	public ModelAndView edituser(Model model, @PathVariable(name="dni")Long dni) throws Exception {
		Usuario usuarioEncontrado = new Usuario();
		try {
			usuarioEncontrado = serviceuser.buscarUsuario(dni);
		}catch(Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
		}
		ModelAndView encontrado = new ModelAndView("cargarusuario");
		encontrado.addObject("usuario", usuarioEncontrado);
		SRT.error("usuario: " + usuarioEncontrado);
		encontrado.addObject("band", true);
		return encontrado;
	}
	@PostMapping("ModificarUsuario")
	public ModelAndView subUser(@Valid @ModelAttribute ("Usuario") Usuario usuariomodificar, Model model) {
		serviceuser.modificarUsuario(usuariomodificar);
		SRT.info("Ingresando al metodo guardar Usuario: "+usuariomodificar.getApellido());
		ModelAndView vista = new ModelAndView ("mostrarusuario");
		vista.addObject("listaUsuario", serviceuser.mostrarUsuarios());
		vista.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		return vista;
	}
	@GetMapping("/eliminarUsuario/{dni}")
	public String deleteuser(@PathVariable(name="dni")Long dni) {
		try {
		serviceuser.eliminarUsuario(dni);
		}catch(Exception error){
			SRT.error("No se pudo eliminar el usuario");
			return "redirect:/cargarusuario";
		}
		return "redirect:/mostrarusuario";
	}
	
}
