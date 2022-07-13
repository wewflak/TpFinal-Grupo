package ar.edu.unju.edm.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Resenia;
@Service

public interface IReseniaService {
	public Resenia nuevaResenia();
	public void guardarResenia(Resenia review);
	public List<Resenia> mostrarResenias();
	public void eliminarReaenia(Integer idResenia) throws Exception;
	public List<Resenia> mostrarReseniasPorPelicula(Integer id);
	public Resenia buscarResenia(Integer idResenia) throws Exception;


}
