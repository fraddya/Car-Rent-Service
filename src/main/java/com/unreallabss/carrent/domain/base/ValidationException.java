package com.unreallabss.carrent.domain.base;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(String message) {
    super(message);
  }

}
