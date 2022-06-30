package ar.edu.unju.edm.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Cliente;
@Service
public interface IClienteService {
	/*El service guarda el usuario*/
	public void guardarCliente (Cliente client);
	//Muestra usuarios
	public List<Cliente> mostrarClientes();
	//Elminar Usuario
	public void eliminarCliente(Long dni) throws Exception;
	//Modifica los datos del usuario
	public void modificarCliente(Cliente client);
	//Busca usuarios
	public Cliente buscarCliente(Long dni) throws Exception;
}
