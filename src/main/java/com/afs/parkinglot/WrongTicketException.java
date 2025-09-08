package com.afs.parkinglot;

public class WrongTicketException extends Exception {
    public WrongTicketException() {
        super("Wrong ticket");
    }
}
