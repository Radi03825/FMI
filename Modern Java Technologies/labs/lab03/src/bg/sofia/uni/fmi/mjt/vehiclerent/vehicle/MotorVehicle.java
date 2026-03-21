package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract sealed class MotorVehicle extends Vehicle permits Car, Caravan {

    private static final int PRICE_FOR_SEAT = 5;

    private FuelType fuelType;
    private int numberOfSeats;
    private double pricePerWeek;
    private double pricePerDay;
    private double pricePerHour;

    public MotorVehicle(String id, String model, FuelType fuelType, int numberOfSeats, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model);
        setFuelType(fuelType);
        setNumberOfSeats(numberOfSeats);
        setPricePerWeek(pricePerWeek);
        setPricePerDay(pricePerDay);
        setPricePerHour(pricePerHour);
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        if (!canBeRented(startOfRent, endOfRent)) {
            throw new InvalidRentingPeriodException("Vehicle can not be rented for this period");
        }

        Duration duration = Duration.between(startOfRent, endOfRent);

        long totalDays = duration.toDays();
        long weeks = totalDays / 7;
        long days = totalDays % 7;

        duration = duration.minus(Duration.ofDays(totalDays));
        long hours = duration.toHours();

        duration = duration.minus(Duration.ofHours(hours));
        long minutes = duration.toMinutes();
        if (minutes > 0) {
            hours++;
        }

        long priceForEngine = totalDays * getFuelType().getDailyFee();
        int priceForSeats = getNumberOfSeats() * MotorVehicle.PRICE_FOR_SEAT;

        double totalPricePerWeeks = getPricePerWeek() * weeks;
        double totalPricePerDays = getPricePerDay() * days;
        double totalPricePerHours = getPricePerHour() * hours;

        return priceForEngine + priceForSeats + totalPricePerWeeks + totalPricePerDays + totalPricePerHours;
    }

    public double getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(double pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    
}
