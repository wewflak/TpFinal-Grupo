package ar.edu.unju.edm.Service.IMP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.ClientePelicula;
import ar.edu.unju.edm.Repository.ClientePeliculaRepository;
import ar.edu.unju.edm.Service.IClientePeliculaService;

@Service
public class IClientePeliculaIMP implements IClientePeliculaService {

	@Autowired
	ClientePelicula clientePelicula;
	
	@Autowired
	ClientePeliculaRepository clientePeliculaRepository;
	
	@Override
	public ClientePelicula nuevoClientePelicula() {
		// TODO Auto-generated method stub
		return clientePelicula;
	}

	@Override
	public void guardarClientePelicula(ClientePelicula clientMovie) {
		// TODO Auto-generated method stub
			clientePeliculaRepository.save(clientePelicula);
	}

	@Override
	public void eliminarClientePelicula(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarClientePelicula(ClientePelicula clientMovie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClientePelicula> listarClientePelicula() {
		// TODO Auto-generated method stub
		return (List<ClientePelicula>) clientePeliculaRepository.findAll();
	}

	@Override
	public ClientePelicula buscarClientePelicula(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
