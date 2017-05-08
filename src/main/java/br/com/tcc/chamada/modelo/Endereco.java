package br.com.tcc.chamada.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco {

	@Id
	@GeneratedValue
	private Long id;
	
//	@NotBlank
//	@Length(min=5,max=60)
	private String rua;
	
//	@NotBlank
//	@Length(min=1,max=5)
	private String numero;
	
//	@Length(min=1,max=10)
	private String complemento;
	
//	@NotBlank
//	@Length(min=4,max=100)
	private String bairro;
	
//	@NotBlank
//	@Length(min=2,max=10)
	private String cidade;
	
//	@NotBlank
//	@Length(min=4,max=100)
	private String estado;
	
//	@NotBlank
//	@Length(min=8,max=0)
	private String cep;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

}
