package com.pragma.plazoleta.infrastructure.advice;

import com.pragma.plazoleta.application.exception.CustomerAlreadyHasAnOrderException;
import com.pragma.plazoleta.application.exception.OrderNotValidException;
import com.pragma.plazoleta.application.exception.UnauthorizedDishModificationException;
import com.pragma.plazoleta.application.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.exception.SendMessageException;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
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
    public ResponseEntity<ProblemDetail> userAlreadyExistException(UserAlreadyExistException error) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                error.getMessage());
        problemDetail.setTitle("Usuario ya está registrado");
        return ResponseEntity.status(409).body(problemDetail);
    }

    @ExceptionHandler(value = UserIsNotOwnerException.class)
    public ResponseEntity<ProblemDetail> userIsNotOwnerException(UserIsNotOwnerException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,
                error.getMessage());
        problemDetail.setTitle("Usuario no propietario.");
        return ResponseEntity.status(401).body(problemDetail);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ProblemDetail> dishNotFoundException(NotFoundException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                error.getMessage());
        problemDetail.setTitle("Not found");
        return ResponseEntity.status(404).body(problemDetail);
    }

    @ExceptionHandler(value = UnauthorizedDishModificationException.class)
    public ResponseEntity<ProblemDetail> unauthorizedDishModificationException(UnauthorizedDishModificationException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,
                error.getMessage());
        problemDetail.setTitle("El usuario no es el propietario");
        return ResponseEntity.status(403).body(problemDetail);
    }

    @ExceptionHandler(value = OrderNotValidException.class)
    public ResponseEntity<ProblemDetail> orderNotValidException(OrderNotValidException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                error.getMessage());
        problemDetail.setTitle("Pedido no valido.");
        return ResponseEntity.status(409).body(problemDetail);
    }

    @ExceptionHandler(value = CustomerAlreadyHasAnOrderException.class)
    public ResponseEntity<ProblemDetail> customerAlreadyHasAnOrderException(CustomerAlreadyHasAnOrderException error) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                error.getMessage());
        problemDetail.setTitle("El usuario ya tiene un pedido.");
        return ResponseEntity.status(409).body(problemDetail);
    }
    @ExceptionHandler(value = SendMessageException.class)
    public ResponseEntity<ProblemDetail> sendMessageException(SendMessageException error) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                error.getMessage());
        problemDetail.setTitle("Notification could not be sent");
        return ResponseEntity.status(500).body(problemDetail);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existingValue, newValue) -> existingValue + " | " + newValue));
    }
}
