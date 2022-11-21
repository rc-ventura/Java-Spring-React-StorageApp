
package com.ufsc.file.upload.models;

/**
 *
 * @author RC_Ventura
 */
public class FileUpload {
    
  private String fileName;
  private String downloadUri;   
  private Long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
  
  
  
    
}
