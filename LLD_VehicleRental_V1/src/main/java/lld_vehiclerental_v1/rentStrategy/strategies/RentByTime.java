package lld_vehiclerental_v1.rentStrategy.strategies;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.rentStrategy.Rent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentByTime implements Rent {
    @Override
    public double getRent(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        // Calculate days and convert to hours
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        
        // For same-day rentals, assume minimum 8 hours
        long hours = days == 0 ? 8 : days * 24;
        
        switch (vehicleType) {
            case TWO_WHEELER -> {
                return hours * 50.0; // Reduced hourly rate
            }
            case SEDAN -> {
                return hours * 125.0; // Reduced hourly rate  
            }
            case TRAVELER -> {
                return hours * 200.0; // Reduced hourly rate
            }
        }
        return 0.0;
    }
}
