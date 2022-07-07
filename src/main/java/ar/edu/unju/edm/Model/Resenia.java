package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Resenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer idComentario;
    @NotEmpty
	private String texto;
	@NotNull
	private Integer valoracion;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dni")
	private Cliente cliente;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Pelicula pelicula;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechadeCom;
	
	public Resenia() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getValoracion() {
		return valoracion;
	}
	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}
	public LocalDate getFechadeCom() {
		return fechadeCom;
	}
	public void setFechadeCom(LocalDate fechadeCom) {
		this.fechadeCom = fechadeCom;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
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
	
}
