package Good_Code.Concrete_Car_Factories;

import Good_Code.Concrete_Car_Brands.Toyota;
import Good_Code.Interfaces.Vehicle;
import Good_Code.Interfaces.VehicleFactory;

public class ToyotaFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new Toyota();
    }

}
