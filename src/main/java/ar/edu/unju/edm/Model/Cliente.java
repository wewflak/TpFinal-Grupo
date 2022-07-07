package ar.edu.unju.edm.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
@Entity
public class Cliente {
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	///AGREGADO DE BRISA
   // @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)    
    //private Long id;

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Basic(optional = false)
//    @Column(name = "IdCliente",unique=true, nullable = false)
//    private Integer IdCliente;
	@NotEmpty
    @Size(min = 3, max = 15, message="El nombre tiene que tener entre 3 y 15 caracteres")
	private String nombre;
	@NotEmpty
    @Size(min = 3, max = 15, message="El apellido tiene que tener entre 3 y 15 caracteres")
	private String apellido;
	@NotEmpty
	private String contrasena;
	@Min(value=1000000, message="El DNI debe ser mayor que un millon")
	@Max(value=99999999, message="El DNI debe ser menor que 100 millones")
	@NotNull
	@Id
	private Long dni;
	@NotEmpty
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate FechadeN;
	private Boolean estado;
	@NotBlank
	private String tipo;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
//	public Integer getIdCliente() {
//		return IdCliente;
//	}
//	public void setIdCliente(Integer idCliente) {
//		IdCliente = idCliente;
//	}
	//AGREGADO POR BRISA
	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/
//_---------------------
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
	
	//borrar despues
	int n,a;
}
