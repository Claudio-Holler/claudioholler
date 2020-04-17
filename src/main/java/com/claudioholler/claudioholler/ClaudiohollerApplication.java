package com.claudioholler.claudioholler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.domain.Produto;
import com.claudioholler.claudioholler.respositories.CategoriaRespository;
import com.claudioholler.claudioholler.respositories.ProdutoRespository;

@SpringBootApplication
public class ClaudiohollerApplication implements CommandLineRunner {

	@Autowired //instanciar automaticamente no sprinboot
	private CategoriaRespository categoriaRepository;
	
	@Autowired //instanciar automaticamente no sprinboot
	private ProdutoRespository produtoRepository;
	
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
		
	}

}
