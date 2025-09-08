package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingLotBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
    public ParkingTicket park(Car car) throws UnavailableParkingSpaceException {
        return parkingLots.stream()
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
