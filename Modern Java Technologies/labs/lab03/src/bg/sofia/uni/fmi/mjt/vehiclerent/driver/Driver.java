package bg.sofia.uni.fmi.mjt.vehiclerent.driver;

public class Driver {

    private AgeGroup group;

    public Driver(AgeGroup group) {
        setGroup(group);
    }

    public AgeGroup getGroup() {
        return group;
    }

    public void setGroup(AgeGroup group) {
        this.group = group;
    }
    
}
