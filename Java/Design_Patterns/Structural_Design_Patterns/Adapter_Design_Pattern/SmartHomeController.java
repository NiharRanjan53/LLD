interface SmartDevice {
    void turnOn();

    void turnOff();
}

// Concrete Smart Devices
class AirConditioner {
    public void connectViaBlutooth() {
        System.out.println("Air conditioner connected via Blutooth");
    }

    public void startCooling() {
        System.out.println("Cooling Started");
    }

    public void stopCooling() {
        System.out.println("Cooling Stoped");
    }

    public void disconnectBlutooth() {
        System.out.println("Air conditioner disconnected from blutooth");
    }

}

class SmartLight {
    public void connectToWiFi() {
        System.out.println("Smart Light connected to Wi-Fi.");
    }

    public void switchOn() {
        System.out.println("Smart Light is now ON.");
    }

    public void switchOff() {
        System.out.println("Smart Light is now OFF.");
    }

    public void disconnectWiFi() {
        System.out.println("Smart Light disconnected from Wi-Fi.");
    }
}

// Concrete Adapters
class AirConditionerAdapter implements SmartDevice {
    AirConditioner airConditioner;

    AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void turnOn() {
        airConditioner.connectViaBlutooth();
        airConditioner.startCooling();
    }

    @Override
    public void turnOff() {
        airConditioner.stopCooling();
        airConditioner.disconnectBlutooth();
    }
}

class SmartLightAdapter implements SmartDevice {
    private SmartLight smartLight;

    protected SmartLightAdapter(SmartLight smartLight) {
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn() {
        smartLight.connectToWiFi();
        smartLight.switchOn();
    }

    @Override
    public void turnOff() {
        smartLight.switchOff();
        smartLight.disconnectWiFi();
    }
}

public class SmartHomeController {
    public static void main(String[] args) {

        SmartDevice airConditioner = new AirConditionerAdapter(new AirConditioner());
        airConditioner.turnOn();
        airConditioner.turnOff();
        SmartDevice smartLight = new SmartLightAdapter(new SmartLight());
        smartLight.turnOn();
        smartLight.turnOff();

    }
}