package org.mh.dts.common.http.servlet;

public class RequestException extends Exception {

  private static final long serialVersionUID = 1L;

  public RequestException() {
    super();
  }

  public RequestException(String message) {
    super(message);
  }

  public RequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestException(Throwable cause) {
    super(cause);
  }
}
