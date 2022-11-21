package com.ufsc.file.upload.controllers;

import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.services.ProdutoService;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin("http://localhost:3000")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PutMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
		Produto produtoAtualizado = produtoService.update(id, produto);
		return ResponseEntity.ok().body(produtoAtualizado);
	}
	
	
	@DeleteMapping(value = "/produtos/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		produtoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/produtos")
	public ResponseEntity<Produto> save(@RequestBody Produto produto){
		Produto produtosSaved = produtoService.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
				.buildAndExpand(produtosSaved.getId()).toUri();
		
		return ResponseEntity.created(uri).body(produtosSaved);		
	}
	
	@GetMapping(value = "/produtos")
	public ResponseEntity<List<Produto>> findAll(){
		
		List<Produto> produtos = produtoService.findAll();		
		return ResponseEntity.ok().body(produtos);		
	}
	
	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		
		Produto c = produtoService.findById(id);
		return ResponseEntity.ok().body(c);		
		
	}
	
	
	
	

}
