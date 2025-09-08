package com.afs.parkinglot;

import java.util.Objects;

public class Ticket {
    private int used_count;
    final static int MAX_USED_COUNT = 1;

    public int getUsed_count() {
        return used_count;
    }
    public Ticket() {
        this.used_count = 0;
    }


}
