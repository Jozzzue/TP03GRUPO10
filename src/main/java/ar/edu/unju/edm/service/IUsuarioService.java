package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;

@Service
public interface IUsuarioService {
	public void guardarUsuario(Usuario usuario);
	public void eliminarUsuario(int dni);
	public void modificarUsuario(Usuario usuario);
	public List<Usuario> listarUsuarios(); 
	public Usuario buscarUsuario(int dni); 
}
