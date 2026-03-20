package Good_Code.Concrete_Vehicles;

import Good_Code.Vehicle;

public class Car implements Vehicle {
    public Car() {
        System.out.println("Car object created!");
    }

    @Override
    public void Start() {
        System.out.println("Car Started");
    }

    @Override
    public void Stop() {
        System.out.println("Car Stopped");
    }
}
