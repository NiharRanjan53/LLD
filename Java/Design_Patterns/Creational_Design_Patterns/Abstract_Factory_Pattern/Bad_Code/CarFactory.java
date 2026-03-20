package Bad_Code;

import Bad_Code.Concrete_Car_Brands.BMW;
import Bad_Code.Concrete_Car_Brands.Honda;
import Bad_Code.Concrete_Car_Brands.Toyota;

public class CarFactory {
    public Vehicle createVehicle(String brand) {
        if (brand.equalsIgnoreCase("BMW")) {
            return new BMW();
        } else if (brand.equalsIgnoreCase("Honda")) {
            return new Honda();
        } else if (brand.equalsIgnoreCase("Toyota")) {
            return new Toyota();
        } else {
            throw new IllegalArgumentException("Unknown car brand");
        }
    }
}
