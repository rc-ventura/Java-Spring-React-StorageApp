package com.ufsc.file.upload.repositories;

import com.ufsc.file.upload.models.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, String>{
	
}
