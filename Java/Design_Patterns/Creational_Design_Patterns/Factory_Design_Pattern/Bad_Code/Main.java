package Bad_Code;

import Bad_Code.Concrete_Vehicles.Bike;
import Bad_Code.Concrete_Vehicles.Car;
import Bad_Code.Concrete_Vehicles.Truck;

public class Main {
    public static void main(String[] args) {
        String vehicleType = "Car";
        Vehicle vehicle;

        if (vehicleType.equalsIgnoreCase("CAR")) {
            vehicle = new Car();
        } else if (vehicleType.equalsIgnoreCase("TRUCk")) {
            vehicle = new Truck();
        } else if (vehicleType.equalsIgnoreCase("BIKE")) {
            vehicle = new Bike();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }

        vehicle.start();
        vehicle.stop();
    }

}
