package com.claudioholler.claudioholler.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claudioholler.claudioholler.domain.Cliente;
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

}
