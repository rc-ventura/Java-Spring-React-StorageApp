
package com.ufsc.file.upload.exceptions;

/**
 *
 * @author RC_Ventura
 */
public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException (Long id) {
        super( "could not found the product with id" + " " + id );
    }
    
}
