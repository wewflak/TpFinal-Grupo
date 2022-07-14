package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class ClientePelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Basic(optional = false)
	private Integer idClientePelicula;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dni")
	private Cliente cliente;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Pelicula pelicula;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCompra;

	public ClientePelicula() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdClientePelicula() {
		return idClientePelicula;
	}
	public void setIdClientePelicula(Integer idClientePelicula) {
		this.idClientePelicula = idClientePelicula;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

}
