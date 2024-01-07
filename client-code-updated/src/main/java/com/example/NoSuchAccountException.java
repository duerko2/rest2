package com.example;

public class NoSuchAccountException extends Throwable {
    public NoSuchAccountException(String s) {
        super(s);
    }
}
