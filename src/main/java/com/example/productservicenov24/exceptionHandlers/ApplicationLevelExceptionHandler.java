package com.example.productservicenov24.exceptionHandlers;

import com.example.productservicenov24.Dtos.ExceptionDto;
import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationLevelExceptionHandler {
    @ExceptionHandler(ProductRelatedException.class)
    public ResponseEntity<ExceptionDto> handleProductRelatedExceptions(Exception ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RestTemplateRelatedException.class)
    public ResponseEntity<ExceptionDto> handleRestTemplateRelatedExceptions(Exception ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleAllExceptions(Exception ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
