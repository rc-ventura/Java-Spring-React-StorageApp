
package com.ufsc.file.upload.controllers;



import com.ufsc.file.upload.models.Categoria;
import com.ufsc.file.upload.services.imp.CategoriaImp;
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


/**
 *
 * @author RC_Ventura
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class CategoriaController {
	
	private final CategoriaImp categoriaImp;
    
    @Autowired
    public CategoriaController(CategoriaImp categoriaImp){
        this.categoriaImp = categoriaImp;
    }
	
	@PutMapping(value = "/categorias/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
		Categoria categoriaAtualizado = categoriaImp.update(id, categoria);
		return ResponseEntity.ok().body(categoriaAtualizado);
	}
	
	
	@DeleteMapping(value = "/categorias/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		categoriaImp.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/categorias")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
		Categoria categoriasSaved = categoriaImp.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/categorias/{id}")
				.buildAndExpand(categoriasSaved.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoriasSaved);		
	}
	
	@GetMapping(value = "/categorias")
	public ResponseEntity<List<Categoria>> findAll(){
		
		List<Categoria> categorias = categoriaImp.findAll();		
		return ResponseEntity.ok().body(categorias);		
	}
	
	@GetMapping(value = "/categorias/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		
		Categoria c = categoriaImp.findById(id);
		return ResponseEntity.ok().body(c);		
		
	}
	
	
	
	

}
