
package com.ufsc.file.upload.controllers;

import com.ufsc.file.upload.services.FileDownloadService;
import com.ufsc.file.upload.services.FileUploadService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author RC_Ventura
 */
@Controller
@CrossOrigin("http://localhost:3000")
public class FileDownloadController {
    

  private final FileDownloadService fileDownloadService;
    
    @Autowired
    public FileDownloadController(FileDownloadService fileDownloadService){
        this.fileDownloadService = fileDownloadService;
    }
    
    
  @GetMapping("/downloadFile/{fileCode}")
  public ResponseEntity<Resource> downloadFile(@PathVariable("fileCode") String fileCode) {
    
      Resource resource = null;
      resource = fileDownloadService.getFileAsResource(fileCode);
      if (resource == null) {
          return new ResponseEntity ("File not found", HttpStatus.NOT_FOUND);

      }
      String contentType = "application/octet-stream";
      String headerValue = "attachment; filename=\" " + resource.getFilename() + "\" ";
      
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
              .header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resource);
              }
    
}
