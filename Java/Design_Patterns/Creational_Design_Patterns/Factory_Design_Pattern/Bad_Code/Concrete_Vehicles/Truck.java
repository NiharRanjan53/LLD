package Bad_Code.Concrete_Vehicles;

import Bad_Code.Vehicle;

public class Truck implements Vehicle {
    @Override
    public void start() {
        System.out.println("Truck is Starting........");
    }

    @Override
    public void stop() {
        System.out.println("Truck is stopping.....");
    }

}
