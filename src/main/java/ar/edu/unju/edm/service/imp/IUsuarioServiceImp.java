package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListaUsuario lista;
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.setEstado(true);
		lista.getListado().add(usuario);
	}

	@Override
	public void eliminarUsuario(int id) {
		// TODO Auto-generated method stub		
		for (int i = 0; i < lista.getListado().size(); i++) {			
			if (lista.getListado().get(i).getDni()==id) {				
				lista.getListado().get(i).setEstado(false);		
			}            
        }		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < lista.getListado().size(); i++) {			
			if (lista.getListado().get(i).getDni() == usuario.getDni()) {
				LOGGER.error("encontrando: usuario"+i);
				lista.getListado().set(i, usuario);			
			}            
        }
	}

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> auxiliar = new ArrayList<>();
		LOGGER.info("ingresando al metodo arraylist: listar usuarios");
		for (int i = 0; i < lista.getListado().size(); i++) {
			
			if (lista.getListado().get(i).getEstado()==true) {
				auxiliar.add(lista.getListado().get(i));
				LOGGER.error("recorriendo arraylist: usuarios "+lista.getListado().get(i).getDni());
			}            
        }
		return auxiliar;
	}

	

	@Override
	public Usuario buscarUsuario(int id) {
		// TODO Auto-generated method stub
		Usuario usuarioEncontrado = new Usuario();
		for (int i = 0; i < lista.getListado().size(); i++) {
			if (lista.getListado().get(i).getDni() == id) {
				usuarioEncontrado = lista.getListado().get(i);		
			}            
        }
		return usuarioEncontrado;
	}

}
