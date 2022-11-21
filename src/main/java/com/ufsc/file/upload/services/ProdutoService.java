package com.ufsc.file.upload.services;

import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.repositories.ProdutoRepository;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	
	public Produto update(Long id, Produto produto) {
		Produto produtoEntity = produtoRepository.getReferenceById(id);
		
		produtoEntity.setNome(produto.getNome());
		produtoEntity.setPreco(produto.getPreco());
		produtoEntity.setQtd(produto.getQtd());
		
		
		return produtoRepository.save(produtoEntity);		
	}
	
	
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}
	
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}

	public Produto findById(Long id) {		
		try {
			return produtoRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Produto id: " + id);
		}
	}
	
}
