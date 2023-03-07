package br.com.xerosorvetes.flavorapi.application.exception;

public class PeopleNotFoundException extends RuntimeException {

  private static final String MESSAGE = "People id: %s not found";
  public PeopleNotFoundException(String id) {
    super(String.format(MESSAGE, id));
  }
}
