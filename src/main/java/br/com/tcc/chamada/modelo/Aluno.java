package br.com.tcc.chamada.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Aluno implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long ra;

	@NotBlank
	@Length(min = 2, max = 100)
	private String nome;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNascimento;

	@Length(min = 9, max = 15) 
	@NotBlank
	private String rg;

	@Length(min = 11, max = 14)
	@NotBlank
	private String cpf;

	@Length(min = 6, max = 60)
	@NotBlank
	private String email;

	@Lob
	private byte[] digitalUm;

	@Lob
	private byte[] digitalDois;
	
	private String foto;

	@ManyToOne(fetch = FetchType.EAGER)
	private Turma turma;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResponsavelAluno responsavelAluno;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getUsername() {
		return email;
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

	

	public void addPermission(String permissao) {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(new Role(permissao));
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public byte[] getDigitalUm() {
		return digitalUm;
	}

	public void setDigitalUm(byte[] digitalUm) {
		this.digitalUm = digitalUm;
	}

	public byte[] getDigitalDois() {
		return digitalDois;
	}

	public void setDigitalDois(byte[] digitalDois) {
		this.digitalDois = digitalDois;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public ResponsavelAluno getResponsavelAluno() {
		return responsavelAluno;
	}

	public void setResponsavelAluno(ResponsavelAluno responsavelAluno) {
		this.responsavelAluno = responsavelAluno;
	}

	

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
