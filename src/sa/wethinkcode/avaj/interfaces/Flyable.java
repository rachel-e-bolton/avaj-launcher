package sa.wethinkcode.avaj.interfaces;

import sa.wethinkcode.avaj.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower WeatherTower);
}
