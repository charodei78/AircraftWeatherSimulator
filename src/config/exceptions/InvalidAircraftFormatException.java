package config.exceptions;

public class InvalidAircraftFormatException extends Exception {
  public InvalidAircraftFormatException() {
    super();
  }

  public InvalidAircraftFormatException(String message) {
    super(message);
  }

}