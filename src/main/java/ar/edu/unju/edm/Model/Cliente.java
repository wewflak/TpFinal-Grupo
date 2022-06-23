package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
@Entity
public class Cliente {
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Basic(optional = false)
    //@Column(name = "IdCliente",unique=true, nullable = false)
    private Integer IdCliente;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String contrasena;
	@Min(value=1000000, message="El DNI debe ser mayor que un millon")
	@Max(value=99999999, message="El DNI debe ser menor que un 100 millones")
	@NotNull
	private Long dni;
	@NotEmpty
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate FechadeN;
	private Boolean estado;
	

	public Integer getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(Integer idCliente) {
		IdCliente = idCliente;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
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
