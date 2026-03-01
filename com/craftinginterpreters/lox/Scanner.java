package com.craftinginterpreters.lox;

import java.util.List;

public class Scanner {
  public static void lex(String source) {
    Lexer lexer = new Lexer(source);
    List<Token> tokens = lexer.scanTokens();
    for (Token token : tokens) {
      System.out.printf("token: %s\n", token);
    }
  }
}
