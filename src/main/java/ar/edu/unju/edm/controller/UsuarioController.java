package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.until.ListaUsuario;

@Controller
public class UsuarioController {

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	ListaUsuario lista;
	
	// cargar usuarios
	@GetMapping({"/otroUsuario"})	
	public ModelAndView addUser() {
		ModelAndView vista = new ModelAndView("cargarusuario");
		vista.addObject("usuario", nuevoUsuario);
		return vista;
	}
	
	// guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparaguardar, BindingResult result, Model model) {
		if(result.hasErrors()) {
			LOGGER.fatal("Error de validacion");
			model.addAttribute("usuario", usuarioparaguardar);
			return "cargarusuario";
		}
			lista.getListado().add(usuarioparaguardar);
			LOGGER.info("Usuario guardado");
			return "redirect:/otroUsuario";

		
	}
	
	// listar usuarios
	@GetMapping({"/listarUsuario"})	
	public ModelAndView listUser() {
		ModelAndView vista2 = new ModelAndView("mostrarusuario");
		vista2.addObject("listausuarios", lista.getListado());
		return vista2;
	}
	
	
	
	
	
	// modificar usuario
	@RequestMapping("/update/{dni}")
	public ModelAndView modUser(@PathVariable(name="dni") int dni) {
		// ningun algoritmo de busqueda hace falta optimizar ya q luego se utilizara una BD 
		Usuario usertomod = new Usuario();
		for(int i=0 ;i <  lista.getListado().size(); i++ ) { 
			if(lista.getListado().get(i).getDni() == dni)
				usertomod = lista.getListado().get(i);
		}
		
		ModelAndView usermod = new ModelAndView("actualizarusuario");
	    usermod.addObject("usuarioparamod", usertomod);
	    return usermod;
	}
	
	
	@PostMapping("/actualizarUsuario")
	public String savemodUser(@Valid @ModelAttribute ("usuarioparamod") Usuario usuarioparamod, BindingResult result, Model model) {
		if(result.hasErrors()) {
			LOGGER.fatal("Error de validacion");
			model.addAttribute("usuario", usuarioparamod);
			return "actualizarusuario";
		}
		for(int i=0 ;i <  lista.getListado().size(); i++ ) { 
			if(lista.getListado().get(i).getDni() == usuarioparamod.getDni())
				lista.getListado().set(i, usuarioparamod);
		}
	
		System.out.println("Tamaño del listado: " + lista.getListado().size());
		return "redirect:/listarUsuario";
	}
	

	
	
	
	
	// eliminar usuarios
	@RequestMapping("/del/{dni}")
	public String deleteUser(@PathVariable(name="dni") int dni) {
		for(int i=0 ;i <  lista.getListado().size(); i++ ) {
			if(lista.getListado().get(i).getDni() == dni)
			{
				lista.getListado().remove(i);
				System.out.println("Tamaño del listado: " + lista.getListado().size());
			}
				
		}
	    	
	    return "redirect:/listarUsuario";
	}
}
