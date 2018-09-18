package me.dlevin.aspectdemo.exceptions;

import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -5511254899426589624L;

  public EntityNotFoundException(final Class clazz, final Serializable id) {
    super("Entity of type [" + clazz.getSimpleName() + "] with ID [" + id + "] was not found");
  }

}
