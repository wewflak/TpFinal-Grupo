package ar.edu.unju.edm.Service.IMP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		reseniaRepository.save(review);
	}

	@Override
	public List<Resenia> mostrarResenias() {
		// TODO Auto-generated method stub
		
		return (List<Resenia>) reseniaRepository.findAll();
	}
	
	
	
}
