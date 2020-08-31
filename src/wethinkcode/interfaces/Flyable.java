package wethinkcode.interfaces;

import wethinkcode.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower WeatherTower);
}
