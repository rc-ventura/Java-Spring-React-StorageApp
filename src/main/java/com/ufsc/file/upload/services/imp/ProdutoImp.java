package com.ufsc.file.upload.services.imp;

import com.ufsc.file.upload.exceptions.ProductNotFoundException;
import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.repositories.ProdutoRepository;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoImp {
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	
	public Produto update(Long id, Produto produto) {
		try{
                    Produto produtoEntity = produtoRepository.getReferenceById(id);
        
		
		produtoEntity.setNome(produto.getNome());
		produtoEntity.setPreco(produto.getPreco());
		produtoEntity.setQtd(produto.getQtd());
        
		
		return produtoRepository.save(produtoEntity);		
	}catch (Exception e){
            throw new ProductNotFoundException(id);
        }
        }

	
	
	public String deleteById(Long id) {
            if(!produtoRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
            produtoRepository.deleteById(id);
            return "Product with id " +id+ "has been deleted sucess.";
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
			throw new ProductNotFoundException(id);
		}
	}
	
}
