package wethinkcode.aircraft;

import wethinkcode.interfaces.Flyable;
import wethinkcode.simulator.Simulator;
import wethinkcode.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = _weatherTower.getWeather(this.coordinates);

        String msg = String.format("Baloon#%s(%d): ", this.name, this.id);
        int newLong = this.coordinates.getLongitude();
        int newLat = this.coordinates.getLatitude();
        int newHeight = this.coordinates.getHeight();

        if (weather.equals("SUN")) {
            newLong += 2;
            newHeight += 4;
            msg += "Well folks, looks like clear skies and plane sailing... I mean floating.";
            Simulator.writer.println(msg);
        } else if (weather.equals("RAIN")) {
            newHeight -= 5;
            msg += "I know this isn't a parade, but I do feel well rained on. Should've gone with the helicopter flip instead.";
            Simulator.writer.println(msg);
        } else if (weather.equals("FOG")) {
            newHeight -= 3;
            msg += "How many fingers am I holding up? I can't tell either. We really shouldn't fly in anything other than sunny weather.";
            Simulator.writer.println(msg);
        } else if (weather.equals("SNOW")) {
            newHeight -= 15;
            msg += "I mean, a baloon in the snow is just silly. Does noone check the forecasts anymore?!";
            Simulator.writer.println(msg);
        }

        this.coordinates = new Coordinates(newLong, newLat, newHeight);

        if (this.coordinates.getHeight() <= 0) {
            this._weatherTower.unregister(this);
            Simulator.writer.println(String.format("Baloon#%s(%d): AIRCRAFT INCOMING! @ %d,%d.",
                    this.name, this.id, newLong, newLat));
            Simulator.writer.println(String.format("Tower says: Baloon#%s(%d) unregistered to weather tower.",
                    this.name, this.id));
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this._weatherTower = weatherTower;
        Simulator.writer.println(String.format("Tower says: Baloon#%s(%d) registered to weather tower.",
                this.name, this.id));
    }
}
