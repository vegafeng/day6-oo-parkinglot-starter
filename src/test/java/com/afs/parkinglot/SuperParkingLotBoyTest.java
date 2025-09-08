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
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SuperParkingLotBoy.getParkingLots().getFirst().park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->SuperParkingLotBoy.getParkingLots().getFirst().fetch(car, wrongTicket));
        assertEquals("Wrong ticket", wrongTicketException.getMessage());
    }
    @Test
    void should_throw_exception_when_fetch_given_used_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SuperParkingLotBoy.getParkingLots().getFirst().park(car);
        SuperParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        UsedTicketException usedTicketException = assertThrows(UsedTicketException.class, ()->SuperParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket));
        assertEquals("Ticket is already used", usedTicketException.getMessage());
    }
    @Test
    void should_return_car_when_fetch_given_right_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = SuperParkingLotBoy.getParkingLots().getFirst().park(car);
        Car car2 = SuperParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        assertEquals(car, car2);
    }

    @Test
    void should_throw_exception_when_park_given_full_parkingLot() throws Exception {
        int MAX_PARKING_CARS = 10;
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(Car::new)
                .forEach(car -> parkCar(SuperParkingLotBoy.getParkingLots().getFirst(), car));
        Car car = new Car(11);
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->SuperParkingLotBoy.getParkingLots().getFirst().park(car));
        assertEquals("Parking Space Unavailable", unavailableParkingSpaceException.getMessage());
    }

    @Test
    void should_return_matching_car_when_fetch_given_right_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        ParkingTicket parkTicket1 = parkingLot.park(car1);
        ParkingTicket parkTicket2 = parkingLot.park(car2);
        assertEquals(car1, SuperParkingLotBoy.getParkingLots().getFirst().fetch(car1, parkTicket1));
        assertEquals(car2, SuperParkingLotBoy.getParkingLots().getFirst().fetch(car2, parkTicket2));
    }




    private void parkCar(ParkingLot parkingLot, Car car) {
        try {
            parkingLot.park(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
