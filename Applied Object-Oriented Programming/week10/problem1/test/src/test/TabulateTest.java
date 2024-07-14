package test;

import functions.Tabulate;
import services.Computable;

public class TabulateTest {
    public static void main(String[] args) {
        Tabulate tab = new Tabulate(null);

        tab.tabulate(1, 101, 25);

        Tabulate.tabulateFunction(1, 10, 5, new Computable() {
            @Override
            public double function(double x) {
                if (x == 0) {
                    return 0;
                }

                return 1 / x;
            }
        });
    }
}
