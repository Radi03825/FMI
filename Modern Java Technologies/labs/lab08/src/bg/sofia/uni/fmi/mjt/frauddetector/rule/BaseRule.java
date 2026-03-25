package bg.sofia.uni.fmi.mjt.frauddetector.rule;

public abstract class BaseRule implements Rule {

    private double weight;

    public BaseRule(double weight) {
        setWeight(weight);
    }

    @Override
    public double weight() {
        return this.weight;
    }

    private void setWeight(double weight) {
        if (weight < 0 || weight > 1) {
            throw new IllegalArgumentException("Weight must be between 0 and 1");
        }

        this.weight = weight;
    }
    
}
