package ParkingLot.LLD;

class Vehicle {
    private VehicleType vehicleType;
    public String vehicleNumber;
    public Vehicle(VehicleType vehicleType,String vehicleNumber) {
        this.vehicleType = vehicleType;this.vehicleNumber=vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle [vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber + "]";
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}

enum VehicleType {
    CAR, BIKE, TRUCK, VAN
}