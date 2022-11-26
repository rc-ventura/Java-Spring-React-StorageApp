
package com.ufsc.file.upload.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author RC_Ventura
 */
@Entity
public class FileStorage implements Serializable {
    private static final long serialVersionUID= 1L;
    
  @Id
  @GeneratedValue (generator = "uuid")
  @GenericGenerator(name= "uuid", strategy = "uuid2")
  private String id;
  
  private String name; 
  private String type;
  
  @Lob
  private byte []data;
 
  private Long size;
  
  //relacionamentos
 
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "produto_id")
  private Produto produto;

  public FileStorage() {}
  
  public FileStorage( String id, String name, String type, byte[] data, Long size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.size = size;
    }
  
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final FileStorage other = (FileStorage) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
  
  
  
    
}
