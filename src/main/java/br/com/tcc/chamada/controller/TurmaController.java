package br.com.tcc.chamada.controller;

import java.util.ArrayList;
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

import br.com.tcc.chamada.bean.BuscaAlunoMB;
import br.com.tcc.chamada.bean.BuscaTurmaMB;
import br.com.tcc.chamada.bean.PresencaMB;
import br.com.tcc.chamada.biometria.CisBiox;
import br.com.tcc.chamada.biometria.RecolherDigitalAula;
import br.com.tcc.chamada.dao.AlunoDAO;
import br.com.tcc.chamada.dao.AulaDAO;
import br.com.tcc.chamada.dao.PresencaDAO;
import br.com.tcc.chamada.dao.TurmaDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.BuscaTurma;
import br.com.tcc.chamada.modelo.CombinedCommand;
import br.com.tcc.chamada.modelo.Presenca;
import br.com.tcc.chamada.modelo.Professor;
import br.com.tcc.chamada.modelo.Turma;
import br.com.tcc.chamada.validator.TurmaValidator;

@RequestMapping("/turma")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class TurmaController {
	@Autowired
	private TurmaDAO turmaDAO;

	@Autowired
	private AlunoDAO alunoDAO;

	@Autowired
	private AulaDAO aulaDAO;

	@Autowired
	private PresencaDAO presencaDAO;

	@Autowired
	private TurmaValidator turmaValidator;

	@Autowired
	private BuscaTurmaMB buscaTurmaMB;

	@Autowired
	private BuscaAlunoMB buscaAlunoMB;

	@Autowired
	private PresencaMB presencaMB;

	@Autowired
	private RecolherDigitalAula recolherDigitalAula;

	public void initBinder(WebDataBinder binder) {
		binder.setValidator(turmaValidator);
	}

	@RequestMapping(method = RequestMethod.GET, name = "montarFormularioTurma")
	public ModelAndView montarFormulario(Turma turma) {
		ModelAndView mav = new ModelAndView("turma/form");
		mav.addObject("turma", turma);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "cadastrarTurma")
	public ModelAndView cadastrarTurma(@Valid Turma turma, BindingResult result) {
		ModelAndView mav = new ModelAndView("turma/form");

		if (result.hasErrors()) {
			return montarFormulario(turma);
		}

		if (turma.getId() == null) {
			turmaDAO.save(turma);
			mav.addObject("turma", new Turma());
			mav.addObject("mensagem", "Turma cadastrada com sucesso");
		} else {
			turmaDAO.save(turma);
			mav.addObject("turma", turma);
			mav.addObject("mensagem", "Turma alterada com sucesso");
		}

		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET, name = "montarBuscarTurma")
	public ModelAndView montarBusca() {
		ModelAndView mav = new ModelAndView("turma/buscar");
		mav.addObject("buscarTurma", new BuscaTurma());
		mav.addObject("turmasResultado", Boolean.FALSE);
		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST, name = "filtroBuscarTurma")
	public ModelAndView buscarAluno(BuscaTurma buscaTurma, BindingResult result) {
		ModelAndView mav = new ModelAndView("turma/buscar");
		List<Turma> turmas = buscaTurmaMB.getTurmas(buscaTurma);
		mav.addObject("buscarTurma", new BuscaTurma());
		mav.addObject("turmasResultado", Boolean.TRUE);
		mav.addObject("turmas", turmas);
		return mav;
	}
	

	
	
	

	@RequestMapping(value = "/alterar", method = RequestMethod.GET, name = "montarAlterarTurma")
	public ModelAndView montarAlterarAluno(Long id) {
		ModelAndView mav = new ModelAndView("turma/buscar");

		if (!turmaDAO.exists(id)) {
			return mav;
		}

		Turma turmaAlterar = turmaDAO.findOne(id);

		return montarFormulario(turmaAlterar);
	}

	@RequestMapping(value = "/remover", method = RequestMethod.GET, name = "removerTurma")
	public ModelAndView removerTurma(Long id) {
		ModelAndView mav = new ModelAndView("turma/buscar");
		mav.addObject("buscarTurma", new BuscaTurma());

		if (turmaDAO.exists(id)) {
			Turma turma = turmaDAO.findOne(id);

			List<Aula> aulas = turma.getAulas();
			if (aulas.isEmpty()) {
				List<Aluno> alunos = turma.getAlunos();
				
				for (Aluno aluno : alunos) {
					aluno.setTurma(null);
					alunoDAO.save(aluno);
				}
				
				turmaDAO.delete(id);
				mav.addObject("mensagem", "Turma removida com sucesso");
			} else {
				mav.addObject("mensagem",
						"Turma não pode ser removida, existe aulas associada a esta turma.");
			}
		}

		return mav;
	}

	@RequestMapping(value = "/alunos", method = RequestMethod.GET, name = "montarInserirAluno")
	public ModelAndView montarAlunoTurma(Long id, CombinedCommand combinedCommand) {
		ModelAndView mav = new ModelAndView("turma/alunos");

		if (id != null && turmaDAO.exists(id)) {
			mav.addObject("turma", turmaDAO.findOne(id));
			combinedCommand.setId(id);
			mav.addObject("combinedCommand", combinedCommand);
		} else {
			return montarBusca();
		}

		return mav;
	}

	@RequestMapping(value = "/add-aluno", method = RequestMethod.POST, name = "inserirAluno")
	public ModelAndView inserirAlunoTurma(CombinedCommand combinedCommand) {
		ModelAndView mav = new ModelAndView("turma/alunos");

		List<Aluno> alunos = buscaAlunoMB.getAlunos(combinedCommand.getBuscaAluno());
		Long idTurma = combinedCommand.getId();
		Turma turma = turmaDAO.findOne(idTurma);
		mav.addObject("turma", turma);

		if (alunos != null && alunos.size() > 0) {

			for (Aluno aluno : alunos) {
				System.out.println(aluno.getTurma());
				if (aluno.getTurma() != null) {
					mav.addObject("mensagem", "Aluno está registrado em outra turma");
					return mav;
				}
				aluno.setTurma(turma);
				alunoDAO.save(aluno);
			}

			mav.addObject("sms", "Aluno inserido com sucesso");

		} else {
			mav.addObject("mens", "Aluno invalido");
		}
		return mav;
	}

	@RequestMapping(value = "/aulas", method = RequestMethod.GET, name = "montarMostrarAula")
	public ModelAndView montarAulasTurma(Long id) {
		ModelAndView mav = new ModelAndView("turma/aulas");

		if (id != null && turmaDAO.exists(id)) {
			mav.addObject("turma", turmaDAO.findOne(id));
		} else {
			return montarBusca();
		}

		return mav;
	}

	@RequestMapping(value = "/remover-aluno", method = RequestMethod.GET, name = "removerAluno")
	public ModelAndView removerAluno(Long idTurma, Long idAluno) {
		ModelAndView mav = montarAlunoTurma(idTurma, new CombinedCommand());

		if (idTurma != null && turmaDAO.exists(idTurma)) {
			Turma turma = turmaDAO.findOne(idTurma);
			List<Aluno> alunos = turma.getAlunos();

			for (int i = 0; i < alunos.size(); i++) {
				if (alunos.get(i).getRa().equals(idAluno)) {
					alunos.remove(i);
					alunos.get(i).setTurma(null);
					alunoDAO.save(alunos.get(i));
					break;
				}
			}
			
			turmaDAO.save(turma);
			mav.addObject("turma", turma);
		
		} else {
			return montarBusca();
		}

		return mav;
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.GET, name = "excluirAulaDaTurma")
	public ModelAndView excluirAulaDaTurma(Long idTurma, Long idAula) {
		ModelAndView mav = new ModelAndView("redirect:/turma/aulas?id=" + idTurma);

		if (idAula != null && aulaDAO.exists(idAula)) {
			Aula aula = aulaDAO.findOne(idAula);

			List<Presenca> presencas = presencaDAO.findByAulaId(idAula);
			if (presencas != null) {
				for (Presenca presenca : presencas) {
					presencaDAO.delete(presenca);
				}
			}

			aulaDAO.delete(aula);
			mav.addObject("turma", turmaDAO.findOne(idTurma));
			
		} else {
			return montarBusca();
		}

		return mav;
	}

	@RequestMapping(value = "/iniciar", method = RequestMethod.GET, name = "montarInicioAula")
	public ModelAndView montarInicioAula(Long id) {
		ModelAndView mav = new ModelAndView("turma/codigo-professor");

		if (id != null && turmaDAO.exists(id)) {
			Turma turma = turmaDAO.findOne(id);
			Presenca presenca = presencaMB.iniciarAula(turma);

			if (presenca == null) {
				ModelAndView montarBusca = montarBusca();
				montarBusca.addObject("mensagem", "Não existe aula disponivel para esta turma neste horario");
				return montarBusca;
			}
			mav.addObject("presenca", presenca);
			mav.addObject("idTurma", id);

		} else {
			return montarBusca();
		}

		return mav;
	}

	@RequestMapping(value = "/iniciar", method = RequestMethod.POST, name = "confirmarSenhaProfessorIniciar")
	public ModelAndView iniciarAula(String senha, Long id) {
		ModelAndView mav = new ModelAndView("turma/presenca");

		if (id != null && turmaDAO.exists(id)) {
			Turma turma = turmaDAO.findOne(id);
			Presenca presenca = presencaMB.iniciarAula(turma);

			if (presenca == null) {
				ModelAndView montarBusca = montarBusca();
				montarBusca.addObject("mensagem", "Não existe aula disponivel para esta turma neste horario");
				return montarBusca;
			}

			Professor professor = presenca.getAula().getProfessor();

			if (!professor.getPassword().equals(senha)) {
				mav = montarInicioAula(id);
				mav.addObject("mensagem", "Senha inválida");
				return mav;
			}

			presencaMB.salvar(presenca);
			mav.addObject("presenca", presenca);

			recolherDigitalAula.setIdPresenca(presenca.getId());
			Thread inicioBiometria = new Thread(recolherDigitalAula);
			inicioBiometria.start();
		}

		return mav;
	}

	@RequestMapping(value = "/aulas-ativas", method = RequestMethod.GET, name = "aulasAtivas")
	public ModelAndView aulasAtivas() {
		ModelAndView mav = new ModelAndView("turma/aulas-ativas");
		presencaMB.atualizarBancoDeDado();
		mav.addObject("presencas", presencaMB.aulasAtivas());
		return mav;
	}

	@RequestMapping(value = "/visualizar", method = RequestMethod.GET, name = "mostrarPresenca")
	public ModelAndView mostrarPresenca(Long idPresenca) {
		ModelAndView mav = new ModelAndView("turma/presenca");
		presencaMB.atualizarBancoDeDado();
		Presenca presenca = presencaDAO.findOne(idPresenca);
		mav.addObject("presenca", presenca);
		return mav;
	}

	@RequestMapping(value = "/finalizar", method = RequestMethod.GET, name = "finalizarAula")
	public ModelAndView finalizarAula(Long idPresenca) {
		Presenca presenca = presencaDAO.findOne(idPresenca);
		presenca.setAulaFinalizada(Boolean.TRUE);
		presencaDAO.save(presenca);
		ModelAndView mav = aulasAtivas();
		CisBiox cisBiox = new CisBiox();
		int finalizar = cisBiox.finalizar();
		System.out.println(finalizar);
		return mav;
	}
	
	@RequestMapping(value = "/todas-presencas", method = RequestMethod.GET, name = "todasPresencas")
	public ModelAndView finalizarAula(Long idTurma, Long idAula) {
		ModelAndView mav = aulasAtivas();
		List<Presenca> presenca = presencaDAO.findByAulaId(idAula);
		mav.addObject("presencas", presenca);
		return mav;
	}

}
