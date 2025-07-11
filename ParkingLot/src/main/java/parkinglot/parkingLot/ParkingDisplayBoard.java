package parkinglot.parkingLot;

import parkinglot.parkingSpotFactory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingDisplayBoard {
    private String id;
    private Map<ParkingSpotType, List<ParkingSpot>> freeParkingSpot;

    public ParkingDisplayBoard(String id) {
        this.id = id;
        this.freeParkingSpot = new HashMap<>();
    }

    public void addFreeParkingSpot(ParkingSpot parkingSpot) {
        freeParkingSpot.computeIfAbsent(parkingSpot.getParkingSpotType(), k -> new ArrayList<>()).add(parkingSpot);
    }

    public void removeFreeParkingSpot(ParkingSpot parkingSpot) {
        List<ParkingSpot> spots = freeParkingSpot.get(parkingSpot.getParkingSpotType());
        if (spots != null) {
            spots.remove(parkingSpot);
        }
    }

    public void showFreeParkingSpots() {
        System.out.println("Free Parking Spots on " + id);
        for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : freeParkingSpot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " spots available");
        }
    }

    public int getFreeSpotsCount(ParkingSpotType spotType) {
        List<ParkingSpot> spots = freeParkingSpot.get(spotType);
        return spots != null ? spots.size() : 0;
    }
}
