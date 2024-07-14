package functions;

import services.Computable;

import java.util.Scanner;

public class Tabulate {
    private Computable callback;

    public Tabulate(Computable callback) {
        setCallback(callback);
    }

    public void tabulate(double a, double b, int steps) {
        tabulateFunction(a, b, steps, callback);
    }

    public static void tabulateFunction(double a, double b, int steps, Computable computable) {
        if (computable == null) {
            System.out.println("Invalid function!");
            return;
        }

        if (a >= b) {
            System.out.println("Invalid interval!");
            return;
        }

        if (steps <= 0) {
            System.out.println("Invalid step!");
            return;
        }

        int size = (int) (b - a) / steps;

        Scanner input = new Scanner(System.in);

        System.out.printf("%-10s%-10s\n", "X", "F(X)");
        double x;
        for (int i = 0; i <= steps; i++) {
            x = a + size * i;
            System.out.printf("%-10.2f%-10.2f\n", x, computable.function(x));

            if (i % 20 == 19) {
                System.out.println("Press Enter to continue...");
                input.nextLine();
            }
        }
    }

    public Computable getCallback() {
        return callback;
    }

    public void setCallback(Computable callback) {
        if (callback != null) {
            this.callback = callback;
        } else {
            this.callback = new Computable() {
                @Override
                public double function(double x) {
                    return x * x;
                }
            };
        }
    }
}
