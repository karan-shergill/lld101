package lld_vehiclerental_v1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private String mobileNo;
    private List<Reservation> reservationList;

    public User(String name, String email, String mobileNo) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.reservationList = new ArrayList<>();
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
