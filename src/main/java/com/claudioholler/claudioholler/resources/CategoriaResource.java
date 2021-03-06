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

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.dto.CategoriaDto;
import com.claudioholler.claudioholler.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")//ENDPOINT categorias
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<?> find(@PathVariable Integer id){
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		//Categoria cat1 = new Categoria(1, "Informatica");
		//Categoria cat2 = new Categoria(2, "Escritorio");
		
		//List<Categoria> lista = new ArrayList();
		//lista.add(cat1);
		//lista.add(cat2);
		
		//return "REST está funcionando";
	    //return lista;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	//observacoes @Valid para fazer as validacoes do dto como tamanho e not null la definidas
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto objDto){
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDto objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Categoria obj, @PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<List<CategoriaDto>> findAll(){
		List<Categoria> lista = service.retornaTodasCategorias();
		//percorrer uma lista usando o stream do java 8
		List<CategoriaDto> listDto = lista.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="page", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<Page<CategoriaDto>> acharPagina(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction)
	{
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		//percorrer uma lista usando o stream do java 8
		Page<CategoriaDto> listDto = lista.map(obj -> new CategoriaDto(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
	

}
