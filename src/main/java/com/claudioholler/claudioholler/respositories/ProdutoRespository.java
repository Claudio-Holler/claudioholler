package com.claudioholler.claudioholler.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudioholler.claudioholler.domain.Produto;

public interface ProdutoRespository extends JpaRepository<Produto, Integer>{

	
  
}
