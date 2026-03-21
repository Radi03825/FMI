package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import java.time.LocalDateTime;

public final class Car extends MotorVehicle {

    public Car(String id, String model, FuelType fuelType, int numberOfSeats, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model, fuelType, numberOfSeats, pricePerWeek, pricePerDay, pricePerHour);
    }

    @Override
    protected boolean canBeRented(LocalDateTime startOfRent, LocalDateTime endOfRent) {
        return startOfRent.isBefore(endOfRent);
    }
    
}
