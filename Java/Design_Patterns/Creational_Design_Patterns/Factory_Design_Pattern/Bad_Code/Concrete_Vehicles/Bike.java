package Bad_Code.Concrete_Vehicles;

import Bad_Code.Vehicle;

public class Bike implements Vehicle {
    public Bike() {
        System.out.println("Bike object created....");
    }

    @Override
    public void start() {
        System.out.println("Bike is starting......");
    }

    @Override
    public void stop() {
        System.out.println("Bike is stopping........");
    }
}
