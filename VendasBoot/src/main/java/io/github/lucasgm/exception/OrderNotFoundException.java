package io.github.lucasgm.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Pedido não encontrado");
    }
}
