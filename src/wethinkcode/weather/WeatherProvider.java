package wethinkcode.weather;

import wethinkcode.aircraft.Coordinates;

public class WeatherProvider {
    private static final WeatherProvider _weatherProvider = new WeatherProvider();
    private static String[] _weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        return _weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        if ((latitude%2 == 0) && (longitude%2 == 0) && (height%2 == 0)) {
            return _weather[0];
        } else if ((latitude%2 != 0) && (longitude%2 != 0) && (height%2 != 0)) {
            return _weather[1];
        } else if ((latitude%2 == 0) && (longitude%2 == 0) && (height%2 != 0)) {
            return _weather[2];
        } else {
            return _weather[3];
        }
    }
}
