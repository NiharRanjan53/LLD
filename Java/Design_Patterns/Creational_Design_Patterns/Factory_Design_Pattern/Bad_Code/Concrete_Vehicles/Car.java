package Bad_Code.Concrete_Vehicles;

import Bad_Code.Vehicle;

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Car is sopping...");
    }
}
