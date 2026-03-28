package bg.sofia.uni.fmi.mjt.glovo.delivery.comparator;

import bg.sofia.uni.fmi.mjt.glovo.delivery.DeliveryInfo;

import java.util.Comparator;

public class DeliveryInfoComparatorByTime implements Comparator<DeliveryInfo> {

    // Compare two DeliveryInfo objects by their estimated time, ordering them in ascending order.
    @Override
    public int compare(DeliveryInfo o1, DeliveryInfo o2) {
        int comparedByTime = Integer.compare(o1.estimatedTime(), o2.estimatedTime());

        if (comparedByTime != 0) {
            return comparedByTime;
        }

        return o1.deliveryGuyLocation().compareTo(o2.deliveryGuyLocation());
    }
    
}
