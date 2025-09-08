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
        Car car = new Car();
        ParkingTicket parkTicket = SuperParkingLotBoy.getParkingLots().getFirst().park(car);
        assertEquals(SuperParkingLotBoy.getParkingLots().getFirst().getCarToParkingTicket().get(car), parkTicket);
    }
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car();
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
        Car car = new Car();
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
        Car car = new Car();
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
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(SuperParkingLotBoy.getParkingLots().getFirst(), car));
        Car car = new Car();
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->SuperParkingLotBoy.getParkingLots().getFirst().park(car));
        assertEquals("Parking Space Unavailable", unavailableParkingSpaceException.getMessage());
    }

    @Test
    void should_return_matching_car_when_fetch_given_right_ticket() throws Exception {
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkTicket1 = parkingLot.park(car1);
        ParkingTicket parkTicket2 = parkingLot.park(car2);
        assertEquals(car1, SuperParkingLotBoy.getParkingLots().getFirst().fetch(car1, parkTicket1));
        assertEquals(car2, SuperParkingLotBoy.getParkingLots().getFirst().fetch(car2, parkTicket2));
    }
    @Test
    void should_park_car_by_order_when_park_given_cars() throws Exception {
        int FIRST_MAX_PARKING_CARS = 6;
        int SECOND_MAX_PARKING_CARS = 2;
        SuperParkingLotBoy SuperParkingLotBoy = new SuperParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperParkingLotBoy.setParkingLot(parkingLot);
        SuperParkingLotBoy.setParkingLot(parkingLot2);
        IntStream.rangeClosed(1, FIRST_MAX_PARKING_CARS)
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(SuperParkingLotBoy.getParkingLots().getFirst(), car));
        IntStream.rangeClosed(1, SECOND_MAX_PARKING_CARS)
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(SuperParkingLotBoy.getParkingLots().get(1), car));
        Car car = new Car();
        SuperParkingLotBoy.park(car);
        assertTrue(SuperParkingLotBoy.getParkingLots().get(1).getCarToParkingTicket().containsKey(car));
    }

    private void parkCar(ParkingLot parkingLot, Car car) {
        try {
            parkingLot.park(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
