package Good_Code.Concrete_Car_Factories;

import Good_Code.Concrete_Car_Brands.Honda;
import Good_Code.Interfaces.Vehicle;
import Good_Code.Interfaces.VehicleFactory;

public class HondaFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new Honda();
    }

}
