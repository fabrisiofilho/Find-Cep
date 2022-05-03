package br.com.fabrisio.cepapi.exceptions;

public class CepExpetion extends RuntimeException {
    public CepExpetion() {
        super();
    }
    public CepExpetion(String message, Throwable type) {
        super(message, type);
    }

    public CepExpetion(String message) {
        super(message);
    }
}
