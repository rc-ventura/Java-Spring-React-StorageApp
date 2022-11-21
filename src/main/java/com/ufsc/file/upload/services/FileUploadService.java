
package com.ufsc.file.upload.services;

import java.nio.file.Path;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RC_Ventura
 */
public interface FileUploadService {
    
   public void init();
    
   public String store( String fileName, MultipartFile multipartFile);
    
   public Resource loadAsResource (String filename);
   
   public Path load(String filename);

   
   public Stream<Path> loadAll();
   
   public void deleteAll();
   
   
    
    
}
