package bg.sofia.uni.fmi.mjt.gameplatform.store.item;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.Game;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.PriceItemFilter;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.TitleItemFilter;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Test", BigDecimal.valueOf(2.77111), null, null);
        Game game1 = new Game("Test", BigDecimal.valueOf(2.77111), null, null);

        PriceItemFilter priceItemFilter = new PriceItemFilter(BigDecimal.valueOf(1), BigDecimal.valueOf(3));
        TitleItemFilter titleItemFilter = new TitleItemFilter("test", false);

        System.out.println(priceItemFilter.matches(game));
        System.out.println(titleItemFilter.matches(game));
        System.out.println(game.getPrice());

        int[] aa = new int[0];
        System.out.println("aa" + aa.length);

        int[] bb = Arrays.copyOf(aa, 3);


        for (int dfwsd : bb) {
            System.out.println(dfwsd);
        }
    }
}
