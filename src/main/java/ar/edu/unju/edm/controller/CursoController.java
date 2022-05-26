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

import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.until.ListaCursos;




@Controller
public class CursoController {

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Curso nuevoCurso;
	
	@Autowired
	ListaCursos listadoCursos;
	
	// cargar cursos
		@GetMapping({"/otroCurso"})	
		public ModelAndView addCourse() {
			ModelAndView vista = new ModelAndView("cargarcurso");
			vista.addObject("curso", nuevoCurso);
			vista.addObject("band", "false");
			return vista;
		}
		
		// guardar cursos
		@PostMapping("/guardarCurso")
		public String saveCourse(@Valid @ModelAttribute ("curso") Curso coursetosave, BindingResult result, Model model) {
		
			if(result.hasErrors()) {
				LOGGER.fatal("Error de validacion");
				model.addAttribute("curso", coursetosave);
				return "cargarcurso";
			}
			    coursetosave.setId(listadoCursos.getListado().size());
				listadoCursos.getListado().add(coursetosave);
				return "redirect:/otroCurso";
		}
		
		// listar cursos
		@GetMapping({"/listarCursos"})	
		public ModelAndView listCourse() {
			ModelAndView vista = new ModelAndView("mostrarcursos");
			vista.addObject("listacursos", listadoCursos.getListado());
			return vista;
		}
		
		// modificar cursos
		@RequestMapping("/editecourse/{id}")
		public ModelAndView modCourse(@PathVariable(name="id") int id) { 
			Curso coursetomod = new Curso();
			for(int i=0 ;i <  listadoCursos.getListado().size(); i++ ) { 
				if(listadoCursos.getListado().get(i).getId() == id)
					coursetomod = listadoCursos.getListado().get(i);
			}
			
			ModelAndView coursemod = new ModelAndView("cargarcurso");
		    coursemod.addObject("curso", coursetomod);
		    coursemod.addObject("band", "true");
		    return coursemod;
		}
		
		//actualizar curso
		@PostMapping("/modificarCurso")
		public String savemodUser(@Valid @ModelAttribute ("curso") Curso cursoparamod, BindingResult result, Model model) {
			
			if(result.hasErrors()) {
				LOGGER.fatal("Error de validacion");
				model.addAttribute("curso", cursoparamod);
				model.addAttribute("band", "true");
				return "cargarcurso";
			}
			for(int i=0 ;i <  listadoCursos.getListado().size(); i++ ) { 
				if(listadoCursos.getListado().get(i).getId() == cursoparamod.getId())
					listadoCursos.getListado().set(i, cursoparamod);
			}
		
			return "redirect:/listarCursos";
		}
		
		
		// eliminar cursos
		@RequestMapping("/deletecourse/{id}")
		public String deleteCourse(@PathVariable(name="id") int id) {
			for(int i=0 ;i <  listadoCursos.getListado().size(); i++ ) {
				if(listadoCursos.getListado().get(i).getId() == id)
				{
					listadoCursos.getListado().remove(i);
					System.out.println("Tamaño del listado: " + listadoCursos.getListado().size());
				}
					
			}
		    	
		    return "redirect:/listarCursos";
		}
}
