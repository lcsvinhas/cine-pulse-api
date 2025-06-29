package br.com.cinepulse.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ": " + erro.getDefaultMessage());
        }
        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(),
                erros);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(),
                erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);

    }

    @ExceptionHandler(UsuarioException.class)
    protected ResponseEntity<Object> handleUsuarioException(UsuarioException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FilmeException.class)
    protected ResponseEntity<Object> handleFilmeException(FilmeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AtorException.class)
    protected ResponseEntity<Object> handleAtorException(AtorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FotoException.class)
    protected ResponseEntity<Object> handleFotoException(FotoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
