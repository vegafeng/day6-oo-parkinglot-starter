package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardParkingLotBoyTest {

    @Test
    void should_return_ticket_when_park_given_car_parkingLot() throws Exception {
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = standardParkingLotBoy.getParkingLots().getFirst().park(car);
        assertEquals(standardParkingLotBoy.getParkingLots().getFirst().getCarToParkingTicket().get(car), parkTicket);
    }
    @Test
    void should_throw_exception_when_fetch_given_wrong_ticket() throws Exception {
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = standardParkingLotBoy.getParkingLots().getFirst().park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, ()->standardParkingLotBoy.getParkingLots().getFirst().fetch(car, wrongTicket));
        assertEquals("Wrong ticket", wrongTicketException.getMessage());
    }
    @Test
    void should_throw_exception_when_fetch_given_used_ticket() throws Exception {
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = standardParkingLotBoy.getParkingLots().getFirst().park(car);
        standardParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        UsedTicketException usedTicketException = assertThrows(UsedTicketException.class, ()->standardParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket));
        assertEquals("Ticket is already used", usedTicketException.getMessage());
    }
    @Test
    void should_return_car_when_fetch_given_right_ticket() throws Exception {
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        Car car = new Car(1);
        ParkingTicket parkTicket = standardParkingLotBoy.getParkingLots().getFirst().park(car);
        Car car2 = standardParkingLotBoy.getParkingLots().getFirst().fetch(car, parkTicket);
        assertEquals(car, car2);
    }
    @Test
    void should_throw_exception_when_park_given_full_parkingLot() throws Exception {
        int MAX_PARKING_CARS = 10;
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        IntStream.rangeClosed(1, MAX_PARKING_CARS)
                .mapToObj(Car::new)
                .forEach(car -> parkCar(standardParkingLotBoy, car));
        Car car = new Car(11);
        UnavailableParkingSpaceException unavailableParkingSpaceException = assertThrows(UnavailableParkingSpaceException.class, ()->standardParkingLotBoy.getParkingLots().getFirst().park(car));
        assertEquals("Parking Space Unavailable", unavailableParkingSpaceException.getMessage());
    }
    private void parkCar(StandardParkingLotBoy standardParkingLotBoy, Car car) {
        try {
            standardParkingLotBoy.getParkingLots().getFirst().park(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void should_return_matching_car_when_fetch_given_right_ticket() throws Exception {
        StandardParkingLotBoy standardParkingLotBoy = new StandardParkingLotBoy();
        ParkingLot parkingLot = new ParkingLot();
        standardParkingLotBoy.setParkingLot(parkingLot);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        ParkingTicket parkTicket1 = parkingLot.park(car1);
        ParkingTicket parkTicket2 = parkingLot.park(car2);
        assertEquals(car1, standardParkingLotBoy.getParkingLots().getFirst().fetch(car1, parkTicket1));
        assertEquals(car2, standardParkingLotBoy.getParkingLots().getFirst().fetch(car2, parkTicket2));
    }

}
