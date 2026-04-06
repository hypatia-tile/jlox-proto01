package com.craftinginterpreters.lox;

public class ErrorReporter {
  public static boolean hadError = false;
  public static boolean hadRuntimeError = false;
  public static String source = null;

  public static void error(int line, String message) {
    System.err.println(
        String.format("%s[line %d] Error: %s",
            source != null ? source + ": " : "",
            line, message));
  }

  public static void parseError(Token token, String message) {
    hadError = true;
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  public static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage() +
        "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }

  private static void report(int line, String loc, String message) {
    System.err.println(
        String.format("%d: %s Error: %s", line, loc, message)
        );
  }

}
