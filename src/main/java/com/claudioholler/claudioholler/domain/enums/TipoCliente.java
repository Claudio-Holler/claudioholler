package com.claudioholler.claudioholler.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao){
		this.cod = cod;
		this.descricao = descricao;
	}

	//somente o GET / SET não porque não se muda valor é fixo
	public int getCod(){
		return cod;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	//metdo estatico você pode rodar mesmo sem o objeto estar instanciado
	public static TipoCliente toEnum(Integer cod){
		
		if (cod == null){
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())){
			  return x;
		 }
		}
		
		throw new IllegalArgumentException("Codigo/Id inválido "+ cod);
	
		
	}
}
