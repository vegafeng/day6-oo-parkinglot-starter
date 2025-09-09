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
        Car car = new Car();
        ParkingTicket parkTicket = SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        assertEquals(SmartParkingLotBoy.getParkingLots().getFirst().getCarToParkingTicket().get(car), parkTicket);
    }
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car();
        SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->SmartParkingLotBoy.getParkingLots().getFirst().fetch(car, wrongTicket));
        assertEquals(GlobalExceptionMSG.WRONG_TICKET_EXCEPTIONMSG, wrongTicketException.getMessage());
    }
    @Test
    void should_throw_exception_when_fetch_given_used_ticket() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car();
        ParkingTicket parkTicket = SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        SmartParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        UsedTicketException usedTicketException = assertThrows(UsedTicketException.class, ()->SmartParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket));
        assertEquals(GlobalExceptionMSG.USED_TICKET_EXCEPTIONMSG, usedTicketException.getMessage());
    }
    @Test
    void should_return_car_when_fetch_given_right_ticket() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car();
        ParkingTicket parkTicket = SmartParkingLotBoy.getParkingLots().getFirst().park(car);
        Car car2 = SmartParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        assertEquals(car, car2);
    }
    @Test
    void should_throw_exception_when_park_given_full_parkingLot() throws Exception {
        int MAX_PARKING_CARS = 10;
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(CAR -> new Car())
                .forEach(car -> parkCar(SmartParkingLotBoy.getParkingLots().getFirst(), car));
        Car car = new Car();
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->SmartParkingLotBoy.getParkingLots().getFirst().park(car));
        assertEquals(GlobalExceptionMSG.UNAVAILABLE_PARKING_SPACE_EXCEPTIONMSG, unavailableParkingSpaceException.getMessage());
    }

    @Test
    void should_return_matching_car_when_fetch_given_right_ticket() throws Exception {
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkTicket1 = parkingLot.park(car1);
        ParkingTicket parkTicket2 = parkingLot.park(car2);
        assertEquals(car1, SmartParkingLotBoy.getParkingLots().getFirst().fetch(car1, parkTicket1));
        assertEquals(car2, SmartParkingLotBoy.getParkingLots().getFirst().fetch(car2, parkTicket2));
    }
    @Test
    void should_park_car_by_order_when_park_given_cars() throws Exception {
        int FIRST_MAX_PARKING_CARS = 6;
        int SECOND_MAX_PARKING_CARS = 2;
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        SmartParkingLotBoy.setParkingLot(parkingLot2);
        IntStream.rangeClosed(1, FIRST_MAX_PARKING_CARS)
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(SmartParkingLotBoy.getParkingLots().getFirst(), car));
        IntStream.rangeClosed(1, SECOND_MAX_PARKING_CARS)
                .mapToObj(car -> new Car())
                .forEach(car -> parkCar(SmartParkingLotBoy.getParkingLots().get(1), car));
        Car car = new Car();
        SmartParkingLotBoy.park(car);
        assertTrue(SmartParkingLotBoy.getParkingLots().get(1).getCarToParkingTicket().containsKey(car));
    }

    @Test
    void should_throw_exception_when_park_given_full_parkingLots() throws Exception {
        int MAX_PARKING_CARS = 10;
        SmartParkingLotBoy SmartParkingLotBoy = new SmartParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingLotBoy.setParkingLot(parkingLot);
        SmartParkingLotBoy.setParkingLot(parkingLot2);
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(CAR -> new Car())
                .forEach(car -> parkCar(SmartParkingLotBoy.getParkingLots().getFirst(), car));
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(CAR -> new Car())
                .forEach(car -> parkCar(SmartParkingLotBoy.getParkingLots().get(1), car));
        Car car = new Car();
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->SmartParkingLotBoy.park(car));
        assertEquals(GlobalExceptionMSG.UNAVAILABLE_PARKING_SPACE_EXCEPTIONMSG, unavailableParkingSpaceException.getMessage());
    }

    private void parkCar(ParkingLot parkingLot, Car car) {
        try {
            parkingLot.park(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
