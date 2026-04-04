package com.craftinginterpreters.lox;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.craftinginterpreters.lox.TokenType.*;

class Parser {
  Expr parse() {
    try {
      return expression();
    } catch (ParseError error) {
      ErrorReporter.hadError = true;
      return null;
    }
  }

  private static class ParseError extends RuntimeException {}

  private final List<Token> tokens;
  private int current = 0;

  Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  private Expr expression() {
    return equality();
  }

  private Expr equality() {
    return leftAssoc(comparison(), tok -> tok == BANG_EQUAL || tok == EQUAL_EQUAL, this::comparison);
  }

  private Expr comparison() {
    return leftAssoc(term(), tok -> tok == GREATER || tok == GREATER_EQUAL || tok == LESS || tok == LESS_EQUAL,
        this::term);
  }

  private Expr term() {
    return leftAssoc(factor(), tok -> tok == MINUS || tok == PLUS, this::factor);
  }

  private Expr factor() {
    return leftAssoc(unary(), tok -> tok == SLASH || tok == STAR, this::unary);
  }

  private Expr unary() {
    if (match(BANG, MINUS)) {
      Token operator = previous();
      Expr right = unary();
      return new Expr.Unary(operator, right);
    }
    return primary();
  }

  private Expr primary() {
    if (match(FALSE)) return new Expr.Literal(false);
    if (match(TRUE)) return new Expr.Literal(true);
    if (match(NIL)) return new Expr.Literal(null);
    if (match(NUMBER, STRING)) {
      return new Expr.Literal(previous().literal);
    }

    if (match(LEFT_PAREN)) {
      Expr expr = expression();
      consume(RIGHT_PAREN, "Expect ')' after expression.");
      return new Expr.Grouping(expr);
    }
    throw error(peek(), "Expect expression.");
  }

  private Token consume(TokenType type, String message) {
    if (check(type)) return advance();
    throw error(peek(), message);
  }

  static ParseError error(Token token, String message) {
    ErrorReporter.parseError(token, message);
    return new ParseError();
  }

  private Expr leftAssoc(Expr baseExpr, Predicate<TokenType> opFilter, Supplier<Expr> nextLevelParser) {
    Expr acc = baseExpr;
    while (match(opFilter)) {
      Token operator = previous();
      Expr right = nextLevelParser.get();
      acc = new Expr.Binary(acc, operator, right);
    }
    return acc;
  }

  private Token previous() {
    return tokens.get(current - 1);
  }

  private boolean match(Predicate<TokenType> isOperator) {
    if (isAtEnd())
      return false;
    if (isOperator.test(peek().type)) {
      advance();
      return true;
    }
    return false;
  }

  private boolean match(TokenType... types) {
    for (TokenType type : types) {
      if (check(type)) {
        advance();
        return true;
      }
    }
    return false;
  }

  private Token advance() {
    if (!isAtEnd())
      current++;
    return previous();
  }

  private boolean check(TokenType type) {
    if (isAtEnd())
      return false;
    return peek().type == type;
  }

  private Token peek() {
    return tokens.get(current);
  }

  private boolean isAtEnd() {
    return peek().type == EOF;
  }

  private void synchronize() {
    advance();

    while (!isAtEnd()) {
      switch (peek().type) {
        case CLASS:
        case FUN:
        case VAR:
        case FOR:
        case IF:
        case WHILE:
        case PRINT:
        case RETURN:
          return;
        default:
          advance();
      }
    }
  }
}
