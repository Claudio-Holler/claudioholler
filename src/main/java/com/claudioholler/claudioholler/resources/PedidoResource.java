package com.claudioholler.claudioholler.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claudioholler.claudioholler.domain.Pedido;
import com.claudioholler.claudioholler.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")//ENDPOINT pedidos
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //REST - http GET
	public ResponseEntity<?> find(@PathVariable Integer id){
		Pedido obj = service.buscar(id);
		
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
