package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_car_parkingLot(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket parkTicket = parkingLot.park(car);
        assertEquals(parkingLot.getCarToTicket().get(car), parkTicket);
    }
    @Test
    void should_return_null_when_fetch_given_wrong_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket parkTicket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->parkingLot.fetch(car, wrongTicket));
    }



}
