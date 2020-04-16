package com.claudioholler.claudioholler.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claudioholler.claudioholler.domain.Categoria;
import com.claudioholler.claudioholler.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")//ENDPOINT categorias
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<?> find(@PathVariable Integer id){
		System.out.println("TESTE 1");
		Categoria obj = service.buscar(id);
		
		System.out.println("TESTE 2");
		return ResponseEntity.ok().body(obj);
		//Categoria cat1 = new Categoria(1, "Informatica");
		//Categoria cat2 = new Categoria(2, "Escritorio");
		
		//List<Categoria> lista = new ArrayList();
		//lista.add(cat1);
		//lista.add(cat2);
		
		//return "REST está funcionando";
	    //return lista;
	}

}
