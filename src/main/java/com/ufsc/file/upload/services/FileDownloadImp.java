
package com.ufsc.file.upload.services;

import com.ufsc.file.upload.exceptions.StorageException;
import com.ufsc.file.upload.util.StorageProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

/**
 *
 * @author RC_Ventura
 */
@Service
public class FileDownloadImp implements FileDownloadService {

    private final Path rootLocation;
    private Path foundFile;
    
    @Autowired
    public FileDownloadImp (StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    
    @Override
    public Resource getFileAsResource(String fileCode) {
        try {
            Files.list(rootLocation).forEach(file -> {
                if (file.getFileName().toString().startsWith(fileCode)){
                    foundFile = file;
                    
                }
            });
                if (foundFile != null) {
                    return new UrlResource(foundFile.toUri());
                }
                    } catch (IOException e) {
                        throw new StorageException ("Failed to Download file", e);
                    }
        return null;
    
    }
    
}


