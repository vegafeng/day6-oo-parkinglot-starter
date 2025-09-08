package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingLotBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
    public void setParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
