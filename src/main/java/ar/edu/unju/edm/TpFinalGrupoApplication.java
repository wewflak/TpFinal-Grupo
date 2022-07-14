package ar.edu.unju.edm;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Service.IClienteService;

@SpringBootApplication
public class TpFinalGrupoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TpFinalGrupoApplication.class, args);
	}

	@Autowired
	Cliente cliente;
	
	@Autowired
	IClienteService clienteService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		
		cliente.setDni((long) 44754210);
	    cliente.setNombre("Brisa");
		cliente.setApellido("Ontiveros");
		cliente.setContrasena("123");
		cliente.setFechadeN(LocalDate.now());
		cliente.setEmail("brisa@gmail.com");
		cliente.setTipo("ADMIN");
		clienteService.guardarCliente(cliente);
	}

}
