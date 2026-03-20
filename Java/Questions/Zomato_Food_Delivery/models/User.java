package models;

public class User {
    private int userId;
    private String name;
    private String address;
    private Cart cart;

    public User(int userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.cart = new Cart();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

}
