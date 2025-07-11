package parkinglot.parkingTicket;

import parkinglot.parkingLot.Entrance;
import parkinglot.parkingLot.Exit;
import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.paymentStrategy.Payment;
import parkinglot.vehicleFactory.Vehicle;

import java.util.Date;

public class ParkingTicket {
    private String number;
    private Date entryTimeStamp;
    private Date exitTimeStamp;
    private double amount;
    private boolean payed;
    private Vehicle vehicle;
    private Entrance entrance;
    private Exit exit;
    private Payment payment;
    private ParkingSpot parkingSpot;

    // Constructor for creating a parking ticket when vehicle enters
    public ParkingTicket(String number, Vehicle vehicle, Entrance entrance, ParkingSpot parkingSpot) {
        this.number = number;
        this.vehicle = vehicle;
        this.entrance = entrance;
        this.parkingSpot = parkingSpot;
        this.entryTimeStamp = new Date(); // Set current time as entry time
        this.exitTimeStamp = null; // Will be set when vehicle exits
        this.amount = 0.0; // Will be calculated when vehicle exits
        this.payed = false; // Initially not paid
        this.exit = null; // Will be set when vehicle exits
        this.payment = null; // Will be set when payment is made
    }

    // Getters and setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public void setEntryTimeStamp(Date entryTimeStamp) {
        this.entryTimeStamp = entryTimeStamp;
    }

    public Date getExitTimeStamp() {
        return exitTimeStamp;
    }

    public void setExitTimeStamp(Date exitTimeStamp) {
        this.exitTimeStamp = exitTimeStamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public void setEntrance(Entrance entrance) {
        this.entrance = entrance;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
