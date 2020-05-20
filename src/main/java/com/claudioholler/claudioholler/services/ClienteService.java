package com.claudioholler.claudioholler.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.claudioholler.claudioholler.domain.Cidade;
import com.claudioholler.claudioholler.domain.Cliente;
import com.claudioholler.claudioholler.domain.Endereco;
import com.claudioholler.claudioholler.domain.enums.TipoCliente;
import com.claudioholler.claudioholler.dto.ClienteDto;
import com.claudioholler.claudioholler.dto.ClienteNewDto;
import com.claudioholler.claudioholler.respositories.CidadeRepository;
import com.claudioholler.claudioholler.respositories.ClienteRepository;
import com.claudioholler.claudioholler.respositories.EnderecoRepository;
import com.claudioholler.claudioholler.services.exceptions.DataIntegrityException;
import com.claudioholler.claudioholler.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private ClienteRepository repo;
	private EnderecoRepository enderecoRepository;
	
	@Autowired//instancia automaticamente o objeto abaixo no Spring Boot
	private CidadeRepository cidadeRepository;
	
	
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
	
	
	@Transactional//uso da anotacao Transactional para salvar tanto o Cliente como os Enderecos na mesma transacao
	public Cliente insert(Cliente obj){
		System.out.print("TESTE01 Insert Cliente");
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
	
	public Cliente fromDTO(ClienteNewDto objDto){
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);	
		Endereco end = new Endereco(null, objDto.getLogradouro(), 
				objDto.getNumero(), 
				objDto.getComplemento(), 
				objDto.getBairro(), 
				objDto.getCep(), 
				cli, 
				cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newobj, Cliente obj){
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}
	
}
