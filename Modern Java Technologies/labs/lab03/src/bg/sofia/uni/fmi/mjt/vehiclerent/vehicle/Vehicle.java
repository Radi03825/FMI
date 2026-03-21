package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.driver.Driver;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleAlreadyRentedException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleNotRentedException;

import java.time.LocalDateTime;

public abstract sealed class Vehicle permits Bicycle, MotorVehicle {

    private String id;
    private String model;

    private Driver driver;
    private LocalDateTime startRentTime;

    public Vehicle(String id, String model) {
        this.id = id;
        this.model = model;
    }

    /**
     * Simulates rental of the vehicle. The vehicle now is considered rented by the provided driver and the start of the rental is the provided date.
     * @param driver the driver that wants to rent the vehicle.
     * @param startRentTime the start time of the rent
     * @throws VehicleAlreadyRentedException in case the vehicle is already rented by someone else or by the same driver.
     */
    public void rent(Driver driver, LocalDateTime startRentTime) {
        if (isVehicleRented()) {
            throw new VehicleAlreadyRentedException("Vehicle is already rent");
        }

        this.driver = driver;
        this.startRentTime = startRentTime;
    }

    /**
     * Simulates end of rental for the vehicle - it is no longer rented by a driver.
     * @param rentalEnd time of end of rental
     * @throws IllegalArgumentException in case @rentalEnd is null
     * @throws VehicleNotRentedException in case the vehicle is not rented at all
     * @throws InvalidRentingPeriodException in case the rentalEnd is before the currently noted start date of rental or
     * in case the Vehicle does not allow the passed period for rental, e.g. Caravans must be rented for at least a day
     * and the driver tries to return them after an hour.
     */
    public void returnBack(LocalDateTime rentalEnd) throws InvalidRentingPeriodException {
        if (rentalEnd == null) {
            throw new IllegalArgumentException("Rental end cannot be null");
        } else if (!isVehicleRented()) {
            throw new VehicleNotRentedException("Vehicle is not renting");
        } else if (rentalEnd.isBefore(startRentTime) || !canBeRented(this.startRentTime, rentalEnd)) {
            throw new InvalidRentingPeriodException("Renting period is invalid");
        }

        this.driver = null;
        this.startRentTime = null;
    }

    /**
     * Used to calculate potential rental price without the vehicle to be rented.
     * The calculation is based on the type of the Vehicle (Car/Caravan/Bicycle).
     *
     * @param startOfRent the beginning of the rental
     * @param endOfRent the end of the rental
     * @return potential price for rent
     * @throws InvalidRentingPeriodException in case the vehicle cannot be rented for that period of time or
     * the period is not valid (end date is before start date)
     */
    public abstract double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException;

    public boolean isVehicleRented() {
        return this.startRentTime != null;
    }

    protected abstract boolean canBeRented(LocalDateTime startOfRent, LocalDateTime endOfRent);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LocalDateTime getStartRentTime() {
        return startRentTime;
    }

    public void setStartRentTime(LocalDateTime startRentTime) {
        this.startRentTime = startRentTime;
    }
    
}
