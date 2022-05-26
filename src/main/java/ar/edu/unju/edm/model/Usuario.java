package ar.edu.unju.edm.model;

import java.time.LocalDate;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Usuario {

	@NotEmpty //@Email
	private String email;
	
	@Size(min=4, max=30, message="La contraseña debe tener 4 caracteres minimo, maximo 30")
	@NotBlank(message="La contraseña no puede ser espacios en blanco")
	private String contraseña;
	
	@Size(min=2, max=50, message="El apellido debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El apellido no puede ser espacios en blanco")
	@NotEmpty(message="El apellido no puede estar vacio")
	private String apellido;
	
	@Size(min=2, max=50, message="EL nombre debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")
	private String nombre;
	
	@Min(value=1000000, message="El dni debe ser mayor a 1.000.000")
	@Max(value=99999999, message="El dni debe ser menor a 99.999.999")
	private int dni;
	
	@Past(message="La fecha de nacimiento debe ser del pasado")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechanacimiento;
	

	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String email, String contraseña) {
		super();
		this.email = email;
		this.contraseña = contraseña;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
	public LocalDate getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	
}
