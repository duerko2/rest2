package com.example;

public class AccountAlreadyExistsException extends Throwable {
    public AccountAlreadyExistsException(String account_already_exists) {
        super(account_already_exists);
    }
}
