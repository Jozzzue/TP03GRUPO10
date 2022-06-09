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
import ar.edu.unju.edm.service.ICursoService;





@Controller
public class CursoController {

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Curso nuevoCurso;
	
	@Autowired
	ICursoService cursoService;
	
	// cargar cursos
		@GetMapping({"/otroCurso"})	
		public ModelAndView addCourse() {
			LOGGER.info("ingresando al metodo: addCourse");
			ModelAndView vista = new ModelAndView("cargarcurso");
			vista.addObject("curso", nuevoCurso);
			vista.addObject("band",false);
			return vista;
		}
		
		// guardar cursos
		@PostMapping("/guardarCurso")
		public String saveCourse(@Valid @ModelAttribute ("curso") Curso coursetosave, BindingResult result, Model model) {
		
			if(result.hasErrors()) {
				LOGGER.fatal("Error de validacion");
				model.addAttribute("curso", coursetosave);
				model.addAttribute("band", false);
				return "cargarcurso";
			}
			
			
			try {
				cursoService.guardarCurso(coursetosave);
			}catch(Exception e) {
				model.addAttribute("formCourseMessage", e.getMessage());
				model.addAttribute("curso", coursetosave);
				model.addAttribute("band", false);
				LOGGER.error("saliendo del metodo: saveCourse");
				return "cargarusuario";
			}
			model.addAttribute("formCourseMessage", "Curso guardado correctamente");
			model.addAttribute("curso", nuevoCurso);	 
			model.addAttribute("band", false);
			LOGGER.error("saliendo del metodo: saveCourse");
				return "cargarcurso";
		}
		
		// listar cursos
		@GetMapping({"/listarCursos"})	
		public ModelAndView listCourse() {
			ModelAndView vista = new ModelAndView("mostrarcursos");
			if(cursoService.listarCursos().size()!=0) {
				vista.addObject("listacursos", cursoService.listarCursos());
				LOGGER.info("ingresando al metodo: listUsers "+cursoService.listarCursos().size());
			}
			return vista;
		}
		
		// modificar curso
		@RequestMapping("/editeCourse/{id}")
		public ModelAndView modCourse(Model model, @PathVariable(name="id") int id) throws Exception { 
			Curso coursetomod = new Curso();
			try {
				coursetomod = cursoService.buscarCurso(id);
			}
			catch(Exception e) {
				model.addAttribute("formCourseMessage", e.getMessage());
			}
			//coursetomod = cursoService.buscarCurso(id);
			ModelAndView coursemod = new ModelAndView("cargarcurso");
		    coursemod.addObject("curso", coursetomod);
		    LOGGER.error("saliendo del metodo: modCourse "+ coursetomod.getNombre());
		    coursemod.addObject("band",true);
		    return coursemod;
		}
		
		//actualizar curso
		@PostMapping("/editarCurso")
		public ModelAndView savemodCourse(@Valid @ModelAttribute ("curso") Curso cursoparamod, BindingResult result) {
			if(result.hasErrors()) {
				LOGGER.fatal("Error de validacion");
				ModelAndView vista = new ModelAndView("cargarcurso");
				vista.addObject("curso", cursoparamod);
				vista.addObject("band",true);
				return vista;
			}
			try {
				cursoService.modificarCurso(cursoparamod);
			}catch(Exception e){
				ModelAndView vista = new ModelAndView("cargarcurso");
				vista.addObject("formCourseMessage", e.getMessage());
				vista.addObject("curso", cursoparamod);
				vista.addObject("band",true);
				LOGGER.error("saliendo del metodo: savemodCourse");
				return vista;
			}
				ModelAndView vista = new ModelAndView("mostrarcursos");
				vista.addObject("listacursos", cursoService.listarCursos());	
				vista.addObject("formCourseMessage","Curso modificado Correctamente");
			return vista;
		}
		
		
		// eliminar cursos
		@RequestMapping("/deleteCourse/{id}")
		public String deleteCourse(@PathVariable(name="id") int id, Model model) {
			try {
				cursoService.eliminarCurso(id);
			}catch(Exception e){
				LOGGER.error("encontrando: curso a eliminar");
				model.addAttribute("formCourseMessage", e.getMessage());
				return "redirect:/otroCurso";
			}
		
		    return "redirect:/listarCursos";
		}
}
