package com.ufsc.file.upload.services;

import com.ufsc.file.upload.models.Produto;
import java.util.List;



public interface ProdutoService {
	
	
	
	
	public Produto update(Long id, Produto produto) ;
		
     
	public String deleteById(Long id) ;
           
        
	public Produto save(Produto produto) ;
	
	
	public List<Produto> findAll();
		
	

	public Produto findById(Long id) ;		
		
	
	
                }
