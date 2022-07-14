package ar.edu.unju.edm.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Resenia;
@Service

public interface IReseniaService {
	public Resenia nuevaResenia();
	public void guardarResenia(Resenia review);
	public List<Resenia> mostrarResenias();
	public void eliminarResenia(Integer idResenia) throws Exception;
	public List<Resenia> mostrarReseniasPorPelicula(Integer id);
	public List<Resenia> mostrarReseniasPorCliente(Long dni);
	public void modificarResenias(Resenia review);
	public Resenia buscarResenia(Integer idResenia) throws Exception;


}
