package lld_vehiclerental_v1.controller;

import lld_vehiclerental_v1.constants.PaymentStatus;
import lld_vehiclerental_v1.model.RentalStore;
import lld_vehiclerental_v1.model.Reservation;
import lld_vehiclerental_v1.model.User;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;
import lld_vehiclerental_v1.rentStrategy.Rent;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReservationController {
    private Map<String, Reservation> allReservations;

    public ReservationController() {
        this.allReservations = new HashMap<>();
    }

    public Reservation createReservation(User user, Vehicle vehicle, PaymentStatus paymentStatus,
                                         LocalDate startDate, LocalDate endDate, RentalStore pickUpStore,
                                         RentalStore returnStore, Rent rentStrategy) {
        Reservation reservation = new Reservation(user, vehicle, paymentStatus, startDate, endDate, pickUpStore, returnStore, rentStrategy);
        allReservations.put(reservation.getId(), reservation);
        user.addReservation(reservation);
        return reservation;
    }

    public void confirmReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation != null) {
            reservation.confirmReservation();
        }
    }

    public void startRental(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation != null) {
            reservation.startRental();
        }
    }

    public void completeRental(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation != null) {
            reservation.completeRental();
        }
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }

    public Reservation getReservation(String reservationId) {
        if (reservationId == null) {
            return null;
        }
        return allReservations.get(reservationId);
    }
}
