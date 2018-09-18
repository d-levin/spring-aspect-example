package me.dlevin.aspectdemo.exceptions;

public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = -4372341861542685042L;

  public UnauthorizedException(final String message) {
    super(message);
  }

}
