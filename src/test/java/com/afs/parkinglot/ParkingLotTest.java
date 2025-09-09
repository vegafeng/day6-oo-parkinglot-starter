package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_car_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkTicket = parkingLot.park(car);
        assertEquals(parkingLot.getCarToParkingTicket().get(car), parkTicket);
    }
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->parkingLot.fetch(car, wrongTicket));
        assertEquals(GlobalExceptionMSG.WRONG_TICKET_EXCEPTIONMSG, wrongTicketException.getMessage());
    }
    @Test
    void should_throw_exception_when_fetch_given_used_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkTicket = parkingLot.park(car);
        parkingLot.fetch(car, parkTicket);
        UsedTicketException usedTicketException = assertThrows(UsedTicketException.class, ()->parkingLot.fetch(car, parkTicket));
        assertEquals(GlobalExceptionMSG.USED_TICKET_EXCEPTIONMSG, usedTicketException.getMessage());
    }
    @Test
    void should_return_car_when_fetch_given_right_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkTicket = parkingLot.park(car);
        Car car2 = parkingLot.fetch(car, parkTicket);
        assertEquals(car, car2);
    }
    @Test
    void should_throw_exception_when_park_given_full_parkingLot() throws Exception {
        int MAX_PARKING_CARS = 10;
        ParkingLot parkingLot = new ParkingLot();
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(parkingLot, car));
        Car car = new Car();
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->parkingLot.park(car));
        assertEquals(GlobalExceptionMSG.UNAVAILABLE_PARKING_SPACE_EXCEPTIONMSG, unavailableParkingSpaceException.getMessage());
    }
    private void parkCar(ParkingLot parkingLot, Car car) {
        try {
            parkingLot.park(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void should_return_matching_car_when_fetch_given_right_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkTicket1 = parkingLot.park(car1);
        ParkingTicket parkTicket2 = parkingLot.park(car2);
        assertEquals(car1, parkingLot.fetch(car1, parkTicket1));
        assertEquals(car2, parkingLot.fetch(car2, parkTicket2));
    }



}
