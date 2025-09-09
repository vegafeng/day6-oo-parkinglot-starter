package com.afs.parkinglot;

public class WrongTicketException extends Exception {
    public WrongTicketException() {
        super(GlobalExceptionMSG.WRONG_TICKET_EXCEPTIONMSG);
    }
}
