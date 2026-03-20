package Good_Code.Concrete_Vehicles;

public class Truck implements Good_Code.Vehicle {
    public Truck() {
        System.out.println("Truck object created!");
    }

    @Override
    public void Start() {
        System.out.println("Truck Started");
    }

    @Override
    public void Stop() {
        System.out.println("Truck Stopped");
    }

}
