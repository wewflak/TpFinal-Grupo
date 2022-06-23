package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

@Component
@Entity
public class ClientePelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	private Integer idClientePelicula;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="IdCliente")
	private Cliente cliente;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="Id")
	private Pelicula pelicula;
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
