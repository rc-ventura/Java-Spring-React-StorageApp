
package com.ufsc.file.upload.services;

import com.ufsc.file.upload.models.Categoria;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author RC_Ventura
 */

    
public interface CategoriaService  {
	

	
	
	public Categoria update(Long id, Categoria categoria) ;
			
	
	
	public void deleteById(Long id);
	
	
	public Categoria save(Categoria categoria);
        
        
	public List<Categoria> findAll();
	

	public Categoria findById(Long id);
	
}

    

