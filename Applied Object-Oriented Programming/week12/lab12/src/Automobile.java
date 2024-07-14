public class Automobile {

    private final String drive() {
        return "Driving vehicle";
    }
}
class Auto extends Automobile {
    protected String drive() {
        return "Driving car";
    }
}
class ElectricCar extends Auto {
    public final String drive() {
        return "Driving electric car";
    }
    public static void main(String[] wheels) {
        final Automobile car = new ElectricCar();
        var v = (Auto) car;
        System.out.print(v.drive());
    }
}

