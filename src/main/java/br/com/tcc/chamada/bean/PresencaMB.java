package br.com.tcc.chamada.bean;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.PresencaDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.Presenca;
import br.com.tcc.chamada.modelo.Turma;

@Component
public class PresencaMB {

	@Autowired
	private PresencaDAO presencaDAO;
	private byte[] digital;

	public Presenca iniciarAula(Turma turma) {
		Presenca presenca = new Presenca();

		LocalDateTime horarioAtual = LocalDateTime.now();
		LocalDateTime hrPersonalizado = LocalDateTime.of(2016, 07, 07, 19, 00);

		Map<Aluno, Boolean> alunos = criarMapAlunos(turma.getAlunos());
		List<Aula> aulas = turma.getAulas();
		Aula aula = procurarAulaDoHorarioAtual(aulas, horarioAtual);
		List<Presenca> aulasAbertas = presencaDAO.findByAulaFinalizadaIsNull();

		if (aula == null || aulasAbertas.contains(aula)) {
			return null;
		}

		presenca.setData(horarioAtual);
		presenca.setAula(aula);
		presenca.setAlunoPresente(alunos);

		return presenca;
	}

	private Map<Aluno, Boolean> criarMapAlunos(List<Aluno> alunos) {
		Map<Aluno, Boolean> map = new HashMap<>();

		for (Aluno aluno : alunos) {
			map.put(aluno, Boolean.FALSE);
		}

		return map;
	}

	private Aula procurarAulaDoHorarioAtual(List<Aula> aulas, LocalDateTime horarioAtual) {
		DayOfWeek dayOfWeek = horarioAtual.getDayOfWeek();
		LocalTime timeAtual = horarioAtual.toLocalTime();

		for (Aula aula : aulas) {
			if (aula.getDiasDeAula().ordinal() == dayOfWeek.ordinal()) {
				if (timeAtual.isAfter(aula.getHorarioInicio()) && timeAtual.isBefore(aula.getHorarioFim())) {
					return aula;
				}
			}
		}

		return null;
	}

	public void salvar(Presenca presenca) {
		presencaDAO.save(presenca);
	}

	public void atualizarBancoDeDado() {
		List<Presenca> aulas = presencaDAO.findByAulaFinalizadaIsNull();

		for (Presenca presenca : aulas) {
			Aula aula = presenca.getAula();
			LocalTime horarioFim = aula.getHorarioFim();
			LocalTime horarioAtual = LocalTime.now();
			boolean finalizado = horarioAtual.isAfter(horarioFim);
			if (finalizado) {
				presenca.setAulaFinalizada(Boolean.TRUE);
				presencaDAO.save(presenca);
			}
		}
	}

	public List<Presenca> aulasAtivas() {
		return presencaDAO.findByAulaFinalizadaIsNull();
	}

	public Boolean confirmarPresenca(Long idPresenca, Long id, byte[] digital) {

		Presenca presenca = presencaDAO.findOne(idPresenca);

		Map<Aluno, Boolean> alunoPresente = presenca.getAlunoPresente();
		Set<Entry<Aluno, Boolean>> entrySet = alunoPresente.entrySet();
		for (Entry<Aluno, Boolean> entry : entrySet) {
			if (entry.getValue()) {
				continue;
			}

			if (entry.getKey().getRa().equals(id)) {
				Aluno aluno = entry.getKey();
				byte[] digitalUm = aluno.getDigitalUm();
				byte[] digitalDois = aluno.getDigitalDois();

				Boolean digitalUmValida = true;
				Boolean digitalDoisValida = true;

				for (int i = 0; i < digital.length; i++) {
					if (!digitalUmValida && !digitalDoisValida) {
						break;
					}
					if (i >= digitalUm.length && digitalUmValida) {
						digitalUmValida = false;
					}

					if (i >= digitalDois.length && digitalDoisValida) {
						digitalDoisValida = false;
					}

					if (digitalUmValida && digital[i] != digitalUm[i]) {
						digitalUmValida = false;
					}

					if (digitalDoisValida && digital[i] != digitalDois[i]) {
						digitalUmValida = false;
					}
				}

				if (digitalUmValida || digitalDoisValida) {
					alunoPresente.put(aluno, Boolean.TRUE);
				}
				break;
			}
		}

		presencaDAO.save(presenca);

		Set<Entry<Aluno, Boolean>> entrySet2 = presenca.getAlunoPresente().entrySet();
		for (Entry<Aluno, Boolean> entry : entrySet2) {
			if (entry.getKey().getRa().equals(id)) {
				return entry.getValue();
			}
		}
		return false;
	}

	public void setDigital(byte[] digital) {
		this.digital = digital;
	}

	public byte[] getDigital() {
		return digital;
	}

}
