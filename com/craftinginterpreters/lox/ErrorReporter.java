package com.craftinginterpreters.lox;

public class ErrorReporter {
  public static boolean hadError = false;
  public static String source = null;

  public static void error(int line, String message) {
    System.err.println(
        String.format("%s[line %d] Error: %s",
            source != null ? source + ": " : "",
            line, message));
  }

}
