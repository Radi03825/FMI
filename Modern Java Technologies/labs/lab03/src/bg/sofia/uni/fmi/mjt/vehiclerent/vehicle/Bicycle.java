package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public final class Bicycle extends Vehicle {

    private static final Duration MAX_RENT_DURATION = Duration.ofDays(6).plusHours(23).plusMinutes(59).plusSeconds(59);

    private double pricePerDay;
    private double pricePerHour;

    public Bicycle(String id, String model, double pricePerDay, double pricePerHour){
        super(id, model);
        setPricePerDay(pricePerDay);
        setPricePerHour(pricePerHour);
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        if (!canBeRented(startOfRent, endOfRent)) {
            throw new InvalidRentingPeriodException("Bicycle can not be rented for this period");
        }

        Duration duration = Duration.between(startOfRent, endOfRent);

        long totalDays = duration.toDays();

        duration = duration.minus(Duration.ofDays(totalDays));
        long hours = duration.toHours();

        duration = duration.minus(Duration.ofHours(hours));
        long minutes = duration.toMinutes();
        if (minutes > 0) {
            hours++;
        }

        double totalPricePerDays = getPricePerDay() * totalDays;
        double totalPricePerHours = getPricePerHour() * hours;

        return totalPricePerDays + totalPricePerHours;
    }

    @Override
    protected boolean canBeRented(LocalDateTime startOfRent, LocalDateTime endOfRent) {
        return startOfRent.isBefore(endOfRent) && Duration.between(startOfRent, endOfRent).compareTo(MAX_RENT_DURATION) <= 0;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    
}
