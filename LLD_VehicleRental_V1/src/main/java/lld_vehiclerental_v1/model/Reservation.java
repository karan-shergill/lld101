package lld_vehiclerental_v1.model;

import lld_vehiclerental_v1.constants.PaymentStatus;
import lld_vehiclerental_v1.constants.ReservationStatus;
import lld_vehiclerental_v1.constants.VehicleStatus;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;
import lld_vehiclerental_v1.rentStrategy.Rent;
import lld_vehiclerental_v1.observer.ReservationObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reservation {
    private String id;
    private User user; // reservation for this user
    private Vehicle vehicle; // vehicle reserved
    private PaymentStatus paymentStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus reservationStatus;
    private RentalStore pickUpStore;
    private RentalStore returnStore;
    private double rent;
    private List<ReservationObserver> observers;

    public Reservation(User user, Vehicle vehicle, PaymentStatus paymentStatus, LocalDate startDate, LocalDate endDate, RentalStore pickUpStore, RentalStore returnStore, Rent rentStrategy) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.vehicle = vehicle;
        this.paymentStatus = paymentStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationStatus = ReservationStatus.PENDING;
        this.pickUpStore = pickUpStore;
        this.returnStore = returnStore;
        this.observers = new ArrayList<>();
        // Calculate rent using strategy pattern
        this.rent = rentStrategy.getRent(vehicle.getVehicleType(), startDate, endDate);
    }

    public void addObserver(ReservationObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(ReservationObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(ReservationStatus oldStatus, ReservationStatus newStatus) {
        for (ReservationObserver observer : observers) {
            observer.onReservationStatusChanged(this, oldStatus, newStatus);
        }
    }

    public void confirmReservation() {
        if (reservationStatus == ReservationStatus.PENDING) {
            ReservationStatus oldStatus = reservationStatus;
            reservationStatus = ReservationStatus.CONFIRMED;
            vehicle.setVehicleStatus(VehicleStatus.RESERVED);
            notifyObservers(oldStatus, reservationStatus);
        }
    }

    public void startRental() {
        if (reservationStatus == ReservationStatus.CONFIRMED) {
            ReservationStatus oldStatus = reservationStatus;
            reservationStatus = ReservationStatus.IN_PROGRESS;
            vehicle.setVehicleStatus(VehicleStatus.RENTED);
            notifyObservers(oldStatus, reservationStatus);
        }
    }

    public void completeRental() {
        if (reservationStatus == ReservationStatus.IN_PROGRESS) {
            ReservationStatus oldStatus = reservationStatus;
            reservationStatus = ReservationStatus.COMPLETED;
            vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
            notifyObservers(oldStatus, reservationStatus);
        }
    }

    public void cancelReservation() {
        if (reservationStatus == ReservationStatus.PENDING
                || reservationStatus == ReservationStatus.CONFIRMED) {
            ReservationStatus oldStatus = reservationStatus;
            reservationStatus = ReservationStatus.CANCELED;
            vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
            notifyObservers(oldStatus, reservationStatus);
        }
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public RentalStore getPickUpStore() {
        return pickUpStore;
    }

    public RentalStore getReturnStore() {
        return returnStore;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }
}
