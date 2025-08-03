package lld_vehiclerental_v1.observer;

import lld_vehiclerental_v1.model.Reservation;
import lld_vehiclerental_v1.constants.ReservationStatus;

public interface ReservationObserver {
    void onReservationStatusChanged(Reservation reservation, ReservationStatus oldStatus, ReservationStatus newStatus);
}