package com.claudioholler.claudioholler.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudioholler.claudioholler.domain.Cliente;
import com.claudioholler.claudioholler.dto.ClienteDto;
import com.claudioholler.claudioholler.dto.ClienteNewDto;
import com.claudioholler.claudioholler.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")//ENDPOINT categorias
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<?> find(@PathVariable Integer id){
		Cliente obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
		//Cliente cat1 = new Cliente(1, "Informatica");
		//Cliente cat2 = new Cliente(2, "Escritorio");
		
		//List<Cliente> lista = new ArrayList();
		//lista.add(cat1);
		//lista.add(cat2);
		
		//return "REST está funcionando";
	    //return lista;
	}
	
	@RequestMapping(method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<List<ClienteDto>> findAll(){
		List<Cliente> lista = service.retornaTodasClientes();
		//percorrer uma lista usando o stream do java 8
		List<ClienteDto> listDto = lista.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="page", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<Page<ClienteDto>> acharPagina(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction)
	{
		Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
		//percorrer uma lista usando o stream do java 8
		Page<ClienteDto> listDto = lista.map(obj -> new ClienteDto(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Cliente obj, @PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	//observacoes @Valid para fazer as validacoes do dto como tamanho e not null la definidas
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto objDto){
		Cliente obj = service.fromDTO(objDto);
		System.out.print("TESTE00 Insert Cliente::::"+obj.getId());
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}

}
