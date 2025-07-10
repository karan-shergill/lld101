package parkinglot_V1.accountFactory;

import parkinglot_V1.parkingLot.*;
import parkinglot_V1.parkingSpotFactory.ParkingSpot;

public class ParkingAdmin extends Account {
    ParkingLot parkingLot;

    public ParkingAdmin(Person person, String userName, String password, AccountStatus accountStatus, ParkingLot parkingLot) {
        super(person, userName, password, accountStatus);
        this.parkingLot = parkingLot;
    }

    public ParkingAdmin(Person person, String userName, String password, AccountStatus accountStatus) {
        super(person, userName, password, accountStatus);
    }

    public boolean addParkingFloor(ParkingFloor parkingFloor) {
        return false;
    }

    public boolean addParkingSpot(ParkingSpot parkingSpot, String floorNo) {
        return false;
    }

    public void addParkingDisplayBoard(ParkingDisplayBoard parkingDisplayBoard, String floor) {

    }

    public void addEntrancePanel(Entrance entrance) {
        this.parkingLot.addEntrance(entrance);
    }

    public void addExitPanel(Exit exit) {
        this.parkingLot.addExit(exit);
    }

    @Override
    public boolean resetPassword() {
        return false;
    }
}
