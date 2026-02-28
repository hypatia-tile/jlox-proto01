package com.craftinginterpreters.lox;

import java.util.List;

public class Lexer {
  public static void lex(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    for (Token token : tokens) {
      System.out.printf("token: %s\n", token);
    }
  }
}
