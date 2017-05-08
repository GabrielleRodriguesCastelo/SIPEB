package br.com.tcc.chamada.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.chamada.dao.AulaDAO;
import br.com.tcc.chamada.dao.MateriaDAO;
import br.com.tcc.chamada.dao.PresencaDAO;
import br.com.tcc.chamada.dao.ProfessorDAO;
import br.com.tcc.chamada.dao.TurmaDAO;
import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.DiaSemana;
import br.com.tcc.chamada.modelo.Presenca;
import br.com.tcc.chamada.validator.AulaValidator;

@RequestMapping("/aula")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AulaController {

	@Autowired
	private AulaDAO aulaDAO;

	@Autowired
	private TurmaDAO turmaDAO;

	@Autowired
	private ProfessorDAO professorDAO;

	@Autowired
	private MateriaDAO materiaDAO;

	@Autowired
	private PresencaDAO presencaDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new AulaValidator());
	}

	@RequestMapping(method = RequestMethod.GET, name = "montarFormularioAula")
	public ModelAndView montarFormulario(Aula aula) {
		ModelAndView mav = new ModelAndView("aula/form");
		mav.addObject("professores", professorDAO.findAll());
		mav.addObject("turma", turmaDAO.findAll());
		mav.addObject("materias", materiaDAO.findAll());
		mav.addObject("diasSemana", DiaSemana.values());

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "cadastrarAula")
	public ModelAndView cadastrarAula(@Valid Aula aula, BindingResult result) {
		ModelAndView mav = new ModelAndView("aula/form");

		if (result.hasErrors()) {
			return montarFormulario(aula);
		}

		if (aula.getId() == null) {

			mav = montarFormulario(new Aula());
			mav.addObject("mensagem", "Aula cadastrada com sucesso");
			aulaDAO.save(aula);
		} else {
			aulaDAO.save(aula);
			mav.addObject("aula", aula);
			mav.addObject("mensagem", "Aula alterado com sucesso");
		}

		return mav;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET, name = "listarAulas")
	public ModelAndView listarAulas() {
		ModelAndView mav = new ModelAndView("aula/listar");

		List<Aula> aulas = aulaDAO.findAll();

		mav.addObject("aulas", aulas);
		return mav;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET, name = "montarAlterarAula")
	public ModelAndView montarAlterarAula(Long id) {
		ModelAndView mav = new ModelAndView("redirect:/aula/listar");

		if (!aulaDAO.exists(id)) {
			return mav;
		}

		Aula aula = aulaDAO.findOne(id);

		return montarFormulario(aula);
	}

	@RequestMapping(value = "/remover", method = RequestMethod.GET, name = "removerAula")
	public ModelAndView removerAula(Long id) {
		ModelAndView mav = new ModelAndView("aula/listar");

		if (aulaDAO.exists(id)) {
			List<Presenca> presencas = presencaDAO.findByAulaId(id);
			if (presencas != null) {
				for (Presenca presenca : presencas) {
					presencaDAO.delete(presenca);
				}
			}
			
			aulaDAO.delete(id);
			List<Aula> aulas = aulaDAO.findAll();
			mav.addObject("aulas", aulas);
			mav.addObject("mensagem", "Aula removida com sucesso");
		}

		return mav;
	}
}
