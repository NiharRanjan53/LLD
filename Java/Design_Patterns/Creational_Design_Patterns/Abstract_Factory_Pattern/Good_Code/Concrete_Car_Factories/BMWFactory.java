package Good_Code.Concrete_Car_Factories;

import Good_Code.Concrete_Car_Brands.BMW;
import Good_Code.Interfaces.VehicleFactory;

public class BMWFactory implements VehicleFactory {

    @Override
    public Good_Code.Interfaces.Vehicle createVehicle() {
        return new BMW();
    }

}
