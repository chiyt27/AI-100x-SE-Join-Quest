import java.util.List;

public class Order {
    private List<OrderItem> items;
    private double totalAmount;
    private double originalAmount;
    private double discount;
    
    public Order(List<OrderItem> items, double totalAmount, double originalAmount, double discount) {
        this.items = items;
        this.totalAmount = totalAmount;
        this.originalAmount = originalAmount;
        this.discount = discount;
    }
    
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public double getOriginalAmount() { return originalAmount; }
    public double getDiscount() { return discount; }
}