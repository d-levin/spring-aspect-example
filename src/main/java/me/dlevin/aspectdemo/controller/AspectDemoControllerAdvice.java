package me.dlevin.aspectdemo.controller;

import me.dlevin.aspectdemo.exceptions.EntityNotFoundException;
import me.dlevin.aspectdemo.exceptions.UnauthenticatedException;
import me.dlevin.aspectdemo.exceptions.UnauthorizedException;
import me.dlevin.aspectdemo.util.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AspectDemoController.class)
public class AspectDemoControllerAdvice {

  @ExceptionHandler(UnauthenticatedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public void handleUnauthenticatedException(final UnauthenticatedException exception) {
    Logger.debug("Handled UnauthenticatedException\t" + exception.getMessage());
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public void handleUnauthorizedException(final UnauthorizedException exception) {
    Logger.debug("Handled UnauthorizedException\t" + exception.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleEntityNotFoundException(final EntityNotFoundException exception) {
    Logger.debug("Handled EntityNotFoundException\t" + exception.getMessage());
  }

}
