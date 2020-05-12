package com.claudioholler.claudioholler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.claudioholler.claudioholler.domain.Cliente;
import com.claudioholler.claudioholler.dto.ClienteDto;
import com.claudioholler.claudioholler.respositories.ClienteRepository;
import com.claudioholler.claudioholler.services.exceptions.DataIntegrityException;
import com.claudioholler.claudioholler.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private ClienteRepository repo;
	
	public Cliente buscar(Integer Id){
		Optional<Cliente> obj = repo.findById(Id);
		/*if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! "+ Id
					+ "Tipo "+ Cliente.class.getName());
			
		}
		return obj.orElse(null);
		*/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontraado! ID: " + Id));
		
		
	}
	
	
	public Cliente update(Cliente obj){
		Cliente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer Id){
		buscar(Id);
		try{
			
			repo.deleteById(Id);	
			
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas!!");
			
		}
		
	}
	
	
	public List<Cliente> retornaTodasClientes(){
		return repo.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDto objDto){
		return new Cliente((objDto.getId()==null) ? 1 : objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newobj, Cliente obj){
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}
	
}
