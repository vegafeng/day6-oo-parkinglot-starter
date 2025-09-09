package com.afs.parkinglot;

public class UnavailableParkingSpaceException extends Exception{
    public UnavailableParkingSpaceException(){
        super(GlobalExceptionMSG.UNAVAILABLE_PARKING_SPACE_EXCEPTIONMSG);
    }
}
