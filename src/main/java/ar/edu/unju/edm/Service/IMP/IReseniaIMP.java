package ar.edu.unju.edm.Service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Pelicula;
import ar.edu.unju.edm.Model.Resenia;
import ar.edu.unju.edm.Repository.ReseniaRepository;
import ar.edu.unju.edm.Service.IReseniaService;

@Service
public class IReseniaIMP implements IReseniaService {

	@Autowired
	Resenia resenia;
	
	@Autowired
	ReseniaRepository reseniaRepository;

	@Override
	public Resenia nuevaResenia() {
		// TODO Auto-generated method stub
		return resenia;
	}
	
	@Override
	public void guardarResenia(Resenia review) {
		// TODO Auto-generated method stub
		review.setEstado(true);
		reseniaRepository.save(review);
	}

	@Override
	public List<Resenia> mostrarResenias() {
		// TODO Auto-generated method stub
		List<Resenia> activos = new ArrayList<>();
		List<Resenia> activos2 = new ArrayList<>();
		activos=(List<Resenia>)reseniaRepository.findAll();
			for (int i=0; i<activos.size();i++) {
				if(activos.get(i).getEstado()==true) {
					activos2.add(activos.get(i));
				}
			}
		return activos2;
	}
//	@Override
//	public List<Pelicula> mostrarPeliculas() {
//		// TODO Auto-generated method stub
//		List<Pelicula> activos = new ArrayList<>();
//		List<Pelicula> activos2 = new ArrayList<>();
//		activos=(List<Pelicula>) peliculaRepository.findAll();
//			for(int i=0; i< activos.size(); i++) {
//				if(activos.get(i).getEstado()==true){
//					activos2.add(activos.get(i));			
//				}
//		}
//		return activos2;
//	}
	@Override
	public List<Resenia> mostrarReseniasPorPelicula(Integer id){
		//Pelicula nuevaPelicula;
		List<Resenia> todos = new ArrayList<>();
		List<Resenia> filtrado = new ArrayList<>();
		todos=(List<Resenia>) reseniaRepository.findAll();
		for(int i=0; i<todos.size();i++) {
			System.out.println(todos.get(i).getPelicula().getNombre());
			if( todos.get(i).getEstado()==true && todos.get(i).getPelicula().getId()==id) {
				filtrado.add(todos.get(i));
			}
		}
		return filtrado;
	}

	@Override
	public List<Resenia> mostrarReseniasPorCliente(Long dni){
		//Pelicula nuevaPelicula;
		List<Resenia> todos = new ArrayList<>();
		List<Resenia> filtrado = new ArrayList<>();
		todos=(List<Resenia>) reseniaRepository.findAll();
		for(int i=0; i<todos.size();i++) {
			System.out.println(todos.get(i).getCliente().getNombre());
			if(todos.get(i).getEstado()==true && todos.get(i).getCliente().getDni()==dni) {
				filtrado.add(todos.get(i));
			}
		}
		return filtrado;
	}
	
	@Override
	public void eliminarResenia(Integer idResenia) throws Exception {
		// TODO Auto-generated method stub
		Resenia auxiliar = new Resenia();
		auxiliar = buscarResenia(idResenia);
		auxiliar.setEstado(false);
		reseniaRepository.save(auxiliar);
	}

	@Override
	public Resenia buscarResenia(Integer idResenia) throws Exception {
		// TODO Auto-generated method stub
		Resenia reseniaEncontrada = new Resenia();
		reseniaEncontrada=reseniaRepository.findById(idResenia).orElseThrow(()->new Exception("Resenia no encontrada"));
		return reseniaEncontrada;
	}

	@Override
	public void modificarResenias(Resenia review) {
		review.setEstado(true);
		reseniaRepository.save(review);
		
	}
	
}
