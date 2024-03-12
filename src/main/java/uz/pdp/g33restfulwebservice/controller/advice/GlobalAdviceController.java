package uz.pdp.g33restfulwebservice.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.g33restfulwebservice.dto.web.ErrorResponse;
import uz.pdp.g33restfulwebservice.exception.TodoNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalAdviceController {

    /*@ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<?> handleTodoNotFound(TodoNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }*/

    @ExceptionHandler(value = {TodoNotFoundException.class})
    public ResponseEntity<?> handleTodoNotFound(TodoNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .errorMessage(e.getMessage())
                                .path(request.getRequestURI())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            if (errors.get(fieldError.getField()) != null) {
                errors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                errors.put(fieldError.getField(), new ArrayList<>(List.of(fieldError.getDefaultMessage())));
            }
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errorMessage("Validation Error")
                        .body(errors)
                        .path(request.getRequestURI())
                        .build());
    }
}
