
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
    
   // @Autowired
   // private ComentarioRepository comentarioRepository;
    
    //@Autowired
   // private UsuarioRepository usuarioRepository;

   
    @Override
    public void run(String...args) throws Exception{
       
         //adicionando categorias
        
        Categoria cat1 = new Categoria (null, "Eletronicos");
        Categoria cat2 = new Categoria (null, "Jardinagem");
        Categoria cat3 = new Categoria (null, "Casa e Lar");
        
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3)); //salvando tudo

        
       
        // adicionando produtos
       
       Produto p1 = new Produto (null, "Smartphone Samsung", 5000, 52, cat1);
       Produto p2 = new Produto (null, "Smartphone Apple", 7000, 22, cat1);
       Produto p3 = new Produto (null, " Vasos", 50, 3000, cat2);
       Produto p4 = new Produto (null, "kit jardinagem", 500, 10, cat2);
       Produto p5 = new Produto (null, "Jogo de Banho", 300, 1002, cat3);
       
       
       produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5)); //salvando tudo
  
   
         //adicionando usuario

    
         
      //  Usuario ed2 = new Usuario(null, "Daniel Ventura","@daniel", "123mudar"); 
      // Usuario ed1 = new Usuario(null, "Rafael Ventura","@arquimedes", "123mudar"); 
     //  Usuario ed3 = new Usuario(null, "Laura Bender","@l23", "123mudar"); 
       
      //  usuarioRepository.saveAll(Arrays.asList(ed1,ed2,ed3)); //salvando tudo
       

    //associando usuarioes com postagens
    
      //  post1.setUsuario(ed1);
     //   post2.setUsuario(ed1);
     //   post3.setUsuario(ed3);
      //  post4.setUsuario(ed3);
      //  post5.setUsuario(ed2);
        
     //postagemRepository.saveAll(Arrays.asList(post1,post2,post3,post4,post5)); //salvando tudo

      
        
    //adicionando assunto
    
     //  Assunto ass1 = new Assunto(null,"#trip"); 
      // Assunto ass2 = new Assunto(null,"#vacation"); 
     //  Assunto ass3 = new Assunto(null,"#freedom"); 
      // Assunto ass4 = new Assunto(null,"#healthy"); 
     //  Assunto ass5 = new Assunto(null,"#lifestyle"); 
       
      // assuntoRepository.saveAll(Arrays.asList(ass1,ass2,ass3,ass4,ass5)); //salvando tudo

    
       
       //adicionando comentario
    
     //  Comentario com1 = new Comentario(null, "@rafael37", Instant.parse("2022-05-18T20:30:00Z"), "Top", post1); 
     //  Comentario com2 = new Comentario(null, "@laura24", Instant.parse("2022-05-19T20:30:00Z"), "Great", post2); 
      // Comentario com3 = new Comentario(null, "@carlos12", Instant.parse("2022-05-21T20:30:00Z"), "Que legal", post1); 
     //  Comentario com4 = new Comentario(null, "@daniel35", Instant.parse("2022-05-25T20:30:00Z"), "Amazing", post3); 
     //  Comentario com5 = new Comentario(null, "@gui38", Instant.parse("2022-05-22T20:30:00Z"), "Parabens", post1); 
       
       
      //  comentarioRepository.saveAll(Arrays.asList(com1,com2,com3,com4,com5)); //salvando tudo

  
        
        //associando postagem com assunto
        
        
     //   post1.getAssuntos().add(ass1);
      //  post1.getAssuntos().add(ass2);
      //  post2.getAssuntos().add(ass5);
      //  post3.getAssuntos().add(ass1);
       // post3.getAssuntos().add(ass2);
      //  post3.getAssuntos().add(ass3);
      //  post3.getAssuntos().add(ass2);
     //   post4.getAssuntos().add(ass3);
      //  post4.getAssuntos().add(ass4);
      //  post5.getAssuntos().add(ass3);
     //   post5.getAssuntos().add(ass4);
      //  post5.getAssuntos().add(ass5);


      //  postagemRepository.saveAll(Arrays.asList(post1,post2,post3,post4,post5)); //salvando tudo

        
    }
    
}
