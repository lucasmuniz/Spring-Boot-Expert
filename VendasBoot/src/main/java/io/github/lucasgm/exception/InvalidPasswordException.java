package io.github.lucasgm.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("A senha informada é inválida ");
    }
}
