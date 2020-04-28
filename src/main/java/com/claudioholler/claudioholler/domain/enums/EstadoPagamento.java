package com.claudioholler.claudioholler.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao){
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
	public static EstadoPagamento toEnum(Integer cod){
		
		if (cod == null){
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())){
			  return x;
		 }
		}
		
		throw new IllegalArgumentException("Codigo/Id inválido "+ cod);
	
		
	}

}
