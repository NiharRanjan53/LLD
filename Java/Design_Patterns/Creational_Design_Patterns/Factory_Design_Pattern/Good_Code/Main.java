package Good_Code;

public class Main {
    public static void main(String[] args) {
        System.out.println("Good Code Example");
        Vehicle vehicle1 = VehicleFactory.createVehicle("CAR");
        vehicle1.Start();
        vehicle1.Stop();
        Vehicle vehicle2 = VehicleFactory.createVehicle("TRUCK");
        vehicle2.Start();
        vehicle2.Stop();
        Vehicle unknown = VehicleFactory.createVehicle("BIKE");
        unknown.Start();
        unknown.Stop();

    }

}
