package com.claudioholler.claudioholler.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.respositories.CategoriaRespository;

@Service
public class CategoriaService {

	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private CategoriaRespository repo;
	
	public Categoria buscar(Integer Id){
		System.out.println("TESTE 3");
		Optional<Categoria> obj = repo.findById(Id);
		return obj.orElse(null);
		
		
	}
}
