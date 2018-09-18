package me.dlevin.aspectdemo.util;

import java.time.LocalDateTime;

public final class Logger {

  public static void debug(final String message) {
    System.out.println("[" + LocalDateTime.now() + "]\t" + message);
  }

}
