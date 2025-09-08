package com.afs.parkinglot;

public class ParkingTicket {
    private int used_count;
    final static int MAX_USED_COUNT = 1;

    public int getUsed_count() {
        return used_count;
    }
    public void setUsed_count(int used_count) {
        this.used_count = used_count;
    }
    public ParkingTicket() {
        this.used_count = 0;
    }


}
