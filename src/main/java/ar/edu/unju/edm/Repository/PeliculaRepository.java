package ar.edu.unju.edm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.Model.Pelicula;

@Repository
public interface PeliculaRepository extends CrudRepository<Pelicula, Integer> {
    
}
