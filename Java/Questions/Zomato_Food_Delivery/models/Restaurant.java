package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static int nextResturantId = 0;
    private int id;
    private String name;
    private String address;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name, String address) {
        this.id = ++nextResturantId;
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

}
