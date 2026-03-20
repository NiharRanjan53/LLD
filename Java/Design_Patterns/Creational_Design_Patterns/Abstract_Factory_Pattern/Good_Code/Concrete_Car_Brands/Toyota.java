package Good_Code.Concrete_Car_Brands;

import Good_Code.Interfaces.Vehicle;

public class Toyota implements Vehicle {
    @Override
    public void start() {
        System.out.println("Toyota Car is starting");
    }

    @Override
    public void stop() {
        System.out.println("Toyota Car is stopping");
    }
}
