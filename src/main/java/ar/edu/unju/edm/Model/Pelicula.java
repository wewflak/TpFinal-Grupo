package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Component
@Entity
public class Pelicula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;
    @NotEmpty
    private String nombre;
    private  String genero;
    @NotNull
    private Long duracion;
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechadeE;
    @Lob
    private String imagen;
    public Pelicula() {
		// TODO Auto-generated constructor stub
	}

    public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	private Boolean Estado;
    
    public Boolean getEstado() {
        return Estado;
    }
    public void setEstado(Boolean estado) {
        Estado = estado;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public LocalDate getFechadeE() {
        return fechadeE;
    }
    public void setFechadeE(LocalDate fechadeE) {
        this.fechadeE = fechadeE;
    }
    public String getNombre() {
        return nombre;
    }
    public Long getDuracion() {
        return duracion;
    }
    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
