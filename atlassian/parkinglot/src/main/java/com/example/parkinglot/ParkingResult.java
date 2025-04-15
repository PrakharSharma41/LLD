package com.example.parkinglot;

import com.example.parkinglot.entities.ParkingSpot;

public class ParkingResult{
    private int status; private ParkingSpot spot;String ticketId;

    public ParkingResult(int status, ParkingSpot spot, String ticketId){
        this.status=status;this.spot=spot;this.ticketId=ticketId;
    }

    @Override
    public String toString() {
        return "ParkingResult [status=" + status + ", spot=" + spot + ", ticketId=" + ticketId + "]";
    }

    public int getStatus(){return status;}
    public ParkingSpot getParkingSpot(){return spot;}
    public String getTicketId(){return ticketId;}
}