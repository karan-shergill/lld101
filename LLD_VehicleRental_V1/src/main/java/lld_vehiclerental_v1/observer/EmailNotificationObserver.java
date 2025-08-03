package lld_vehiclerental_v1.observer;

import lld_vehiclerental_v1.model.Reservation;
import lld_vehiclerental_v1.constants.ReservationStatus;

public class EmailNotificationObserver implements ReservationObserver {
    @Override
    public void onReservationStatusChanged(Reservation reservation, ReservationStatus oldStatus, ReservationStatus newStatus) {
        System.out.println("Email Notification: Reservation " + reservation.getId() + 
                          " for user " + reservation.getUser().getName() + 
                          " status changed from " + oldStatus + " to " + newStatus);
    }
}