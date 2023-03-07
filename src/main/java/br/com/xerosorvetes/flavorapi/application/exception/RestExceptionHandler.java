package br.com.xerosorvetes.flavorapi.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(PeopleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String peopleNotFound(PeopleNotFoundException ex) {
    log.debug("handling exception: {1}", ex);
    return ex.getMessage();
  }
}
