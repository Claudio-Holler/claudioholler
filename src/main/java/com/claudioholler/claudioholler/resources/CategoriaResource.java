package com.claudioholler.claudioholler.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")//ENDPOINT categorias
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET) //REST - http GET
	public String listar(){
		return "REST está funcionando";
	}

}
