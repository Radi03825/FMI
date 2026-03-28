package bg.sofia.uni.fmi.mjt.glovo;

import bg.sofia.uni.fmi.mjt.glovo.controlcenter.ControlCenter;
import bg.sofia.uni.fmi.mjt.glovo.controlcenter.ControlCenterApi;
import bg.sofia.uni.fmi.mjt.glovo.controlcenter.map.MapEntity;
import bg.sofia.uni.fmi.mjt.glovo.delivery.Delivery;
import bg.sofia.uni.fmi.mjt.glovo.delivery.DeliveryInfo;
import bg.sofia.uni.fmi.mjt.glovo.delivery.ShippingMethod;
import bg.sofia.uni.fmi.mjt.glovo.exception.InvalidInputException;
import bg.sofia.uni.fmi.mjt.glovo.exception.NoAvailableDeliveryGuyException;

public class Glovo implements GlovoApi {

    private ControlCenterApi controlCenterApi;

    public Glovo(char[][] mapLayout) {
        this.controlCenterApi = new ControlCenter(mapLayout);
    }

    @Override
    public Delivery getCheapestDelivery(MapEntity client, MapEntity restaurant, String foodItem)
        throws NoAvailableDeliveryGuyException {
        return getDelivery(client, restaurant, foodItem, -1, -1, ShippingMethod.CHEAPEST);
    }

    @Override
    public Delivery getFastestDelivery(MapEntity client, MapEntity restaurant, String foodItem)
        throws NoAvailableDeliveryGuyException {
        return getDelivery(client, restaurant, foodItem, -1, -1, ShippingMethod.FASTEST);
    }

    @Override
    public Delivery getFastestDeliveryUnderPrice(MapEntity client, MapEntity restaurant, String foodItem,
                                                 double maxPrice) throws NoAvailableDeliveryGuyException {
        return getDelivery(client, restaurant, foodItem, maxPrice, -1, ShippingMethod.FASTEST);
    }

    @Override
    public Delivery getCheapestDeliveryWithinTimeLimit(MapEntity client, MapEntity restaurant, String foodItem,
                                                       int maxTime) throws NoAvailableDeliveryGuyException {
        return getDelivery(client, restaurant, foodItem, -1, maxTime, ShippingMethod.CHEAPEST);
    }

    private Delivery getDelivery(MapEntity client, MapEntity restaurant, String foodItem, double maxPrice, int maxTime,
                                 ShippingMethod shippingMethod) throws NoAvailableDeliveryGuyException {
        if (client == null || restaurant == null || foodItem == null || shippingMethod == null) {
            throw new IllegalArgumentException("Client, restaurant, food item and shipping method cannot be null");
        } else if (maxPrice < -1 || maxTime < -1 || maxPrice == 0 || maxTime == 0) {
            throw new InvalidInputException("Invalid price or time limit");
        }

        DeliveryInfo deliveryInfo = controlCenterApi.findOptimalDeliveryGuy(restaurant.location(),
            client.location(), maxPrice, maxTime, shippingMethod);

        if (deliveryInfo == null) {
            throw new NoAvailableDeliveryGuyException("No available delivery guy");
        }

        return new Delivery(client.location(), restaurant.location(), deliveryInfo.deliveryGuyLocation(),
            foodItem, deliveryInfo.price(), deliveryInfo.estimatedTime());
    }

}
