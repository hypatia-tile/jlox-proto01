package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;

class Interpreter implements Expr.Visitor<Object> {

  @Override
  public Object visitBinaryExpr(Binary expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitBinaryExpr'");
  }

  @Override
  public Object visitGroupingExpr(Grouping expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitGroupingExpr'");
  }

  @Override
  public Object visitLiteralExpr(Literal expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitLiteralExpr'");
  }

  @Override
  public Object visitUnaryExpr(Unary expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitUnaryExpr'");
  }
}
