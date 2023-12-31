package iss4u.ehr.backoffice.parameterization.prescription.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PrescriptionServiceException extends RuntimeException {
    public PrescriptionServiceException(String message) {
        super(message);
    }
}