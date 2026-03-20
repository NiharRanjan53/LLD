package Good_Code;

import Good_Code.Concrete_Vehicles.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType) {

        if (vehicleType.equalsIgnoreCase("CAR")) {
            return new Car();
        } else if (vehicleType.equalsIgnoreCase("TRUCK")) {
            return new Truck();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }

}
