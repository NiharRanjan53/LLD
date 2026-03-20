import java.util.*;

interface SmartComponent {
    public void turnOn();

    public void turnOff();

}

// Concrete class for Individuals (Leaf)
class AirConditioner implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Air Conditioner is now ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Air Conditioner is now OFF");

    }
}

class SmartLight implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Light is now ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Light is now OFF");
    }
}

// Concrete class for group elements (Composite)
class CompositeSmartComponent implements SmartComponent {
    private List<SmartComponent> components;

    CompositeSmartComponent() {
        this.components = new ArrayList<>();
    }

    public void addComponent(SmartComponent component) {
        this.components.add(component);
    }

    public void removeComponent(SmartComponent component) {
        this.components.remove(component);
    }

    @Override
    public void turnOn() {
        for (SmartComponent component : components) {
            component.turnOn();
        }
    }

    @Override
    public void turnOff() {
        for (SmartComponent component : components) {
            component.turnOff();
        }

    }
}

public class CompositeSmartHomeController {
    public static void main(String[] args) {
        // Creating Leaf Node
        SmartComponent airConditoner = new AirConditioner();
        SmartComponent smartLight = new SmartLight();

        // Creating Rooms
        CompositeSmartComponent room1 = new CompositeSmartComponent();
        room1.addComponent(airConditoner);
        room1.addComponent(smartLight);
        CompositeSmartComponent room2 = new CompositeSmartComponent();
        room2.addComponent(airConditoner);
        room2.addComponent(smartLight);

        // Creating Floor
        CompositeSmartComponent floor1 = new CompositeSmartComponent();
        floor1.addComponent(room1);
        floor1.addComponent(room2);

        // Creating Floor
        CompositeSmartComponent floor2 = new CompositeSmartComponent();
        floor2.addComponent(room1);
        floor2.addComponent(room2);

        // Creating the house
        CompositeSmartComponent house = new CompositeSmartComponent();
        house.addComponent(floor1);
        house.addComponent(floor2);

        house.turnOn();
        System.out.println("==================");
        floor1.turnOn();

    }
}
