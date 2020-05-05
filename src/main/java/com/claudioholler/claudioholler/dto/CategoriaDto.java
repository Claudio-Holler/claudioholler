package com.claudioholler.claudioholler.dto;

import java.io.Serializable;

import com.claudioholler.claudioholler.domain.Categoria;

public class CategoriaDto implements Serializable {
	  private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String nome;
	
	public CategoriaDto(){
		
	}
	
public CategoriaDto(Categoria obj){
		Id = obj.getId();
		nome = obj.getNome();
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
	

}
