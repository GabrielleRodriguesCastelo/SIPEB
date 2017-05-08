package br.com.tcc.chamada.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.chamada.dao.AulaDAO;
import br.com.tcc.chamada.dao.MateriaDAO;
import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.Materia;
import br.com.tcc.chamada.validator.MateriaValidator;

@RequestMapping("/materia")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class MateriaController {

	@Autowired
	private MateriaDAO materiaDAO;

	@Autowired
	private AulaDAO aulaDAO;

	@Autowired
	private MateriaValidator materiaValidator;

	public void initBinder(WebDataBinder binder) {
		binder.setValidator(materiaValidator);
	}

	@RequestMapping(method = RequestMethod.GET, name = "montarFormularioMateria")
	public ModelAndView montarFormulario(Materia materia) {
		ModelAndView mav = new ModelAndView("materia/form");
		mav.addObject("materia", materia);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "cadastrarMateria")
	public ModelAndView cadastrarMateria(@Valid Materia materia, BindingResult result) {
		ModelAndView mav = new ModelAndView("materia/form");

		if (result.hasErrors()) {
			return montarFormulario(materia);
		}

		if (materia.getId() == null) {
			materiaDAO.save(materia);
			mav.addObject("materia", new Materia());
			mav.addObject("mensagem", "Materia cadastrada com sucesso");
		} else {
			materiaDAO.save(materia);
			mav.addObject("materia", materia);
			mav.addObject("mensagem", "Materia alterada com sucesso");
		}

		return mav;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET, name = "buscarMateria")
	public ModelAndView montarBusca() {
		ModelAndView mav = new ModelAndView("materia/listar");
		List<Materia> materias = materiaDAO.findAll();
		mav.addObject("materias", materias);

		return mav;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET, name = "montarAlterarMateria")
	public ModelAndView montarAlterarMateria(Long id) {
		ModelAndView mav = new ModelAndView("redirect:/materia/listar");

		if (!materiaDAO.exists(id)) {
			return mav;
		}

		Materia materia = materiaDAO.findOne(id);

		return montarFormulario(materia);
	}

	@RequestMapping(value = "/remover", method = RequestMethod.GET, name = "removerMateria")
	public ModelAndView removerMateria(Long id) {
		ModelAndView mav = new ModelAndView("materia/listar");

		if (materiaDAO.exists(id)) {
			Boolean podeDeletar = true;
			Materia materia = materiaDAO.findOne(id);
			List<Aula> aulas = aulaDAO.findAll();

			for (Aula aula : aulas) {
				if (aula.getMateria().equals(materia)) {
					podeDeletar = false;
				}
			}

			if (podeDeletar) {
				materiaDAO.delete(id);
				List<Materia> materias = materiaDAO.findAll();
				mav.addObject("materias", materias);
				mav.addObject("mensagem", "Materia removida com sucesso");
			} else {
				List<Materia> materias = materiaDAO.findAll();
				mav.addObject("materias", materias);
				mav.addObject("mensagem", "Materia não pode ser removida, existe aula associada a ela");
			}
		}

		return mav;
	}

}
