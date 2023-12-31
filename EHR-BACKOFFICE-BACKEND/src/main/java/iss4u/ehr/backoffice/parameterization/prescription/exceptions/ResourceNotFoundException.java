package iss4u.ehr.backoffice.parameterization.prescription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException (String message){
        // we use super, so we can pass the message to the super class
        super(message);
    }
}
