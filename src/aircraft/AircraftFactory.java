package aircraft;

import java.util.Map;
import java.util.TreeMap;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        Flyable flyable = switch (type.toLowerCase()) {
            case "baloon" -> new Baloon(name, coordinates);
            case "jetplane" -> new JetPlane(name, coordinates);
            case "helicopter" -> new Helicopter(name, coordinates);
            default -> throw new IllegalArgumentException();
        };
        return flyable;
    }
}