package com.nace.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class, HttpMessageNotReadableException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestExceptions(final Exception exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class, ServerErrorException.class,
            RuntimeException.class, HttpClientErrorException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleInternalException(final Exception exception, WebRequest request) {
        log.error("{}", exception);
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(value = NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorMessage noContentException(NoContentException e, WebRequest request) {
        log.error("{}", e);
        return new ErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                e.getMessage(),
                request.getDescription(false));
    }

}
