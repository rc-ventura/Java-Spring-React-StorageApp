
package com.ufsc.file.upload.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author RC_Ventura
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    
    private String location = "File-uploads";
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
}
