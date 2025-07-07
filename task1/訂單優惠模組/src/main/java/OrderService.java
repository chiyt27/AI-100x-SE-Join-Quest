import java.util.List;
import java.util.ArrayList;

public class OrderService {
    private double thresholdAmount = 0;
    private double thresholdDiscount = 0;
    private String bogoCategory = null;
    
    public void configureThresholdDiscount(double threshold, double discount) {
        if (threshold < 0 || discount < 0) {
            throw new IllegalArgumentException("Threshold and discount must be non-negative");
        }
        this.thresholdAmount = threshold;
        this.thresholdDiscount = discount;
    }
    
    public void configureBuyOneGetOnePromotion(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("BOGO category cannot be null or empty");
        }
        this.bogoCategory = category;
    }
    
    public Order checkout(List<OrderItem> items) {
        // Calculate pricing based on original quantities
        double originalAmount = calculateOriginalAmount(items);
        double discount = calculateDiscount(originalAmount);
        double totalAmount = originalAmount - discount;
        
        // Create result items and apply BOGO promotions to modify quantities
        List<OrderItem> resultItems = new ArrayList<>(items);
        applyBuyOneGetOnePromotion(resultItems);
        
        return new Order(resultItems, totalAmount, originalAmount, discount);
    }
    
    private double calculateOriginalAmount(List<OrderItem> items) {
        double amount = 0;
        for (OrderItem item : items) {
            amount += item.getProduct().getUnitPrice() * item.getQuantity();
        }
        return amount;
    }
    
    private double calculateDiscount(double originalAmount) {
        double discount = 0;
        
        // Apply threshold discount if configured and amount meets threshold
        if (hasThresholdDiscount() && originalAmount >= thresholdAmount) {
            discount += thresholdDiscount;
        }
        
        return discount;
    }
    
    private boolean hasThresholdDiscount() {
        return thresholdAmount > 0;
    }
    
    private boolean hasBuyOneGetOnePromotion() {
        return bogoCategory != null;
    }
    
    private void applyBuyOneGetOnePromotion(List<OrderItem> items) {
        if (!hasBuyOneGetOnePromotion()) {
            return; // No BOGO promotion configured
        }
        
        for (OrderItem item : items) {
            if (bogoCategory.equals(item.getProduct().getCategory())) {
                // Add 1 free item for each qualifying product category
                item.setQuantity(item.getQuantity() + 1);
            }
        }
    }
}