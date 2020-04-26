package com.claudioholler.claudioholler.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudioholler.claudioholler.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	
  
}
