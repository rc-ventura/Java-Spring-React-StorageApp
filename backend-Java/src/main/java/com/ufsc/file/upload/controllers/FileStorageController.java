
package com.ufsc.file.upload.controllers;

import com.ufsc.file.upload.models.FileStorage;
import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.services.FileStorageService;
import com.ufsc.file.upload.services.imp.FileStorageImp;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author RC_Ventura
 */

@RestController
@CrossOrigin("http://localhost:3000")
public class FileStorageController {
    
    private final FileStorageImp fileStorageImp;
    
    @Autowired
    public FileStorageController(FileStorageImp fileStorageImp){
        this.fileStorageImp = fileStorageImp;
    }
    
    
    
    @PostMapping("/upload")
    public ResponseEntity <FileStorage> upload (@RequestParam ("file") MultipartFile file)
     throws IOException {
        
     
        FileStorage fileStorage = fileStorageImp.store(file);
        return  ResponseEntity.status(HttpStatus.OK).body(fileStorage);
    }         
    
   
    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    @ResponseBody
     public void  listUploadedFilesById(@PathVariable String id
     , HttpServletResponse response) throws IOException{
        
       FileStorage fileStorage = fileStorageImp.findById(id);
       
       response.setContentType(fileStorage.getType());
       response.getOutputStream().write(fileStorage.getData());
       response.getOutputStream().close();
       
     } 
     
     
     @RequestMapping(value = "/returnFiles/{id}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<FileStorage>  responseFindById(@PathVariable String id )
             throws IOException{
        
       FileStorage file = fileStorageImp.responseFindById(id);
       
      return ResponseEntity.ok().body(file);
       
     } 
     
     	@GetMapping(value = "/files")
	public ResponseEntity<List<FileStorage>> listUploadedFilesAll() {
		List<FileStorage> files = (List<FileStorage>) fileStorageImp.listUploadedFilesAll().toList();
		return ResponseEntity.ok().body(files);
	}
     
   
       
       
       @PutMapping(value = "/files/{id}")
	public ResponseEntity<FileStorage> updateFiles(@PathVariable String id, 
                @RequestParam ("file" )MultipartFile multipartFile)  throws IOException {

		
                 FileStorage  fileStorageSaved = fileStorageImp.update(id, multipartFile);
		
                return ResponseEntity.ok().body(fileStorageSaved);
	}
	
	
	@DeleteMapping(value = "/files/{id}")
	
        public ResponseEntity<String> deleteById(@PathVariable String id){
		fileStorageImp.deleteById(id);
		
                return ResponseEntity.noContent().build();
	}
	
	
	
	
	}
	
	
	
  
          
