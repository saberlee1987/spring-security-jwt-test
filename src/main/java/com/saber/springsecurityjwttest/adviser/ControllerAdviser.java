package com.saber.springsecurityjwttest.adviser;

import com.saber.springsecurityjwttest.dto.ServiceErrorResponse;
import com.saber.springsecurityjwttest.dto.ServiceValidatorDto;
import com.saber.springsecurityjwttest.exceptions.ResourceExistException;
import com.saber.springsecurityjwttest.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceErrorResponse> resourceNotFoundException(ResourceNotFoundException ex){

        ServiceErrorResponse response =new ServiceErrorResponse();
        response.setCode(404);
        response.setMessage("ResourceNotFound");
        response.setOriginalMessage(ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ServiceErrorResponse> resourceExistException(ResourceExistException ex){

        ServiceErrorResponse response =new ServiceErrorResponse();
        response.setCode(400);
        response.setMessage("ResourceExistException");
        response.setOriginalMessage(ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }


    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServiceErrorResponse response =new ServiceErrorResponse();
        response.setCode(400);
        response.setMessage("ValidationError");
        response.setOriginalMessage(ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServiceErrorResponse response =new ServiceErrorResponse();
        response.setCode(400);
        response.setMessage("ValidationError");
        List<FieldError> fieldErrors =ex.getFieldErrors();
        List<ServiceValidatorDto> validatorDtoList = new ArrayList<>();
        response.setOriginalMessage(ex.getMessage());

        for (FieldError fieldError : fieldErrors) {
            ServiceValidatorDto validatorDto = new ServiceValidatorDto();
            validatorDto.setFieldName(fieldError.getField());
            validatorDto.setMessageError(fieldError.getDefaultMessage());
            fieldErrors.add(fieldError);
        }


        response.setValidations(validatorDtoList);

        return ResponseEntity.status(400).body(response);

    }
}
