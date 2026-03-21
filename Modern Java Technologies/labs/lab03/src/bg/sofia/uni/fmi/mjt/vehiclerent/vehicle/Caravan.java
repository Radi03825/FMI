package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public final class Caravan extends MotorVehicle {

    private static final int PRICE_FOR_BED = 10;
    private static final Duration MIN_RENT_DURATION = Duration.ofDays(1);

    private int numberOfBeds;

    public Caravan(String id, String model, FuelType fuelType, int numberOfSeats, int numberOfBeds, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model, fuelType, numberOfSeats, pricePerWeek, pricePerDay, pricePerHour);
        setNumberOfBeds(numberOfBeds);
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        double rentalPrice = super.calculateRentalPrice(startOfRent, endOfRent);

        return rentalPrice + numberOfBeds * PRICE_FOR_BED;
    }

    @Override
    protected boolean canBeRented(LocalDateTime startOfRent, LocalDateTime endOfRent) {
        return startOfRent.isBefore(endOfRent) && Duration.between(startOfRent, endOfRent).compareTo(MIN_RENT_DURATION) >= 0;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

}
