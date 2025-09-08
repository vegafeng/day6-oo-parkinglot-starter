package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingLotBoyTest {
    @Test
    void should_return_ticket_when_park_given_car_parkingLot() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        assertEquals(SmartParkingLotBoy.getParkingLots().getFirst().getCarToParkingTicket().get(car), parkTicket);
    }
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->SmartParkingLotBoy.getParkingLots().getFirst().fetch(car, wrongTicket));
        assertEquals("Wrong ticket", wrongTicketException.getMessage());
    }


}
