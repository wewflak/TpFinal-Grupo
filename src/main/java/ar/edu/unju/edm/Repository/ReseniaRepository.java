package ar.edu.unju.edm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.Model.Resenia;

@Repository
public interface ReseniaRepository extends CrudRepository<Resenia, Integer>{
//extends CrudRepository<ClientePelicula, Integer>
}
