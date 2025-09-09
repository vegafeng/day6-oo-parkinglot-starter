package com.afs.parkinglot;

public class UsedTicketException extends Exception {
    public UsedTicketException() {
        super(GlobalExceptionMSG.USED_TICKET_EXCEPTIONMSG);
    }
}
