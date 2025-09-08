package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingLotBoy extends ParkingLotBoy {
    @Override
    public ParkingTicket park(Car car) throws Exception {
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getREST_LOCATION() > 0)
                .findFirst()
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
