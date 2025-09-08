package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingLotBoy extends ParkingLotBoy{
    public ParkingTicket park(Car car) throws UnavailableParkingSpaceException {
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getREST_LOCATION() > 0)
                .max(Comparator.comparingInt(ParkingLot::getREST_LOCATION))
                .map(parkingLot -> {
                    try {
                        return parkingLot.park(car);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(UnavailableParkingSpaceException::new);
    }
}
