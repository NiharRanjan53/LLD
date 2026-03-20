package strategies;

public class UPIPaymentStrategy implements PaymentStrategy {
    private String mobile;

    public UPIPaymentStrategy(String mob) {
        this.mobile = mob;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI (" + mobile + ")");
    }
}
