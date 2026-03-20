package Good_Code.Concrete_Car_Brands;

import Good_Code.Interfaces.Vehicle;

public class BMW implements Vehicle {
    @Override
    public void start() {
        System.out.println("BMW Car is starting");
    }

    @Override
    public void stop() {
        System.out.println("BMW Car is stopping");
    }
}
