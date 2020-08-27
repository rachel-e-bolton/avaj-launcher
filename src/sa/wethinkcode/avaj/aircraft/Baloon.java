package sa.wethinkcode.avaj.aircraft;

import sa.wethinkcode.avaj.interfaces.Flyable;
import sa.wethinkcode.avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name,coordinates);
    }

    public void updateConditions() {
        // TO-DO Write Actual Method
        System.out.println("UpdateConditions");
    }

    public void registerTower(WeatherTower _weatherTower) {
        System.out.println("Registering to Weather Tower");
    }
}
