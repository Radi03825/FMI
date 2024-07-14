
interface Toy {
    String play();
}
public class Gift {

    public static void main(String[] args) {
        abstract class Robot {}

        class Transformer extends Robot implements Toy {
            String name = "GiantRobot";

            public String play() {
                return "DinosaurRobot";
            }
        }

        Transformer transformer = new Transformer() {
            public String play() {
                return name;
            }
        };

        System.out.println(transformer.play() + " " + transformer.name);
    }
}
