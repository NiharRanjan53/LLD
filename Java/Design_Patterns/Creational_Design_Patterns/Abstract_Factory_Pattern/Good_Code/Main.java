package Good_Code;

import Good_Code.Interfaces.Vehicle;
import Good_Code.Concrete_Car_Factories.HondaFactory;
import Good_Code.Interfaces.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        VehicleFactory hondaFactory = new HondaFactory();
        Vehicle vehicle = hondaFactory.createVehicle();
        vehicle.start();
        vehicle.stop();
    }
}
