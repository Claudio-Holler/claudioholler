package com.claudioholler.claudioholler.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudioholler.claudioholler.domain.Cliente;
import com.claudioholler.claudioholler.respositories.ClienteRepository;
import com.claudioholler.claudioholler.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private ClienteRepository repo;
	
	public Cliente buscar(Integer Id){
		Optional<Cliente> obj = repo.findById(Id);
		/*if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! "+ Id
					+ "Tipo "+ Categoria.class.getName());
			
		}
		return obj.orElse(null);
		*/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontraado! ID: " + Id));
		
		
	}
}
