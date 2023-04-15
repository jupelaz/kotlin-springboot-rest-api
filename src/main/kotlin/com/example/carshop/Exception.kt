package com.example.carshop


class AccountNotFoundException(message: String, id: String) :
    RuntimeException(String.format(message, id))
