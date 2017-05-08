package br.com.tcc.chamada.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.chamada.bean.BuscaProfessorMB;
import br.com.tcc.chamada.dao.ProfessorDAO;
import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.BuscaProfessor;
import br.com.tcc.chamada.modelo.Professor;

@RequestMapping("/professor")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ProfessorController {

	@Autowired
	private ProfessorDAO professorDAO;
	@Autowired
	private BuscaProfessorMB buscaProfessorMB;

	@RequestMapping(method = RequestMethod.GET, name = "montarFormularioProfessor")
	public ModelAndView montarFormulario(Professor professor) {
		ModelAndView mav = new ModelAndView("professor/form");
		mav.addObject("professor", professor);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "cadastrarProfessor")
	public ModelAndView cadastrarProfessor(@Valid Professor professor, BindingResult result) {
		ModelAndView mav = new ModelAndView("professor/form");

		if (result.hasErrors()) {
			return montarFormulario(professor);
		}

		if (professor.getId() == null) {
			professor.addPermission("ROLE_PROFESSOR");
			professorDAO.save(professor);
			mav.addObject("professor", new Professor());
			mav.addObject("mensagem", "Professor cadastrado com sucesso");
		} else {
			professorDAO.save(professor);
			mav.addObject("professor", professor);
			mav.addObject("mensagem", "Professor alterado com sucesso");
		}

		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET, name = "montarBuscarProfessor")
	public ModelAndView montarBusca() {
		ModelAndView mav = new ModelAndView("professor/buscar");
		mav.addObject("buscarProfessor", new BuscaProfessor());
		mav.addObject("professoresResultado", Boolean.FALSE);
		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST, name = "filtroBuscarProfessor")
	public ModelAndView buscarProfessor(BuscaProfessor buscaProfessor, BindingResult result) {
		ModelAndView mav = new ModelAndView("professor/buscar");
		System.out.println(buscaProfessor.getCpf());
		List<Professor> professores = buscaProfessorMB.getProfessores(buscaProfessor);
		mav.addObject("buscarProfessor", new BuscaProfessor());
		mav.addObject("professoresResultado", Boolean.TRUE);
		mav.addObject("professores", professores);
		return mav;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET, name = "montarAlterarProfessor")
	public ModelAndView montarAlterarProfessor(Long id) {
		ModelAndView mav = new ModelAndView("professor/buscar");

		if (!professorDAO.exists(id)) {
			return mav;
		}

		Professor professorAlterar = professorDAO.findOne(id);

		return montarFormulario(professorAlterar);
	}

	@RequestMapping(value = "/remover", method = RequestMethod.GET, name = "removerProfessor")
	public ModelAndView removerProfessor(Long id) {
		ModelAndView mav = new ModelAndView("professor/buscar");
		mav.addObject("buscarProfessor", new BuscaProfessor());

		if (professorDAO.exists(id)) {
			Professor professor = professorDAO.findOne(id);
			List<Aula> aulas = professor.getAulas();
			if (aulas.isEmpty()) {
				professorDAO.delete(id);
				mav.addObject("mensagem", "Professor removido com sucesso");
			}else{
				mav.addObject("mensagem", "Professor não pode ser removido, existe aulas associadas a ele");
			}
		}

		return mav;
	}
}
