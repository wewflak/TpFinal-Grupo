package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Component
@Entity
public class Pelicula {
    @NotNull
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer Id;
    @NotEmpty
    String nombre;
    String genero;
    String duracion;
    String descripcion;
    LocalDate fechadeE;
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
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
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
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
