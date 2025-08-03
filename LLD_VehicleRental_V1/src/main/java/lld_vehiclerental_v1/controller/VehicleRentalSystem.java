package lld_vehiclerental_v1.controller;

import lld_vehiclerental_v1.constants.PaymentStatus;
import lld_vehiclerental_v1.model.RentalStore;
import lld_vehiclerental_v1.model.Reservation;
import lld_vehiclerental_v1.model.User;
import lld_vehiclerental_v1.paymentStrategy.Payment;
import lld_vehiclerental_v1.paymentStrategy.PaymentProcessor;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;
import lld_vehiclerental_v1.vehicleFactory.VehicleFactory;
import lld_vehiclerental_v1.rentStrategy.Rent;
import lld_vehiclerental_v1.rentStrategy.strategies.RentByDays;

import java.time.LocalDate;
import java.util.*;

public class VehicleRentalSystem {
    private static VehicleRentalSystem instance;
    private List<RentalStore> rentalStoreList;
    private VehicleFactory vehicleFactory;
    private ReservationController reservationController;
    private PaymentProcessor paymentProcessor;
    private Map<String, User> users;

    private VehicleRentalSystem() {
        this.rentalStoreList = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationController = new ReservationController();
        this.paymentProcessor = new PaymentProcessor();
        this.users = new HashMap<>();
    }

    public static synchronized VehicleRentalSystem getInstance() {
        if (instance == null) {
            instance = new VehicleRentalSystem();
        }
        return instance;
    }


    public void addStore(RentalStore store) {
        rentalStoreList.add(store);
    }


    public RentalStore getStore(String storeId) {
        if (storeId == null) {
            return null;
        }
        for (RentalStore store : rentalStoreList) {
            if (store.getStoreId().equals(storeId)) {
                return store;
            }
        }
        return null;
    }


    public List<RentalStore> getStores() {
        return rentalStoreList;
    }


    public User getUser(String userId) {
        if (userId == null) {
            return null;
        }
        return users.get(userId);
    }


    public Reservation createReservation(String userId, String vehicleRegistration, PaymentStatus paymentStatus,
                                         String pickupStoreId, String returnStoreId, LocalDate startDate, LocalDate endDate, Rent rentStrategy) {
        if (userId == null || vehicleRegistration == null || paymentStatus == null || 
            pickupStoreId == null || returnStoreId == null || startDate == null || endDate == null || rentStrategy == null) {
            return null;
        }
        
        User user = getUser(userId);
        RentalStore pickupStore = getStore(pickupStoreId);
        RentalStore returnStore = getStore(returnStoreId);
        
        if (user == null || pickupStore == null || returnStore == null) {
            return null;
        }
        
        Vehicle vehicle = pickupStore.getVehicle(vehicleRegistration);
        if (vehicle == null) {
            return null;
        }
        
        return reservationController.createReservation(
                user, vehicle, paymentStatus, startDate, endDate, pickupStore, returnStore, rentStrategy);
    }

    // Overloaded method with default rent strategy for backward compatibility
    public Reservation createReservation(String userId, String vehicleRegistration, PaymentStatus paymentStatus,
                                         String pickupStoreId, String returnStoreId, LocalDate startDate, LocalDate endDate) {
        return createReservation(userId, vehicleRegistration, paymentStatus, pickupStoreId, returnStoreId, startDate, endDate, new RentByDays());
    }

    public boolean processPayment(String reservationId, Payment paymentStrategy) {
        if (reservationId == null || paymentStrategy == null) {
            return false;
        }
        
        Reservation reservation = reservationController.getReservation(reservationId);
        if (reservation == null) {
            return false;
        }
        
        boolean result = paymentProcessor.processPayment(reservation.getRent(), paymentStrategy);
        if (result) {
            reservationController.confirmReservation(reservationId);
            return true;
        }
        return false;
    }

    public void startRental(String reservationId) {
        reservationController.startRental(reservationId);
    }

    public void completeRental(String reservationId) {
        reservationController.completeRental(reservationId);
    }

    public void cancelReservation(String reservationId) {
        reservationController.cancelReservation(reservationId);
    }

    public void registerUser(User user){
        String userID = user.getId();
        if(users.containsKey(userID)){
            System.out.println("User with id : " + userID + "Already exists in the system");
            return;
        }
        users.put(userID , user);
    }
}
