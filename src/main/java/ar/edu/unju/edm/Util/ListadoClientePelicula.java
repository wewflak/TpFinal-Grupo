package ar.edu.unju.edm.Util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.Model.ClientePelicula;
@Component
public class ListadoClientePelicula {

		private static List<ClientePelicula> listado = new ArrayList<>(); 

		//Constructors
	public ListadoClientePelicula() {
		// TODO Auto-generated constructor stub
	}

	public static List<ClientePelicula> getListado() {
		return listado;
	}

	public static void setListado(List<ClientePelicula> listado) {
		ListadoClientePelicula.listado = listado;
	}


	
}
