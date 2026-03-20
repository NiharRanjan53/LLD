package Good_Code;

public class Car {
    private String engine;
    private int wheels;
    private int seats;
    private String color;
    private boolean sunroof;
    private boolean navigationSystem;

    public Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.color = builder.color;
        this.wheels = builder.wheels;
        this.seats = builder.seats;
        this.sunroof = builder.sunroof;
        this.navigationSystem = builder.navigationSystem;
    }

    public String getEngine() {
        return this.engine;
    }

    public int getWheels() {
        return this.wheels;
    }

    public int getSeats() {
        return this.seats;
    }

    public String getColor() {
        return this.color;
    }

    public boolean hasSunroof() {
        return this.sunroof;
    }

    public boolean hasNavigationDystem() {
        return this.navigationSystem;
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", wheels=" + wheels + ", seats=" + seats
                + ", color=" + color + ", sunroof=" + sunroof
                + ", navigationSystem=" + navigationSystem + "]";
    }

    public static class CarBuilder {
        private String engine;
        private int wheels = 4;
        private int seats = 5;
        private String color = "Black";
        private boolean sunroof = false;
        private boolean navigationSystem = false;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public CarBuilder setNavigationSystem(boolean navigationSystem) {
            this.navigationSystem = navigationSystem;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }
}