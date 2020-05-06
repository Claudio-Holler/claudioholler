package com.claudioholler.claudioholler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.respositories.CategoriaRepository;
import com.claudioholler.claudioholler.services.exceptions.DataIntegrityException;
import com.claudioholler.claudioholler.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private CategoriaRepository repo;
	
	public Categoria find(Integer Id){
		Optional<Categoria> obj = repo.findById(Id);
		/*if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! "+ Id
					+ "Tipo "+ Categoria.class.getName());
			
		}
		return obj.orElse(null);
		*/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontraado! ID: " + Id));
		
	}
	
	public Categoria insert(Categoria obj){
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj){
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer Id){
		find(Id);
		try{
			
			repo.deleteById(Id);	
			
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que tenha produtos");
			
		}
		
	}
	
	
	public List<Categoria> retornaTodasCategorias(){
		return repo.findAll();
		
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
