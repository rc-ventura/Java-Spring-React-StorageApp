package com.ufsc.file.upload.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Produto implements Serializable {
	private  static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private int preco;        
	private int qtd;
        
        
        //relacionamentos
        
        
         @ManyToOne 
         @JoinColumn(name = "id_categoria")
         private Categoria categoria;
         
         @OneToOne
         @JoinColumn(name = "id_fileStorage")
         private FileStorage fileStorage;

	
	public Produto() {}

	
	public Produto(Long id, String nome, int preco, int qtd, Categoria categoria, FileStorage fileStorage ) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qtd = qtd;
		this.categoria = categoria;
                this.fileStorage =  fileStorage;
                
        }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }
    
    
    
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
    
    public FileStorage getFileStorage() {
        return fileStorage;
    }
    

    public void setFileStorage(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

  
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        return Objects.equals(this.id, other.id);
    }

	
     

	

	

}
