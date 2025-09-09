package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
    public void setParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
    public ParkingTicket park(Car car)throws Exception{
        return null;
    }
    public Car fetch(ParkingTicket parkingTicket)throws Exception{
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getCarToParkingTicket().containsKey(parkingTicket)) {
                Map<Car, ParkingTicket> carToParkingTicket = parkingLot.getCarToParkingTicket();
                for (Map.Entry<Car, ParkingTicket> entry : carToParkingTicket.entrySet()) {
                    if (entry.getValue().equals(parkingTicket)) {
                        return entry.getKey();
                    }
                }
            }

        } throw new WrongTicketException();
    }
}
