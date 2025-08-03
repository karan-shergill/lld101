package lld_vehiclerental_v1.observer;

import lld_vehiclerental_v1.model.Reservation;
import lld_vehiclerental_v1.constants.ReservationStatus;

public class SMSNotificationObserver implements ReservationObserver {
    @Override
    public void onReservationStatusChanged(Reservation reservation, ReservationStatus oldStatus, ReservationStatus newStatus) {
        System.out.println("SMS Notification: Reservation " + reservation.getId() + 
                          " status changed to " + newStatus + 
                          " for mobile " + reservation.getUser().getMobileNo());
    }
}