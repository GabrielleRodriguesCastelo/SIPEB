package br.com.tcc.chamada.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Professor implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotBlank
	private String dataNascimento;
	
	@NotBlank
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@NotBlank
	private String password;
	


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "professor")
	private List<Aula> aulas;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void addPermission(String permissao) {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(new Role(permissao));
	}

	@Override
	public String toString() {
		return this.nome;
	}
	public Boolean disponivelNaData(LocalDate ano, DiaSemana diaDaSemana,
			LocalTime horarioInicio, LocalTime horarioFim) {

		for (Aula aula : aulas) {
			DiaSemana diasDeAula = aula.getDiasDeAula();
			Integer anoAula = aula.getAno();
			LocalTime horarioInicioAulaRegistrada = aula.getHorarioInicio();
			LocalTime horarioFimAulaRegistrada = aula.getHorarioFim();

			if (!diasDeAula.equals(diaDaSemana)) {
				continue;
			}
			
			if (!ano.equals(anoAula)) {
				continue;
			}

			if (horarioInicio.isAfter(horarioFimAulaRegistrada)) {
				continue;
			}

			if (horarioFim.isBefore(horarioInicioAulaRegistrada)) {
				continue;
			}

			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	



	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean disponivelNaData(LocalTime horarioInicio, 
			LocalTime horarioFim,DiaSemana diaDaSemana) {

		for (Aula aula : aulas) {
			DiaSemana diasDeAula = aula.getDiasDeAula();
			LocalTime horarioInicioAulaRegistrada = aula.getHorarioInicio();
			LocalTime horarioFimAulaRegistrada = aula.getHorarioFim();

			if (diasDeAula.equals(diaDaSemana)) {
				if (horarioInicio.equals(horarioInicioAulaRegistrada) 
						&& horarioFim.equals(horarioFimAulaRegistrada)) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}
}
