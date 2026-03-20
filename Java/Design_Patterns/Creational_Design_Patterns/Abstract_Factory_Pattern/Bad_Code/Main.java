package Bad_Code;

public class Main {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Vehicle vehicle = carFactory.createVehicle("BMW");
        vehicle.start();
        vehicle.stop();
    }
}
