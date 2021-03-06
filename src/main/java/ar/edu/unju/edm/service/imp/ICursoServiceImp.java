package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.CursoController;
import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.CursoRepository;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.ICursoService;
import ar.edu.unju.edm.until.ListaCursos;


@Service
public class ICursoServiceImp implements ICursoService{

	private static final Log LOGGER = LogFactory.getLog(CursoController.class);
	
	@Autowired
	ListaCursos lista;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@Override
	public void guardarCurso(Curso curso) {
		// TODO Auto-generated method stub
		curso.setEstado(true);
		curso.setId(lista.getListado().size()+1);
		//lista.getListado().add(curso);
		cursoRepository.save(curso);
	}

	@Override
	public void eliminarCurso(int id) throws Exception {
		// TODO Auto-generated method stub		
		/*for (int i = 0; i < lista.getListado().size(); i++) {			
			if (lista.getListado().get(i).getId()==id) {				
				lista.getListado().get(i).setEstado(false);		
			}            
        }		*/
		Curso auxiliar =new Curso();
		auxiliar=buscarCurso(id);
		auxiliar.setEstado(false);
		cursoRepository.save(auxiliar);
	}

	@Override
	public void modificarCurso(Curso curso) {
		// TODO Auto-generated method stub
		LOGGER.info("ingresando al metodo arraylist: modificar cursos " + curso.getId());
		/*for (int i = 0; i < lista.getListado().size(); i++) {			
			if (lista.getListado().get(i).getId() == curso.getId()) {
				LOGGER.error("encontrando: curso "+i);
				lista.getListado().set(i, curso);			
			}            
        }*/
		cursoRepository.save(curso);
	}

	@Override
	public List<Curso> listarCursos() {
		// TODO Auto-generated method stub
		List<Curso> auxiliar = new ArrayList<>();
		List<Curso> auxiliar2 = new ArrayList<>();
		/*LOGGER.info("ingresando al metodo arraylist: listar cursos");
		for (int i = 0; i < lista.getListado().size(); i++) {
			if (lista.getListado().get(i).getEstado()==true) {
				auxiliar.add(lista.getListado().get(i));
				LOGGER.error("recorriendo arraylist: cursos " + lista.getListado().get(i).getId());
			}            
        }*/
		auxiliar=(List<Curso>) cursoRepository.findAll();
		for(int i = 0 ;i<auxiliar.size();i++) {
		if (auxiliar.get(i).getEstado()==true) {
			auxiliar2.add(auxiliar.get(i));
		}
	}	
	return auxiliar2;
}
	
	@Override
	public Curso buscarCurso(int id) throws Exception {
		// TODO Auto-generated method stub
		Curso cursoEncontrado = new Curso();
		for (int i = 0; i < lista.getListado().size(); i++) {
			if (lista.getListado().get(i).getId() == id) {
				cursoEncontrado = lista.getListado().get(i);		
			}            
        }
		cursoEncontrado=cursoRepository.findById(id).orElseThrow(()->new Exception("curso no encontrado"));
		return cursoEncontrado;
	}

}
