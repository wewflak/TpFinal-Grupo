package ar.edu.unju.edm.Util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.Model.Cliente;
@Component
public class ListadoClientes {
	private List <Cliente> listado = new ArrayList<>();
	
	public ListadoClientes() {
		// TODO Auto-generated constructor stub
	}
		public List<Cliente> getListado() {
		return listado;
		}
		public void setListado(List<Cliente> listado) {
		this.listado = listado;
		}
		
	///
}
