package lld_vehiclerental_v1.rentStrategy;

import lld_vehiclerental_v1.constants.VehicleType;

import java.time.LocalDate;

public interface Rent {
    public double getRent(VehicleType vehicleType, LocalDate startDate, LocalDate endDate);
}
