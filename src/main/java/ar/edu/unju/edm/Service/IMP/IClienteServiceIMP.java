package ar.edu.unju.edm.Service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Repository.ClienteRepository;
import ar.edu.unju.edm.Service.IClienteService;
import ar.edu.unju.edm.Util.ListadoClientes;
@Service
public class IClienteServiceIMP implements IClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired

	ListadoClientes lista;
	
	@Override
	public void guardarCliente(Cliente clienteparaguardar) {
		// TODO Auto-generated method stub
		clienteparaguardar.setEstado(true);
		clienteRepository.save(clienteparaguardar);
	}

	@Override
	public List<Cliente> mostrarClientes() {
		// TODO Auto-generated method stub
		List<Cliente> activos = new ArrayList<>();
		List<Cliente> activos2 = new ArrayList<>();
		activos=(List<Cliente>) clienteRepository.findAll();
			for(int i=0; i< activos.size(); i++) {
				if(activos.get(i).getEstado()==true){
					activos2.add(activos.get(i));			
				}
		}
		return activos2;
	}

	@Override
	public void eliminarCliente(Integer IdCliente) throws Exception {
		// TODO Auto-generated method stub
		Cliente auxiliar = new Cliente();
		auxiliar = buscarCliente(IdCliente);
		auxiliar.setEstado(false);
		clienteRepository.save(auxiliar);
	}

	@Override
	public void modificarCliente(Cliente client) {
		// TODO Auto-generated method stub
		client.setEstado(true);
		clienteRepository.save(client);
	}

	@Override
	public Cliente buscarCliente(Integer IdCliente) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteencontrado = new Cliente();
		clienteencontrado=clienteRepository.findById(IdCliente).orElseThrow(()->new Exception("Cliente No encontrado"));
		return clienteencontrado;
	}

}
