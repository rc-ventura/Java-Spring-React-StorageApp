package com.ufsc.file.upload.controllers;

import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.services.FileStorageService;
import com.ufsc.file.upload.services.imp.ProdutoImp;
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
	
    private final ProdutoImp produtoImp;
    
    @Autowired
    public ProdutoController(ProdutoImp produtoImp){
        this.produtoImp = produtoImp;
    }
    
        
        
	@PutMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
		Produto produtoAtualizado = produtoImp.update(id, produto);
		return ResponseEntity.ok().body(produtoAtualizado);
	}
	
	
	@DeleteMapping(value = "/produtos/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		produtoImp.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/produtos")
	public ResponseEntity<Produto> save(@RequestBody Produto produto ){
		Produto produtosSaved = produtoImp.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
				.buildAndExpand(produtosSaved.getId()).toUri();
		
		return ResponseEntity.created(uri).body(produtosSaved);		
	}
        
        

    
	
	@GetMapping(value = "/produtos")
	public ResponseEntity<List<Produto>> findAll(){
		
		List<Produto> produtos = produtoImp.findAll();		
		return ResponseEntity.ok().body(produtos);		
	}
	
	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		
		Produto c = produtoImp.findById(id);
		return ResponseEntity.ok().body(c);		
		
	}
	
	
        //add FileUpload -> Produto
        
	@PutMapping(value = "/produtos/{id_produto}/addFiles/{id_file}")
         public ResponseEntity<Produto> addFiles(@PathVariable Long id_produto,
            @PathVariable String id_file) {
        Produto produto = produtoImp.addFiles(id_produto, id_file);
        return ResponseEntity.ok().body(produto);

    }

            //delete FileUpload -> Produto

        @DeleteMapping(value = "/produtos/{id_produto}/removeFiles/{id_file}")
        public ResponseEntity<Produto> removeFiles(@PathVariable Long id_produto,
            @PathVariable String id_file) {
        Produto produto = produtoImp.removeFiles(id_produto, id_file);
        return ResponseEntity.ok().body(produto);

    }


	//Get FileUpload -> Produto
        

}
