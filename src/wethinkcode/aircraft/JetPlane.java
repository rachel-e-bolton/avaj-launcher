package wethinkcode.aircraft;

import wethinkcode.interfaces.Flyable;
import wethinkcode.simulator.Simulator;
import wethinkcode.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = _weatherTower.getWeather(this.coordinates);

        String msg = String.format("JetPlane#%s(%d): ", this.name, this.id);
        int newLng = this.coordinates.getLongitude();
        int newLat = this.coordinates.getLatitude();
        int newHgt = this.coordinates.getHeight();

        if (weather.equals("SUN")) {
            newLat += 10;
            newHgt += 2;
            msg += "Well folks, looks like clear skies and plane sailing...";
            Simulator.writer.println(msg);
        } else if (weather.equals("RAIN")) {
            newLat += 5;
            msg += "I love how the light refracts through the raindrops. Makes me think of autumn at Grandma's...";
            Simulator.writer.println(msg);
        } else if (weather.equals("FOG")) {
            newLat += 1;
            msg += "It's a little misty out. Bumps could be turbulence. Could be us bumping into things too...";
            Simulator.writer.println(msg);
        } else if (weather.equals("SNOW")) {
            newHgt -= 7;
            msg += "To the lady on the wing: please stop making snow angels and make your way back to your seat.";
            Simulator.writer.println(msg);
        }

        this.coordinates = new Coordinates(newLng, newLat, newHgt);

        if (this.coordinates.getHeight() <= 0) {
            this._weatherTower.unregister(this);
            Simulator.writer.println(String.format("JetPlane#%s(%d): AIRCRAFT INCOMING! @ %d,%d.",
                    this.name, this.id, newLng, newLat));
            Simulator.writer.println(String.format("Tower says: JetPlane#%s(%d) unregistered to weather tower.",
                    this.name, this.id));
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this._weatherTower = weatherTower;
        Simulator.writer.println(String.format("Tower says: JetPlane#%s(%d) registered to weather tower.",
                this.name, this.id));
    }
}

