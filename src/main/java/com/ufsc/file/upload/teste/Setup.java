
package com.ufsc.file.upload.teste;

import com.ufsc.file.upload.models.Categoria;
import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.repositories.CategoriaRepository;
import com.ufsc.file.upload.repositories.ProdutoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author RC_Ventura
 */

@Configuration
@Profile("test")
public class Setup implements CommandLineRunner{

    //dependency injection
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
   
   
    @Override
    public void run(String...args) throws Exception{
       
         //adicionando categorias
        
        Categoria cat1 = new Categoria (null, "Eletronicos");
        Categoria cat2 = new Categoria (null, "Jardinagem");
        Categoria cat3 = new Categoria (null, "Casa e Lar");
        
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3)); //salvando tudo

        
       
        // adicionando produtos
       
       Produto p1 = new Produto (null, "Smartphone Samsung", 5000, 52, cat1,null);
       Produto p2 = new Produto (null, "Smartphone Apple", 7000, 22, cat1,null);
       Produto p3 = new Produto (null, " Vasos", 50, 3000, cat2,null);
       Produto p4 = new Produto (null, "kit jardinagem", 500, 10, cat2,null);
       Produto p5 = new Produto (null, "Jogo de Banho", 300, 1002, cat3,null);
       
       
       produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5)); //salvando tudo
  
   
       
       
       
       
    }
    
}
