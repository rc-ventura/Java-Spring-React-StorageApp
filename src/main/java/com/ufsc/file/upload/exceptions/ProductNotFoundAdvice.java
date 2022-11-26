
package com.ufsc.file.upload.exceptions;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author RC_Ventura
 */
@ControllerAdvice
public class ProductNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map <String, String> exceptionHandler(ProductNotFoundException e){
        
        Map <String,String> errorMap = new HashMap();
                errorMap.put("errorMessage", e.getMessage());
        
                return errorMap;
    }
    
}
