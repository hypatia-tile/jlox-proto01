package com.craftinginterpreters.lox;

import java.util.List;

public class Scanner {
  public static List<Token> lex(String source) {
    Lexer lexer = new Lexer(source);
    List<Token> tokens = lexer.scanTokens();
    for (Token token : tokens) {
      System.out.printf("token: %s\n", token);
    }
    return tokens;
  }
}
