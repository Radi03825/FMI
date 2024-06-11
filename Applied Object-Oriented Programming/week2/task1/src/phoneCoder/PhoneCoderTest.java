package phoneCoder;

public class PhoneCoderTest {
    public static void main(String[] args) {

        PhoneCoder phoneCoder = new PhoneCoder(7);

        System.out.printf("PhoneCoder key: %d%n", phoneCoder.getKey());
        System.out.printf("Encode: %s%n", phoneCoder.encode("1234"));
        System.out.printf("Decode: %s%n", phoneCoder.decode("0189"));
        System.out.println(phoneCoder.toString());
    }
}
