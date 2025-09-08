package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SuperParkingLotBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
    public void setParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingTicket park(Car car) throws UnavailableParkingSpaceException {
        int restLocation = parkingLots.getFirst().getREST_LOCATION();
        return parkingLots.stream()
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
