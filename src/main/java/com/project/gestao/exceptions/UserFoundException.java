package com.project.gestao.exceptions;

public class UserFoundException extends RuntimeException {
  private String field;

  public UserFoundException(String field) {
    super("O seguinte campo: " + field + " já está em uso!");
    this.field = field;
  }

  public String getField() {
    return field;
  }
}
