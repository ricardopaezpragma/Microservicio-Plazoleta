package com.pragma.plazoleta.infrastructure.advice;

import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity userAlreadyExistException(UserAlreadyExistException error){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "El usuario con correo: "+error.getEmail()+" ya está registrado.");
        problemDetail.setTitle("Usuario ya está registrado");
        return ResponseEntity.status(409).body(problemDetail);
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException exception){
       return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError)objectError).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage));
    }
}
