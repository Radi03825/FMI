package bg.sofia.uni.fmi.mjt.gameplatform.store;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.ItemFilter;

import java.math.BigDecimal;
import java.util.Arrays;

public class GameStore implements StoreAPI {

    private StoreItem[] availableItems;
    private String discount;

    public GameStore(StoreItem[] availableItems) {
        setAvailableItems(availableItems);
    }

    @Override
    public StoreItem[] findItemByFilters(ItemFilter[] itemFilters) {
        if (itemFilters.length == 0) {
            return availableItems;
        }

        StoreItem[] matchItems = new StoreItem[itemFilters.length];

        int index = 0;
        boolean matchAll = true;
        for (StoreItem item : availableItems) {
            for (ItemFilter filter : itemFilters) {
                if (!filter.matches(item)) {
                    matchAll = false;
                    break;
                }
            }

            if (matchAll) {
                matchItems[index++] = item;
            }

            matchAll = true;
        }

        return Arrays.copyOf(matchItems, index);
    }

    @Override
    public void applyDiscount(String promoCode) {
        if (discount == null) {
            this.discount = promoCode;

            double discount = switch (promoCode) {
                case "VAN40" -> 0.4;
                case "100YO" -> 1;
                default -> 0;
            };

            if (discount != 0) {
                for (StoreItem item : availableItems) {
                    item.setPrice(item.getPrice().multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(discount))));
                }
            }
        }
    }

    @Override
    public boolean rateItem(StoreItem item, int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }

        item.rate(rating);

        return true;
    }

    public StoreItem[] getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(StoreItem[] availableItems) {
        this.availableItems = availableItems;
    }
}
