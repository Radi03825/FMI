package phoneCoder;

public class PhoneCoder {
    private int key;

    public PhoneCoder() {
    }

    public PhoneCoder(int key) {
        setKey(key);
    }

    public String encode(String phoneNumber) {
        int firstDigit = Integer.parseInt(String.valueOf(phoneNumber.charAt(0)));
        int secondDigit = Integer.parseInt(String.valueOf(phoneNumber.charAt(1)));
        int thirdDigit = Integer.parseInt(String.valueOf(phoneNumber.charAt(2)));
        int fourthDigit = Integer.parseInt(String.valueOf(phoneNumber.charAt(3)));

        firstDigit = encodeFunction(firstDigit);
        secondDigit = encodeFunction(secondDigit);
        thirdDigit = encodeFunction(thirdDigit);
        fourthDigit = encodeFunction(fourthDigit);

        return String.format("%d%d%d%d", thirdDigit, fourthDigit, firstDigit, secondDigit);
    }

    public String decode(String codedPhoneNumber) {
        int firstDigit = Integer.parseInt(String.valueOf(codedPhoneNumber.charAt(0)));
        int secondDigit = Integer.parseInt(String.valueOf(codedPhoneNumber.charAt(1)));
        int thirdDigit = Integer.parseInt(String.valueOf(codedPhoneNumber.charAt(2)));
        int fourthDigit = Integer.parseInt(String.valueOf(codedPhoneNumber.charAt(3)));

        firstDigit = decodeFunction(firstDigit);
        secondDigit = decodeFunction(secondDigit);
        thirdDigit = decodeFunction(thirdDigit);
        fourthDigit = decodeFunction(fourthDigit);

        return String.format("%d%d%d%d", thirdDigit, fourthDigit, firstDigit, secondDigit);
    }

    private int encodeFunction(int number) {
        return (number + this.key) % 10;
    }

    private int decodeFunction(int number) {
        if (number < this.key) {
            number += 10;
        }

        return (number - this.key);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        if (key < 1) {
            this.key = 1;
        } else if (key > 9) {
            this.key = 9;
        } else {
            this.key = key;
        }
    }

    @Override
    public String toString() {
        return String.format("PhoneCoder : %d", this.key);
    }
}
