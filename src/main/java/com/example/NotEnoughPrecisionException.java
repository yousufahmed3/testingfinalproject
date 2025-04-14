package com.example;

public class NotEnoughPrecisionException extends Exception {
    public NotEnoughPrecisionException() {
        super("Not enough precision to compute result.");
    }
}
