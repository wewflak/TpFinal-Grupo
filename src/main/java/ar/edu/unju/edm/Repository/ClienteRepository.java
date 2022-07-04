package ar.edu.unju.edm.Repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.Model.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	public List<Cliente> findByEstado(Boolean estado);
}
