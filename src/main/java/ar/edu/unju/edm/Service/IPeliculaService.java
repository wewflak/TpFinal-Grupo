package ar.edu.unju.edm.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Pelicula;

@Service
public interface IPeliculaService {
	/*El service guarda el usuario*/
	public void guardarPelicula (Pelicula movie);
	//Muestra usuarios
	public List<Pelicula> mostrarPeliculas();
	//Elminar Usuario
	public void eliminarPelicula(Integer id) throws Exception;
	//Modifica los datos del usuario
	public void modificarPelicula(Pelicula movie);
	//Busca usuarios
	public Pelicula buscarPelicula(Integer id) throws Exception;
	
}