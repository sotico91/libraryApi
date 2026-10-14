package com.library.libraryAPI.exceptions;

import com.library.libraryAPI.exceptions.impl.ResourceBadRequestException;
import com.library.libraryAPI.exceptions.impl.ResourceNotFoundException;
import com.library.libraryAPI.model.generalMessage.MessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerExceptionHandlerTest {

    private final GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

    @Test
    void testHandleException() {

        Exception exception = new Exception("Test exception");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(responseEntity.getBody()).getCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleResourceNotFoundException() {

        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");


        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleResourceNotFoundException(exception);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(responseEntity.getBody()).getCode());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleResourceBadRequestException() {

        ResourceBadRequestException exception = new ResourceBadRequestException("Bad request");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleConversion(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(responseEntity.getBody()).getCode());
        assertEquals("Bad request", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleNoResourceFoundException() {

        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET, "/no-existe");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(responseEntity.getBody()).getCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleMissingParameterException() {

        MissingServletRequestParameterException exception = new MissingServletRequestParameterException("isbn", "String");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testHandleMethodNotSupportedException() {

        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("DELETE");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
    }

    @Test
    void testHandleTypeMismatchException() {

        TypeMismatchException exception = new TypeMismatchException("abc", Integer.class);

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
