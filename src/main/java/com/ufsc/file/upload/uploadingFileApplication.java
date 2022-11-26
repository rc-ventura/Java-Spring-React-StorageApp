package com.ufsc.file.upload;

import com.ufsc.file.upload.services.FileStorageService;
import com.ufsc.file.upload.util.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class uploadingFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(uploadingFileApplication.class, args);
	}

        
        @Bean
        CommandLineRunner init(FileStorageService fileStorageService) {
            return (args) -> {
                fileStorageService.deleteAll();
                fileStorageService.init();
            };
        }
}
