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
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			clientePeliculaRepository.save(clientMovie);
	}

	@Override
	public void eliminarClientePelicula(Integer idClientePelicula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarClientePelicula(ClientePelicula clientMovie) {
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		clientePeliculaRepository.save(clientMovie);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClientePelicula> listarClientePelicula() {
		// TODO Auto-generated method stub
		return (List<ClientePelicula>) clientePeliculaRepository.findAll();
	}

	@Override
	public ClientePelicula buscarClientePelicula(Integer idClientePelicula) throws Exception {
		// TODO Auto-generated method stub
		ClientePelicula entradaEncontrada = new ClientePelicula();
		entradaEncontrada=clientePeliculaRepository.findById(idClientePelicula).orElseThrow(()->new Exception("Cliente Pelicula No encontrada"));
		return entradaEncontrada;
	}

}
