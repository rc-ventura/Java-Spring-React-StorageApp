
package com.ufsc.file.upload.services;

import com.ufsc.file.upload.exceptions.StorageException;
import com.ufsc.file.upload.exceptions.StorageFileNotFoundException;
import com.ufsc.file.upload.util.StorageProperties;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author RC_Ventura
 */
@Service
public  class FileUploadImp implements FileUploadService  {

    private final Path rootLocation;
    
    @Autowired
    public FileUploadImp(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    
    @Override
    public void init() {
        try{
            Files.createDirectory(rootLocation);
            
        }catch (IOException e){
            
        throw new StorageException ("Could not initialize folder for upload!");
        
        } 
    }

    @Override
    public  String store(String fileName, MultipartFile multipartFile) {
        try{
            if (multipartFile.isEmpty()){
            throw new StorageException ("Failed to store empty file" + multipartFile.getOriginalFilename());
            }
            
         String fileCode = RandomStringUtils.randomAlphanumeric(8);
         InputStream inputStream = multipartFile.getInputStream(); 
         Path filePath = this.rootLocation.resolve(fileCode + "-" + fileName);
         Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); 
         return fileCode;
         
        }catch (IOException e) {
            throw new StorageException("Failed to store file" + fileName,  e);
        }
         }

    @Override
    public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			UrlResource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return (Resource) resource;
			}
			else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    
    }

    @Override
    public Stream<Path> loadAll() {
        try{
            return Files.walk(this.rootLocation, 1).filter(path-> !path.equals(this.rootLocation))
                    .map(path-> this.rootLocation.relativize(path));
        }catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public void deleteAll() {

            FileSystemUtils.deleteRecursively(rootLocation.toFile());
        }
    }

