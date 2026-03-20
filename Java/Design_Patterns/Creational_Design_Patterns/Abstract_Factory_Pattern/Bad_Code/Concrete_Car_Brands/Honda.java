package Bad_Code.Concrete_Car_Brands;

import Bad_Code.Vehicle;

public class Honda implements Vehicle {
    @Override
    public void start() {
        System.out.println("Honda Car is starting");
    }

    @Override
    public void stop() {
        System.out.println("Honda Car is stopping");
    }
}
