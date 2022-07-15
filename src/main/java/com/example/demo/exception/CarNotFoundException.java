package com.example.demo.exception;

public class CarNotFoundException extends Exception{
    public CarNotFoundException() {
        super("Car not found");
    }
}
