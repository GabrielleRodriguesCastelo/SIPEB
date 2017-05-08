package br.com.tcc.chamada.biometria;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.PresencaDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.Presenca;

@Component
public class RecolherDigitalAula implements Runnable {

	private Long idPresenca;

	@Autowired
	private PresencaDAO presencaDAO;

	@Override
	public void run() {
		System.out.println("ENTROU NO RUN - 1");

		CisBiox cisBiox = new CisBiox();
		System.out.println("INICIANDO " + cisBiox.iniciar());
		Presenca presenca = presencaDAO.findOne(getIdPresenca());
		Presenca presenca2 = presencaDAO.findOne(getIdPresenca());

		while (presenca2.getAulaFinalizada() == null) {
			System.out.println("Entrou na RecolherDigitalAula");

			Runnable runnable = new Runnable() {
				public void run() {
					System.out.println("ENTROU NO RUN - 2");
					byte[] digital = null;

					try {
						System.out.println("ENTROU NO TRY DIGITAL");

						digital = cisBiox.capturarDigital();
						System.out.println("LEU DIGITAL");
						// System.out.println("FINALIZANDO " +
						// cisBiox.finalizar());

					} catch (Exception ex) {

						System.out.println("DEU RUIM");
						return;
					}
					Map<Aluno, Boolean> map = presenca.getAlunoPresente();
					Set<Aluno> alunos = map.keySet();

					for (Aluno aluno : alunos) {
						if (map.get(aluno)) {
							continue;
						}

						int digitalUm = 0;
						if (aluno.getDigitalUm() != null) {
							digitalUm = cisBiox.compararDigital(digital, aluno.getDigitalUm());
						}
						int digitalDois = 0;
						if (aluno.getDigitalDois() != null) {
							digitalDois = cisBiox.compararDigital(digital, aluno.getDigitalDois());
						}
						if (digitalUm == 1 || digitalDois == 1) {
							map.put(aluno, Boolean.TRUE);
							presencaDAO.save(presenca);
							break;

						}
					}
				}

			};

			runnable.run();
			presenca2 = presencaDAO.findOne(getIdPresenca());
		}

	}

	public Long getIdPresenca() {
		return idPresenca;
	}

	public void setIdPresenca(Long idPresenca) {
		this.idPresenca = idPresenca;
	}

}
