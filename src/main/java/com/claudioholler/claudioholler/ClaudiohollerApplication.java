package com.claudioholler.claudioholler;



import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.domain.Cidade;
import com.claudioholler.claudioholler.domain.Cliente;
import com.claudioholler.claudioholler.domain.Endereco;
import com.claudioholler.claudioholler.domain.Estado;
import com.claudioholler.claudioholler.domain.ItemPedido;
import com.claudioholler.claudioholler.domain.Pagamento;
import com.claudioholler.claudioholler.domain.PagamentoComBoleto;
import com.claudioholler.claudioholler.domain.PagamentoComCartao;
import com.claudioholler.claudioholler.domain.Pedido;
import com.claudioholler.claudioholler.domain.Produto;
import com.claudioholler.claudioholler.domain.enums.EstadoPagamento;
import com.claudioholler.claudioholler.domain.enums.TipoCliente;
import com.claudioholler.claudioholler.respositories.CategoriaRepository;
import com.claudioholler.claudioholler.respositories.CidadeRepository;
import com.claudioholler.claudioholler.respositories.ClienteRepository;
import com.claudioholler.claudioholler.respositories.EnderecoRepository;
import com.claudioholler.claudioholler.respositories.EstadoRepository;
import com.claudioholler.claudioholler.respositories.ItemPedidoRepository;
import com.claudioholler.claudioholler.respositories.PagamentoRepository;
import com.claudioholler.claudioholler.respositories.PedidoRepository;
import com.claudioholler.claudioholler.respositories.ProdutoRepository;

@SpringBootApplication
public class ClaudiohollerApplication implements CommandLineRunner {

	@Autowired //instanciar automaticamente no sprinboot
	private CategoriaRepository categoriaRepository;
	@Autowired 
	private ProdutoRepository produtoRepository;
	@Autowired 
	private EstadoRepository estadoRepository;
	@Autowired 
	private CidadeRepository cidadeRepository;
	@Autowired 
	private ClienteRepository clienteRepository;
	@Autowired 
	private EnderecoRepository enderecoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(ClaudiohollerApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().add(p1);
		cat1.getProdutos().add(p2);
		cat1.getProdutos().add(p3);
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat1);
		
		categoriaRepository.save(cat1);
		categoriaRepository.save(cat2);
		produtoRepository.save(p1);
		produtoRepository.save(p2);
		produtoRepository.save(p3);
		
		////////
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia" , est1);
		Cidade c2 = new Cidade(null,"Sao Paulo" , est2);
		Cidade c3 = new Cidade(null,"Campinas" , est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().add(c2);
		est2.getCidades().add(c3);
		
		estadoRepository.save(est1);
		estadoRepository.save(est2);
		cidadeRepository.save(c1);
		cidadeRepository.save(c2);
		cidadeRepository.save(c3);
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com", "123456456", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().add("123456");
		cli1.getTelefones().add("12345612132");
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "3843543", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3888888", cli1, c2);
		
		cli1.getEnderecos().add(e1);
		cli1.getEnderecos().add(e2);
		//para salvar os clientes no banco de dados temos que fazer os repositoryes e criar as dependencias aqui
		
		clienteRepository.save(cli1);
		enderecoRepository.save(e1);
		enderecoRepository.save(e2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().add(ped1);
		cli1.getPedidos().add(ped2);
		
		pedidoRepository.save(ped1);
		pedidoRepository.save(ped2);
		pagamentoRepository.save(pagto1);
		pagamentoRepository.save(pagto2);
		
		ItemPedido it1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(ped1, p2, 0.00, 2, 80.00);
		ItemPedido it3 = new ItemPedido(ped2, p2, 100.00, 2, 800.00);
		
		ped1.getItens().add(it1);
		//ped1.getItens().addAll((Arrays.asList(it1));
		ped1.getItens().add(it2);
		ped2.getItens().add(it3);
		
		p1.getItens().add(it1);
		p2.getItens().add(it3);
		p3.getItens().add(it2);
		
		itemPedidoRepository.save(it1);
		itemPedidoRepository.save(it2);
		itemPedidoRepository.save(it3);
		
		
	}
	

}
