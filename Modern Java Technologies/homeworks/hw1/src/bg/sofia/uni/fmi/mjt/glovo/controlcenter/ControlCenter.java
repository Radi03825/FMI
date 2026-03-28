package bg.sofia.uni.fmi.mjt.glovo.controlcenter;

import bg.sofia.uni.fmi.mjt.glovo.controlcenter.map.Location;
import bg.sofia.uni.fmi.mjt.glovo.controlcenter.map.MapEntity;
import bg.sofia.uni.fmi.mjt.glovo.controlcenter.map.MapEntityType;
import bg.sofia.uni.fmi.mjt.glovo.delivery.DeliveryInfo;
import bg.sofia.uni.fmi.mjt.glovo.delivery.DeliveryType;
import bg.sofia.uni.fmi.mjt.glovo.delivery.ShippingMethod;
import bg.sofia.uni.fmi.mjt.glovo.delivery.comparator.DeliveryInfoComparatorByPrice;
import bg.sofia.uni.fmi.mjt.glovo.delivery.comparator.DeliveryInfoComparatorByTime;
import bg.sofia.uni.fmi.mjt.glovo.exception.InvalidInputException;
import bg.sofia.uni.fmi.mjt.glovo.exception.InvalidMapEntityTypeException;
import bg.sofia.uni.fmi.mjt.glovo.exception.InvalidOrderException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ControlCenter implements ControlCenterApi {

    private MapEntity[][] layout;
    private Map<Location, DeliveryType> deliveryGuys;

    public ControlCenter(char[][] mapLayout) {
        this.deliveryGuys = new HashMap<>();
        setMapLayout(mapLayout);
    }

    @Override
    public DeliveryInfo findOptimalDeliveryGuy(Location restaurantLocation, Location clientLocation, double maxPrice,
                                               int maxTime, ShippingMethod shippingMethod) {
        if (shippingMethod == null) {
            throw new IllegalArgumentException("Shipping method cannot be null");
        } else if (restaurantLocation == null || clientLocation == null) {
            throw new IllegalArgumentException("Locations cannot be null");
        } else if (maxPrice < -1 || maxTime < -1 || maxPrice == 0 || maxTime == 0) {
            throw new InvalidInputException("Invalid max price or max time");
        }
        checkLocations(restaurantLocation, clientLocation);

        TreeSet<DeliveryInfo> deliveryInfoTreeSet;
        if (shippingMethod.equals(ShippingMethod.CHEAPEST)) {
            deliveryInfoTreeSet = new TreeSet<>(new DeliveryInfoComparatorByPrice());
        } else {
            deliveryInfoTreeSet = new TreeSet<>(new DeliveryInfoComparatorByTime());
        }

        int distanceFromRestaurantToClient = findShortestPath(restaurantLocation, clientLocation);
        if (distanceFromRestaurantToClient == -1) {
            return null;
        }

        addDeliveryInfos(restaurantLocation, maxPrice, maxTime, shippingMethod,
            deliveryInfoTreeSet, distanceFromRestaurantToClient);
        if (deliveryInfoTreeSet.isEmpty()) {
            return null;
        }

        return deliveryInfoTreeSet.first();
    }

    @Override
    public MapEntity[][] getLayout() {
        return this.layout;
    }

    private void addDeliveryInfos(Location restaurantLocation, double maxPrice, int maxTime,
                                  ShippingMethod shippingMethod, TreeSet<DeliveryInfo> deliveryInfoTreeSet,
                                  int distanceFromRestaurantToClient) {
        for (Map.Entry<Location, DeliveryType> currentDeliveryGuy : deliveryGuys.entrySet()) {
            Location currentDeliveryGuyLocation = currentDeliveryGuy.getKey();
            int kilometers = findShortestPath(currentDeliveryGuyLocation, restaurantLocation);
            DeliveryType deliveryType = currentDeliveryGuy.getValue();

            int pricePerKilometer = deliveryType.getPricePerKilometer();
            int timePerKilometer = deliveryType.getTimePerKilometer();

            kilometers += distanceFromRestaurantToClient;

            DeliveryInfo currentDeliveryInfo =
                new DeliveryInfo(currentDeliveryGuyLocation, pricePerKilometer * kilometers,
                    timePerKilometer * kilometers, deliveryType);

            if (maxPrice != -1 && currentDeliveryInfo.price() <= maxPrice) {
                deliveryInfoTreeSet.add(currentDeliveryInfo);
            } else if (maxTime != -1 && currentDeliveryInfo.estimatedTime() <= maxTime) {
                deliveryInfoTreeSet.add(currentDeliveryInfo);
            } else if (maxPrice == -1 && maxTime == -1) {
                deliveryInfoTreeSet.add(currentDeliveryInfo);
            }
        }
    }

    private int findShortestPath(Location currentDeliveryGuyLocation, Location restaurantLocation) {
        int rows = layout.length;
        int cols = layout[0].length;
        int[][] possibleMoves = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        Deque<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.add(currentDeliveryGuyLocation);
        visited[currentDeliveryGuyLocation.x()][currentDeliveryGuyLocation.y()] = true;

        return performBfsTraversal(queue, visited, possibleMoves, restaurantLocation);
    }

    private int performBfsTraversal(Deque<Location> queue, boolean[][] visited,
                                    int[][] possibleMoves, Location targetLocation) {
        int movesCount = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Location currentLocation = queue.poll();
                if (currentLocation.equals(targetLocation)) {
                    return movesCount;
                }

                for (int[] possibleMove : possibleMoves) {
                    int newRow = currentLocation.x() + possibleMove[0];
                    int newCol = currentLocation.y() + possibleMove[1];
                    if (canMove(newRow, visited.length, newCol, visited[0].length, visited)) {
                        queue.add(new Location(newRow, newCol));
                        visited[newRow][newCol] = true;
                    }
                }
            }

            movesCount++;
        }

        return -1;
    }

    private boolean canMove(int newRow, int rows, int newCol, int cols, boolean[][] visited) {
        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
            && !visited[newRow][newCol] && !this.layout[newRow][newCol].type().equals(MapEntityType.WALL);
    }

    private void setMapLayout(char[][] mapLayout) {
        this.layout = new MapEntity[mapLayout.length][mapLayout[0].length];
        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[0].length; j++) {
                Location currentLocation = new Location(i, j);
                MapEntityType currentType = switch (mapLayout[i][j]) {
                    case '.' -> MapEntityType.ROAD;
                    case '#' -> MapEntityType.WALL;
                    case 'R' -> MapEntityType.RESTAURANT;
                    case 'C' -> MapEntityType.CLIENT;
                    case 'A' -> {
                        this.deliveryGuys.put(currentLocation, DeliveryType.CAR);
                        yield MapEntityType.DELIVERY_GUY_CAR;
                    }
                    case 'B' -> {
                        this.deliveryGuys.put(currentLocation, DeliveryType.BIKE);
                        yield MapEntityType.DELIVERY_GUY_BIKE;
                    }
                    default -> throw new InvalidMapEntityTypeException("Invalid map entity type");
                };

                this.layout[i][j] = new MapEntity(currentLocation, currentType);
            }
        }
    }

    private void checkLocations(Location restaurantLocation, Location clientLocation) {
        if (restaurantLocation.equals(clientLocation)) {
            throw new InvalidOrderException("Restaurant and client locations cannot be the same");
        } else if (isOutOfBounds(restaurantLocation) || isOutOfBounds(clientLocation)) {
            throw new InvalidOrderException("Restaurant or client location is out of bounds");
        } else if (isNotTheSameMapEntityType(restaurantLocation, MapEntityType.RESTAURANT) ||
            isNotTheSameMapEntityType(clientLocation, MapEntityType.CLIENT)) {
            throw new InvalidOrderException("Restaurant or client location is a wall");
        }
    }

    private boolean isNotTheSameMapEntityType(Location location, MapEntityType mapEntityType) {
        return !layout[location.x()][location.y()].type().equals(mapEntityType);
    }

    private boolean isOutOfBounds(Location location) {
        return location.x() < 0 || location.x() >= layout.length ||
            location.y() < 0 || location.y() >= layout[0].length;
    }

}