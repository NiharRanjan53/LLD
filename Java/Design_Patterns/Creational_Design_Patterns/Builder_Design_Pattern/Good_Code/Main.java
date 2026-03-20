package Good_Code;

public class Main {
    public static void main(String[] args) {
        Car.CarBuilder builder = new Car.CarBuilder();

        Car car1 = builder.setEngine("V8")
                .setWheels(4)
                .setColor("Blue")
                .setSeats(6)
                .setSunroof(true)
                .setNavigationSystem(true)
                .build();
        System.out.println(car1);

    }

}
