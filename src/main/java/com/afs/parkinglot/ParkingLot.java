package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int id;
    private final Map<Car, ParkingTicket> carToParkingTicket = new HashMap<>();
    private final static int MAX_PARKING_CAR_NUMBER = 10;
    private int REST_LOCATION;

    public ParkingLot() {
        REST_LOCATION = 10;
    }

    public int getREST_LOCATION() {
        return REST_LOCATION;
    }

    public Map<Car, ParkingTicket> getCarToParkingTicket() {
        return carToParkingTicket;
    }

    public ParkingTicket park(Car car) throws Exception {
        if (REST_LOCATION == 0) throw new UnavailableParkingSpaceException();
        ParkingTicket ParkingTicket = new ParkingTicket();
        carToParkingTicket.put(car, ParkingTicket);
        REST_LOCATION = REST_LOCATION - 1;
        return ParkingTicket;
    }

    public Car fetch(Car car, ParkingTicket parkingTicket) throws Exception {
        if (parkingTicket.getUsed_count() >= ParkingTicket.MAX_USED_COUNT) throw new UsedTicketException();
        if (!carToParkingTicket.get(car).equals(parkingTicket)) throw new WrongTicketException();
        parkingTicket.setUsed_count(parkingTicket.getUsed_count()+1);
        return car;
    }




}
