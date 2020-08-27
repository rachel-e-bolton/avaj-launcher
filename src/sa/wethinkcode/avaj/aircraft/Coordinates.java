package sa.wethinkcode.avaj.aircraft;

public class Coordinates {
    private int _longitude;
    private int _latitude;
    private int _height;

    public Coordinates(int longitude, int latitude, int height) {
        this._longitude = longitude;
        this._latitude = latitude;
        this._height = height;
    }

    public int getLongitude() {
        return this._longitude;
    }

    public int getLatitude() {
        return _latitude;
    }

    public int getHeight() {
        return _height;
    }
}
