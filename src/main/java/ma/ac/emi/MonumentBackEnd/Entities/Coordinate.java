package ma.ac.emi.MonumentBackEnd.Entities;

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate() {
        latitude = 0;
        longitude = 0;
    }

    public Coordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
