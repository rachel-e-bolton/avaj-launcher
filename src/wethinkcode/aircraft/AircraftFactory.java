package wethinkcode.aircraft;

import wethinkcode.interfaces.Flyable;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Exception {

        if (longitude <= 0 || latitude <= 0 || height < 0) {
            throw new Exception("Coordinates must be positive integers.");
        } else {
            Coordinates coordinates = new Coordinates(longitude, latitude, height);

            switch (type) {
                case "Baloon":
                    return (new Baloon(name, coordinates));
                case "Helicopter":
                    return (new Helicopter(name, coordinates));
                case "JetPlane":
                    return (new JetPlane(name, coordinates));
                default:
                    throw new Exception("Could not create aircraft.");
            }
        }
    }
}

