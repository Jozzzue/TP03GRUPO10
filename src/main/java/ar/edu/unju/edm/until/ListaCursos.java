package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import ar.edu.unju.edm.model.Curso;


@Component
public class ListaCursos {

	private List<Curso> listado = new ArrayList<>();
	public ListaCursos() {
		// TODO Auto-generated constructor stub
	}
	public List<Curso> getListado() {
		return listado;
	}
	public void setListado(List<Curso> listado) {
		this.listado = listado;
	}
	
}
