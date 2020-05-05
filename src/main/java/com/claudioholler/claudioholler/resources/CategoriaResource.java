package com.claudioholler.claudioholler.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.services.CategoriaService;

import ch.qos.logback.core.net.server.ServerListener;

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
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Categoria obj, @PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
