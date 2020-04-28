package com.claudioholler.claudioholler.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.claudioholler.claudioholler.domain.enums.EstadoPagamento;

//esta classe vai ser uma subclasse de Pagamento por isso esta extendendo Pagamento
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	//construtor padrao
	public PagamentoComBoleto(){
	}

	//construtor com superclasse porque esta extentendo pagamento
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
