package ar.edu.unju.edm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.Model.ClientePelicula;

@Repository
public interface ClientePeliculaRepository extends CrudRepository<ClientePelicula, Integer> {

}
