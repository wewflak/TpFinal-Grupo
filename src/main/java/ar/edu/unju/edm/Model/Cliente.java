package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Cliente {
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	@NotNull
	Integer id;
	@NotEmpty
	String nombre;
	@NotEmpty
	String apellido;
	@NotEmpty
	String contrasena;
	@Min(value=1000000, message="El DNI debe ser mayor que un millon")
	@Max(value=99999999, message="El DNI debe ser menor que un 100 millones")
	@NotNull
	@Id
	Long dni;
	@NotEmpty
	String email;
	LocalDate FechadeN;
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFechadeN() {
		return FechadeN;
	}
	public void setFechadeN(LocalDate fechadeN) {
		FechadeN = fechadeN;
	}
	
}
