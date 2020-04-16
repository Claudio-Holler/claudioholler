package com.claudioholler.claudioholler;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.respositories.CategoriaRespository;

@SpringBootApplication
public class ClaudiohollerApplication implements CommandLineRunner {

	@Autowired //instanciar automaticamente no sprinboot
	private CategoriaRespository categoriaRepository;
	
	public static void main(String[] args) {
		
		SpringApplication.run(ClaudiohollerApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriaRepository.save(cat1);
		categoriaRepository.save(cat2);
		
	}

}
