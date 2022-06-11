package ar.edu.unju.edm.Util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.Model.Pelicula;
@Component
public class ListadoPeliculas {
	private List <Pelicula> listado = new ArrayList<>();
	
	public ListadoPeliculas() {
		// TODO Auto-generated constructor stub
	}
		public List<Pelicula> getListado() {
		return listado;
		}
		public void setListado(List<Pelicula> listado) {
		this.listado = listado;
		}
	
}