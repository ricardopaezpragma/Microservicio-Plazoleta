package com.pragma.plazoleta.infrastructure.advice;

import com.pragma.plazoleta.application.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.infrastructure.exception.DishNotFoundException;
import com.pragma.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
@ExceptionHandler(value = UserAlreadyExistException.class)
public ResponseEntity userAlreadyExistException(UserAlreadyExistException error) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
            error.getMessage());
    problemDetail.setTitle("Usuario ya está registrado");
    return ResponseEntity.status(409).body(problemDetail);
}

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException error) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                error.getMessage());
        problemDetail.setTitle("Usuario no encontrado");
        return ResponseEntity.status(404).body(problemDetail);
    }

    @ExceptionHandler(value = UserIsNotOwnerException.class)
    public ResponseEntity userIsNotOwnerException(UserIsNotOwnerException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,
                error.getMessage());
        problemDetail.setTitle("Usuario no propietario.");
        return ResponseEntity.status(401).body(problemDetail);
    }
    @ExceptionHandler(value = RestaurantNotFoundException.class)
    public ResponseEntity restaurantNotFoundException(RestaurantNotFoundException error) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                error.getMessage());
        problemDetail.setTitle("Restaurante no encontrado.");
        return ResponseEntity.status(404).body(problemDetail);
    }
    @ExceptionHandler(value = DishNotFoundException.class)
    public ResponseEntity dishNotFoundException(DishNotFoundException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                error.getMessage());
        problemDetail.setTitle("Plato no encontrado.");
        return ResponseEntity.status(404).body(problemDetail);
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .peek(System.out::println)
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existingValue, newValue) -> existingValue + " | " + newValue));
    }
}
