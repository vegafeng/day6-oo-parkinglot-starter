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
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket parkTicket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->parkingLot.fetch(car, wrongTicket));
        assertEquals("Wrong ticket", wrongTicketException.getMessage());
    }
    @Test
    void should_throw_exception_when_fetch_given_used_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket parkTicket = parkingLot.park(car);
        parkingLot.fetch(car, parkTicket);
        UsedTicketException usedTicketException = assertThrows(UsedTicketException.class, ()->parkingLot.fetch(car, parkTicket));
        assertEquals("Ticket is already used", usedTicketException.getMessage());
    }
    @Test
    void should_return_car_when_fetch_given_right_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket parkTicket = parkingLot.park(car);
        Car car2 = parkingLot.fetch(car, parkTicket);
        assertEquals(car, car2);
    }



}
