package com.claudioholler.claudioholler;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.domain.Cidade;
import com.claudioholler.claudioholler.domain.Estado;
import com.claudioholler.claudioholler.domain.Produto;
import com.claudioholler.claudioholler.respositories.CategoriaRespository;
import com.claudioholler.claudioholler.respositories.CidadeRespository;
import com.claudioholler.claudioholler.respositories.EstadoRespository;
import com.claudioholler.claudioholler.respositories.ProdutoRespository;

@SpringBootApplication
public class ClaudiohollerApplication implements CommandLineRunner {

	@Autowired //instanciar automaticamente no sprinboot
	private CategoriaRespository categoriaRepository;
	@Autowired 
	private ProdutoRespository produtoRepository;
	@Autowired 
	private EstadoRespository estadoRepository;
	@Autowired 
	private CidadeRespository cidadeRepository;
	
	
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
	}

}
