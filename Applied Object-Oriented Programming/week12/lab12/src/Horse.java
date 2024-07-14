abstract class Animal1 {
    public final void eat() { // Line 1
        System.out.println("Eat...");
    }
}
public class Horse extends Animal1 { // Line 2
//    public void eat() { // Line 3
//        System.out.println("Eat Grass");
//    }
    public static void main(String[] args) {
        Animal1 animal = new Horse();
        animal.eat();
    }
} 