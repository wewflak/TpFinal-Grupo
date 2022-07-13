package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Resenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer idComentario;
    @NotEmpty
    @Size(min = 3, max = 150, message="El código del producto tiene que tener entre 3 y 250 caracteres")
	private String texto;
	@NotNull
	@Min(value=1, message="No puede ser menor que 1")
	@Max(value=10, message="No puede ser mayo que 10")
	private Integer valoracion;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dni")
	private Cliente cliente;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Pelicula pelicula;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechadeCom;
	private Boolean estado;
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
}
