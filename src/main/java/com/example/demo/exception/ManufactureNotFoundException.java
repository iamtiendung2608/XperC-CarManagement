package com.example.demo.exception;

public class ManufactureNotFoundException extends Exception{
    public ManufactureNotFoundException() {
        super("Manufacture not found");
    }
}
