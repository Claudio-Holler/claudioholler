package com.claudioholler.claudioholler.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.claudioholler.claudioholler.domain.Cliente;

public class ClienteDto implements Serializable {
	  private static final long serialVersionUID = 1L;
	  
	  private Integer Id;
	  @NotEmpty(message="Preenchimento obrigatorio")
	  @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	  private String nome;
	  @NotEmpty(message="Preenchimento obrigatorio")
	  @Email(message="Email inválido")
	  private String email;
	  
	  //construtor 
	  public ClienteDto(){
		  
	  }
	  
	  public ClienteDto(Cliente obj){
		  Id = obj.getId();
		  nome = obj.getNome();
		  nome = obj.getEmail();
	  }

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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
	  
	  

}
