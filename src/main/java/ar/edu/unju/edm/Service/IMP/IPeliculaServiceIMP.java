package ar.edu.unju.edm.Service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Repository.PeliculaRepository;
import ar.edu.unju.edm.Service.IPeliculaService;
import ar.edu.unju.edm.Util.ListadoPeliculas;
@Service
public class IPeliculaServiceIMP implements IPeliculaService {
	@Autowired
	PeliculaRepository peliculaRepository;
	@Autowired

	ListadoPeliculas lista;
	
	@Override
	public void guardarPelicula(Pelicula peliculaparaguardar) {
		// TODO Auto-generated method stub
		peliculaparaguardar.setEstado(true);
		peliculaRepository.save(peliculaparaguardar);
	}

	@Override
	public List<Pelicula> mostrarPeliculas() {
		// TODO Auto-generated method stub
		List<Pelicula> activos = new ArrayList<>();
		List<Pelicula> activos2 = new ArrayList<>();
		activos=(List<Pelicula>) peliculaRepository.findAll();
			for(int i=0; i< activos.size(); i++) {
				if(activos.get(i).getEstado()==true){
					activos2.add(activos.get(i));			
				}
		}
		return activos2;
	}

	@Override
	public void eliminarPelicula(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Pelicula auxiliar = new Pelicula();
		auxiliar = buscarPelicula(id);
		auxiliar.setEstado(false);
		peliculaRepository.save(auxiliar);
	}

	@Override
	public void modificarPelicula(Pelicula movie) {
		// TODO Auto-generated method stub
		movie.setEstado(true);
		peliculaRepository.save(movie);
	}

	@Override
	public Pelicula buscarPelicula(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Pelicula peliculaencontrado = new Pelicula();
		peliculaencontrado=peliculaRepository.findById(id).orElseThrow(()->new Exception("Pelicula No encontrada"));
		return peliculaencontrado;
	}

}