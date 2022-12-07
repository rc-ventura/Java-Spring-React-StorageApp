package com.ufsc.file.upload.services.imp;

import com.ufsc.file.upload.exceptions.StorageException;
import com.ufsc.file.upload.exceptions.StorageFileNotFoundException;
import com.ufsc.file.upload.models.FileStorage;
import com.ufsc.file.upload.models.Produto;
import com.ufsc.file.upload.repositories.FileStorageRepository;
import com.ufsc.file.upload.services.FileStorageService;
import com.ufsc.file.upload.util.StorageProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RC_Ventura
 */
@Service
public class FileStorageImp implements FileStorageService {

    

    private final Path rootLocation;

    @Autowired
    public FileStorageImp(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Autowired
    FileStorageRepository fileStorageRepository;

    
    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);

        } catch (IOException e) {

            throw new StorageException("Could not initialize folder for upload!");

        }
    }

    @Override
    public FileStorage store( MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file" + file.getOriginalFilename());
            }
            
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStorage fileStorage = new FileStorage(null, fileName, 
                    file.getContentType(), file.getBytes(), file.getSize());
            
         
            return fileStorageRepository.save(fileStorage);

        } catch (IOException e) {
            throw new StorageException("Failed to store file" + file.getOriginalFilename(), e);
        }
    }

    /*
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return (Resource) resource;
            } else {
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
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }
*/
    @Override
    public void deleteAll() {

        FileSystemUtils.deleteRecursively(rootLocation.toFile());

    }

    
    public FileStorage update(String id, MultipartFile multipartFile) {
            try{
                    FileStorage fileStorageEntity = fileStorageRepository.getReferenceById(id);
		    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                   fileStorageEntity.setName(fileName);
                   fileStorageEntity.setType(multipartFile.getContentType());
		   fileStorageEntity.setSize(multipartFile.getSize());
		   fileStorageEntity.setData(multipartFile.getBytes());
                   
         
            return fileStorageRepository.save(fileStorageEntity);
        
        	}catch (IOException e){
            throw new StorageFileNotFoundException(id);
        }
        }
        

        
    

    public  String deleteById(String id) {
            
            if(!fileStorageRepository.existsById(id)){
            throw new StorageFileNotFoundException(id);
        }
            fileStorageRepository.deleteById(id);
            return "File with id " +id+ "has been deleted sucess.";
	}    

    public FileStorage responseFindById(String id) {
        
        return fileStorageRepository.findById(id).get();
        
    }

    
	public Stream <FileStorage> listUploadedFilesAll() {
		return fileStorageRepository.findAll().stream();
	}

    @Override
    public FileStorage findById(String id) {
        return fileStorageRepository.findById(id).get();
    }

    

    
    }

