package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SuperParkingLotBoy extends ParkingLotBoy{
    @Override
    public ParkingTicket park(Car car) throws Exception {
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getREST_LOCATION() > 0)
                .max(Comparator.comparingDouble(parkingLot ->
                        (double) parkingLot.getREST_LOCATION() / ParkingLot.MAX_PARKING_CAR_NUMBER))
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
