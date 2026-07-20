package com.example.oragnization.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.oragnization.controller.EmployeeController;

@RestControllerAdvice(assignableTypes = {EmployeeController.class
		})
public class GlobalException {

	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(
            RuntimeException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleMethodArgumentNotValidException(
//    		MethodArgumentNotValidException ex) {
//
//        return new ResponseEntity<>(
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST);
//    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMethodArgumentNotValidException(
    		MethodArgumentNotValidException ex) {
    	Map<String,String> errorMap=new HashMap<>();
    	ex.getBindingResult().getAllErrors().forEach(error->{
    		String fieldName=((FieldError)error).getField();
    		String message =error.getDefaultMessage();
    		errorMap.put(fieldName, message);
    	});
    	

        return errorMap;
    }
}
