package bg.sofia.uni.fmi.mjt.glovo.delivery.comparator;

import bg.sofia.uni.fmi.mjt.glovo.delivery.DeliveryInfo;

import java.util.Comparator;

public class DeliveryInfoComparatorByPrice implements Comparator<DeliveryInfo> {

    // Compare two DeliveryInfo objects by price, ordering them in ascending order.
    @Override
    public int compare(DeliveryInfo o1, DeliveryInfo o2) {
        int comparedByPrice = Double.compare(o1.price(), o2.price());

        if (comparedByPrice != 0) {
            return comparedByPrice;
        }

        return o1.deliveryGuyLocation().compareTo(o2.deliveryGuyLocation());
    }
    
}
