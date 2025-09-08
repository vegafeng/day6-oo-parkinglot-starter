package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int id;
    private final Map<Car, Ticket> carToTicket = new HashMap<>();
    private final static int MAX_PARKING_CAR_NUMBER = 10;
    private static int REST_LOCATION = 10;

    public ParkingLot() {
    }

    public Map<Car, Ticket> getCarToTicket() {
        return carToTicket;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        carToTicket.put(car, ticket);
        REST_LOCATION = REST_LOCATION - 1;
        return ticket;
    }

    public Car fetch(Car car, Ticket ticket) throws Exception {
        if (ticket.getUsed_count() >= Ticket.MAX_USED_COUNT) return null;
        if (!carToTicket.containsKey(car)) throw new Exception("车位不存在");
        if (!carToTicket.get(car).equals(ticket)) throw new WrongTicketException();
        ticket.setUsed_count(ticket.getUsed_count()+1);
        return car;
    }




}
