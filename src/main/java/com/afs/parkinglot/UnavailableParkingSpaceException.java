package com.afs.parkinglot;

public class UnavailableParkingSpaceException extends Exception{
    public UnavailableParkingSpaceException(){
        super("Parking Space Unavailable");
    }
}
