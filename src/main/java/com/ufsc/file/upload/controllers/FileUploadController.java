
package com.ufsc.file.upload.controllers;

import com.ufsc.file.upload.models.FileUpload;
import com.ufsc.file.upload.services.FileUploadService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RC_Ventura
 */

@RestController
@CrossOrigin("http://localhost:3000")
public class FileUploadController {
    
    private final FileUploadService fileUploadService;
    
    @Autowired
    public FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }
    
    
    
    @PostMapping("/upload")
    public ResponseEntity <FileUpload> uploadFile(@RequestParam ("file") MultipartFile multipartFile)
     throws IOException {
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        
        String fileCode = fileUploadService.store(fileName, multipartFile);
        
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setSize(size);
        fileUpload.setDownloadUri("/downloadFile/" + fileCode);
        
        return new ResponseEntity(fileUpload, HttpStatus.OK);
    }         
    
    //@GetMapping("/files")
   // public String listUploadedFiles(FileUpload fileUpload) throws IOException{
        
        
        
   // }
            
    
}
