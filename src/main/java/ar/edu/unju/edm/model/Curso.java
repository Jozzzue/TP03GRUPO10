package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component 

@Entity
public class Curso {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
	private int id;
	@Size(min=3, max=100, message="EL nombre debe tener 3 caracteres minimo, maximo 100")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")
	private String nombre;
	@Size(min=0, max=200, message="La descripcion debe tener maximo 100 caracteres")
	@NotBlank(message="La descripcion no puede ser espacios en blanco")
	@Column(name="d")
	private String descripcion;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechainicio;
	@PositiveOrZero(message="El costo debe ser mayor o igual a 0")
	private int costo;
	@Min(value=0, message="La valoracion no puede ser menor que 0")
	@Max(value=10, message="La valoracion no puede ser mayor que 10")
	private int valoracion;
	@Positive(message="La duracion debe ser mayor a 0 semanas")
	private int duracion;
	@Size(min=3, max=30, message="El docente debe tener 3 caracteres minimo, maximo 30")
	@NotBlank(message="El docente no puede ser espacios en blanco")
	@NotEmpty(message="El docente no puede estar vacio")
	private String docente;
	@Positive(message="El cupo debe ser positivo")
	private int cupo;

	private Boolean estado;
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Curso() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
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
	public LocalDate getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(LocalDate fechainicio) {
		this.fechainicio = fechainicio;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
}
