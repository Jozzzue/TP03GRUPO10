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
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	IUsuarioService usuarioService;
	
	// cargar usuarios
	@GetMapping({"/otroUsuario"})	
	public ModelAndView addUser() {
		LOGGER.info("ingresando al metodo: addUser");
		ModelAndView vista = new ModelAndView("cargarusuario");
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("editMode",false);
		return vista;
	}
	
	// guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparaguardar, BindingResult result, Model model) {
		if(result.hasErrors()) {
			LOGGER.fatal("Error de validacion");
			model.addAttribute("usuario", usuarioparaguardar);
			model.addAttribute("editMode", false);
			return "cargarusuario";
		}
			try {
				usuarioService.guardarUsuario(usuarioparaguardar);
			} catch(Exception e) {
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				model.addAttribute("usuario", usuarioparaguardar);
				model.addAttribute("editMode", false);
				LOGGER.error("saliendo del metodo: saveUser");
				return "cargarusuario";
			}
		
			model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
			model.addAttribute("usuario", nuevoUsuario);	 
			model.addAttribute("editMode", false);
			return "cargarusuario";

		
	}
	
	// listar usuarios
	@GetMapping({"/listarUsuario"})	
	public ModelAndView listUser() {
		ModelAndView vista2 = new ModelAndView("mostrarusuario");
		if(usuarioService.listarUsuarios().size()!=0) {
		vista2.addObject("listausuarios",usuarioService.listarUsuarios());
		LOGGER.info("ingresando al metodo: listUsers "+usuarioService.listarUsuarios().get(0).getApellido());
		}
		return vista2;
	}
	
	
	
	
	
	// modificar usuario
	@GetMapping("/update/{dni}")
	public ModelAndView modUser(Model model, @PathVariable(name="dni") int dni) throws Exception {
		Usuario usuarioEncontrado = new Usuario();
		try {
			usuarioEncontrado = usuarioService.buscarUsuario(dni);		
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
		}
		//usuarioEncontrado = usuarioService.buscarUsuario(dni);	
		ModelAndView usermod = new ModelAndView("cargarusuario");
	    usermod.addObject("usuario", usuarioEncontrado);
	    LOGGER.error("saliendo del metodo: modUser "+ usuarioEncontrado.getDni());
	    usermod.addObject("editMode",true);
	    return usermod;
	}
	
	
	@PostMapping("/editarUsuario")
	public ModelAndView savemodUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparamod, BindingResult result ) {
		/*if(result.hasFieldErrors("nombre") || result.hasFieldErrors("apellido") || result.hasFieldErrors("fechanacimiento") || result.hasFieldErrors("email") ) {*/
			if(result.hasErrors()){
			LOGGER.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("cargarusuario");
			vista.addObject("usuario", usuarioparamod);
			vista.addObject("editMode",true);
			return vista;
		}
		try{
			usuarioService.modificarUsuario(usuarioparamod);
		}catch(Exception e){
			ModelAndView vista = new ModelAndView("cargarusuario");
			vista.addObject("formUsuarioErrorMessage", e.getMessage());
			vista.addObject("usuario", usuarioparamod);
			vista.addObject("editMode",true);
			LOGGER.error("saliendo del metodo: savemodUser");
			return vista;
		}
		 LOGGER.error("DNI de usuarioparamod "+ usuarioparamod.getDni());
		 LOGGER.error("Nombre de usuarioparamod "+ usuarioparamod.getNombre());
		ModelAndView vista1 = new ModelAndView("mostrarusuario");		
		vista1.addObject("listausuarios", usuarioService.listarUsuarios());	
		vista1.addObject("formUsuarioErrorMessage","Usuario modificado Correctamente");
		
		return vista1;
	}
	

	
	
	
	// eliminar usuarios
	@RequestMapping("/del/{dni}")
	public String deleteUser(@PathVariable(name="dni") int dni, Model model) {
		try {
			usuarioService.eliminarUsuario(dni);
		}catch(Exception e){
			LOGGER.error("encontrando: usuario a eliminar");
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			return "redirect:/otroUsuario";
		}
	    	
	    return "redirect:/listarUsuario";

	}
}
