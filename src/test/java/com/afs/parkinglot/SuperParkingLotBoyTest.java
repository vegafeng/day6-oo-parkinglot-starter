package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingLotBoyTest {
    @Test
    void should_return_ticket_when_park_given_car_parkingLot() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SuperParkingLotBoy.getParkingLots().getFirst().park(car);
        assertEquals(SuperParkingLotBoy.getParkingLots().getFirst().getCarToParkingTicket().get(car), parkTicket);
    }

}
