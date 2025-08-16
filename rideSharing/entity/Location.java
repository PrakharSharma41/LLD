package rideSharing.entity;

public class Location {
    double lat;
    double lon;
    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public double distance(Location other){
        double dx=other.getLat()-this.getLat();
        double dy=other.getLon()-this.getLon();
        return Math.sqrt(dx*dx+dy*dy);
    }
}
