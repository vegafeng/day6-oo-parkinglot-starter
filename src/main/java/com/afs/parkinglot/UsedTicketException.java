package com.afs.parkinglot;

public class UsedTicketException extends Exception {
    public UsedTicketException() {
        super("Ticket is already used");
    }
}
