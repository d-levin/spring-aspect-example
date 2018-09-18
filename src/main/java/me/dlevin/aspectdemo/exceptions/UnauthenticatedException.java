package me.dlevin.aspectdemo.exceptions;

public class UnauthenticatedException extends RuntimeException {

  private static final long serialVersionUID = 5594894098485995069L;

  public UnauthenticatedException(final String message) {
    super(message);
  }

}
