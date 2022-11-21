
package com.ufsc.file.upload.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author RC_Ventura
 * 
 */
@Service
public interface FileDownloadService {
    
      public Resource getFileAsResource(String fileCode);

    
}
