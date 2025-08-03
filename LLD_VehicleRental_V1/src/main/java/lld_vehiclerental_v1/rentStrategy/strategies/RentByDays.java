package lld_vehiclerental_v1.rentStrategy.strategies;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.rentStrategy.Rent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentByDays implements Rent {
    @Override
    public double getRent(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        long noOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        switch (vehicleType) {
            case TWO_WHEELER -> {
                return noOfDays * 1000.0;
            }
            case SEDAN -> {
                return noOfDays * 3000.0;
            }
            case TRAVELER -> {
                return noOfDays * 5000.0;
            }
        }
        return 0.0;
    }
}
