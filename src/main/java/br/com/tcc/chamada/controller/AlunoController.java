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

import br.com.tcc.chamada.bean.BuscaAlunoMB;
import br.com.tcc.chamada.biometria.CisBiox;
import br.com.tcc.chamada.dao.AlunoDAO;
import br.com.tcc.chamada.dao.ResponsavelAlunoDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.BuscaAluno;
import br.com.tcc.chamada.modelo.ResponsavelAluno;
import br.com.tcc.chamada.validator.AlunoValidator;

@RequestMapping("/aluno")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AlunoController {

	@Autowired
	private AlunoDAO alunoDAO;

	@Autowired
	private AlunoValidator alunoValidator;

	@Autowired
	private BuscaAlunoMB buscaAlunoMB;

	@Autowired
	private ResponsavelAlunoDAO responsavelAlunoDAO;

	public void initBinder(WebDataBinder binder) {
		binder.setValidator(alunoValidator);
	}

	@RequestMapping(method = RequestMethod.GET, name = "montarFormularioAluno")
	public ModelAndView montarFormulario(Aluno aluno) {
		ModelAndView mav = new ModelAndView("aluno/form");
		mav.addObject("aluno", aluno);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "cadastrarAluno")
	public ModelAndView cadastrarAluno(@Valid Aluno aluno, BindingResult result) {
		ModelAndView mav = new ModelAndView("aluno/form");

		if (result.hasErrors()) {
			return montarFormulario(aluno);
		}

		if (aluno.getRa() == null) {
			String cpf = aluno.getCpf();

			Aluno findByCpf = alunoDAO.findByCpf(cpf);

			if (findByCpf != null) {
				mav = montarFormulario(aluno);
				mav.addObject("mensagem", "CPF já cadastrado");
				return mav;
			}

			aluno.addPermission("ROLE_ALUNO");
			ResponsavelAluno responsavel = aluno.getResponsavelAluno();
			String cpfResponsavel = responsavel.getCpf();
			ResponsavelAluno responsavelAluno = responsavelAlunoDAO.findByCpf(cpfResponsavel);
			if (responsavelAluno != null) {
				aluno.setResponsavelAluno(responsavelAluno);
			}
			alunoDAO.save(aluno);
			mav.addObject("aluno", new Aluno());
			mav.addObject("mensagem", "Aluno cadastrado com sucesso");
		} else {
			alunoDAO.save(aluno);
			mav.addObject("aluno", aluno);
			mav.addObject("mensagem", "Aluno alterado com sucesso");
		}

		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET, name = "montarBuscarAluno")
	public ModelAndView montarBusca() {
		ModelAndView mav = new ModelAndView("aluno/buscar");
		mav.addObject("buscarAluno", new BuscaAluno());
		mav.addObject("alunosResultado", Boolean.FALSE);
		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST, name = "filtroBuscarAluno")
	public ModelAndView buscarAluno(BuscaAluno buscaAluno, BindingResult result) {
		ModelAndView mav = new ModelAndView("aluno/buscar");
		List<Aluno> alunos = buscaAlunoMB.getAlunos(buscaAluno);
		mav.addObject("buscarAluno", new BuscaAluno());
		mav.addObject("alunosResultado", Boolean.TRUE);
		mav.addObject("alunos", alunos);
		return mav;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET, name = "montarAlterarAluno")
	public ModelAndView montarAlterarAluno(Long id) {
		ModelAndView mav = new ModelAndView("aluno/buscar");

		if (!alunoDAO.exists(id)) {
			return mav;
		}

		Aluno alunoAlterar = alunoDAO.findOne(id);

		return montarFormulario(alunoAlterar);
	}

	@RequestMapping(value = "/remover", method = RequestMethod.GET, name = "removerAluno")
	public ModelAndView removerAluno(Long id) {
		ModelAndView mav = new ModelAndView("aluno/buscar");
		mav.addObject("buscarAluno", new BuscaAluno());

		if (alunoDAO.exists(id)) {
			alunoDAO.delete(id);
		
		}

		return mav;
	}

	@RequestMapping(value = "/cadastrar/biometria", method = RequestMethod.GET, name = "montarCadastroBiometria")
	public ModelAndView montarCadastroBiometria(Long id) {
		ModelAndView mav = new ModelAndView("aluno/biometria");
		Aluno aluno = alunoDAO.findOne(id);

		if (aluno == null) {
			return montarBusca();
		}

		Boolean digitalUm = aluno.getDigitalUm() != null;
		Boolean digitalDois = aluno.getDigitalDois() != null;

		mav.addObject("digitalUm", digitalUm);
		mav.addObject("digitalDois", digitalDois);
		mav.addObject("aluno", aluno);

		return mav;
	}

	@RequestMapping(value = "/cadastrar/biometria/digitalum", method = RequestMethod.GET, name = "cadastroBiometriaDigitalUm")
	public ModelAndView cadastroBiometriaDigitalUm(Long id) {

		Aluno aluno = alunoDAO.findOne(id);

		Runnable runnable = new Runnable() {
			public void run() {
				CisBiox cisBiox = new CisBiox();
				System.out.println(cisBiox.iniciar());
				byte[] digital = cisBiox.capturarDigital();
				System.out.println(cisBiox.finalizar());

				aluno.setDigitalUm(digital);
				alunoDAO.save(aluno);
			}

		};

		runnable.run();

		ModelAndView mav = montarCadastroBiometria(id);
		mav.addObject("mensagem", "Biometria cadastrada com sucesso");
		return mav;
	}

	@RequestMapping(value = "/cadastrar/biometria/digitaldois", method = RequestMethod.GET, name = "cadastroBiometriaDigitalDois")
	public ModelAndView cadastroBiometriaDigitalDois(Long id) {

		Aluno aluno = alunoDAO.findOne(id);
		Runnable runnable = new Runnable() {
			public void run() {
				CisBiox cisBiox = new CisBiox();
				System.out.println(cisBiox.iniciar());
				byte[] digital = cisBiox.capturarDigital();
				System.out.println(cisBiox.finalizar());

				aluno.setDigitalDois(digital);
				alunoDAO.save(aluno);
			}

		};

		runnable.run();

		ModelAndView mav = montarCadastroBiometria(id);
		mav.addObject("mensagem", "Biometria cadastrada com sucesso");
		return mav;
	}
}
