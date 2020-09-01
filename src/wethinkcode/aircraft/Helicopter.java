package wethinkcode.aircraft;

import wethinkcode.interfaces.Flyable;
import wethinkcode.simulator.Simulator;
import wethinkcode.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = _weatherTower.getWeather(this.coordinates);

        String msg = String.format("Helicopter#%s(%d): ", this.name, this.id);
        int newLong = this.coordinates.getLongitude();
        int newLat = this.coordinates.getLatitude();
        int newHeight = this.coordinates.getHeight();

        if (weather.equals("SUN")) {
            newLong += 10;
            newHeight += 2;
            msg += "Blimey, Archibald! I can see the elephants! The Okavango is splendid this time of year...";
            Simulator.writer.println(msg);
        } else if (weather.equals("RAIN")) {
            newLong += 5;
            msg += "Well, Crikey, Fred. I'm not flying with you again if you don't check the weather. It's spring rain.";
            Simulator.writer.println(msg);
        } else if (weather.equals("FOG")) {
            newLong += 1;
            msg += "It's a little misty out. Bumps could be turbulence. Could be us bumping into things.";
            Simulator.writer.println(msg);
        } else if (weather.equals("SNOW")) {
            newHeight -= 12;
            msg += "Getting a little to close too the peaks, chap. I feel the chill in my bones.";
            Simulator.writer.println(msg);
        }

        this.coordinates = new Coordinates(newLong, newLat, newHeight);

        if (this.coordinates.getHeight() <= 0) {
            this._weatherTower.unregister(this);
            Simulator.writer.println(String.format("Helicopter#%s(%d): AIRCRAFT INCOMING! @ %d,%d.",
                    this.name, this.id, newLong, newLat));
            Simulator.writer.println(String.format("Tower says: Helicopter#%s(%d) unregistered to weather tower.",
                    this.name, this.id));
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this._weatherTower = weatherTower;
        Simulator.writer.println(String.format("Tower says: Helicopter#%s(%d) registered to weather tower.",
                this.name, this.id));
    }
}
