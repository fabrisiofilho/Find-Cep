package br.com.fabrisio.cepapi.exceptions;

public class RequestException extends RuntimeException {
    public RequestException(String message, Throwable type) {
        super(message, type);
    }

    public RequestException(String message) {
        super(message);
    }
}
