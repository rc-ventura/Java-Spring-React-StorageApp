
package com.ufsc.file.upload.services.imp;

import com.ufsc.file.upload.models.Categoria;
import com.ufsc.file.upload.models.FileStorage;
import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.repositories.CategoriaRepository;
import com.ufsc.file.upload.repositories.ProdutoRepository;
import com.ufsc.file.upload.services.CategoriaService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RC_Ventura
 */

    
@Service
public class CategoriaImp implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository; 
        
        @Autowired
	private ProdutoRepository produtoRepository; 
	
	
	
	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaEntity = categoriaRepository.getReferenceById(id);
		
		categoriaEntity.setNome(categoria.getNome());
		
		
		
		return categoriaRepository.save(categoriaEntity);		
	}
	
	
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}

	public Categoria findById(Long id) {		
		try {
			return categoriaRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Categoria id: " + id);
		}
	}

    public Categoria addProduto(Long id_categoria, Long id_produto) {

         Categoria categoria = categoriaRepository.findById(id_categoria).get();
         Produto produto = produtoRepository.findById(id_produto).get();
      
         categoria.getProdutos().add(produto);
         categoriaRepository.save(categoria);
         return categoria;
    }

    public Categoria removeProduto(Long id_categoria, Long id_produto) {
        
         Categoria categoria = categoriaRepository.findById(id_categoria).get();
         Produto produto = produtoRepository.findById(id_produto).get();
         
         categoria.getProdutos().remove(produto);
         categoriaRepository.save(categoria);
         return categoria;
    }
	
}

    

