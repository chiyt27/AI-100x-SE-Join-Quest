import java.util.List;
import java.util.ArrayList;

public class OrderService {
    private double thresholdAmount = 0;
    private double thresholdDiscount = 0;
    private String bogoCategory = null;
    private boolean double11Active = false;
    
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
    
    public void configureDouble11Promotion() {
        this.double11Active = true;
    }
    
    public Order checkout(List<OrderItem> items) {
        // Calculate pricing based on original quantities
        double originalAmount = calculateOriginalAmount(items);
        double discount = calculateDiscount(originalAmount, items);
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
    
    private double calculateDiscount(double originalAmount, List<OrderItem> items) {
        double discount = 0;
        
        // Apply Double 11 discount if active
        if (double11Active) {
            discount += calculateDouble11Discount(items);
        } else {
            // Apply threshold discount if configured and amount meets threshold
            if (hasThresholdDiscount() && originalAmount >= thresholdAmount) {
                discount += thresholdDiscount;
            }
        }
        
        return discount;
    }
    
    private boolean hasThresholdDiscount() {
        return thresholdAmount > 0;
    }
    
    private double calculateDouble11Discount(List<OrderItem> items) {
        double totalDiscount = 0;
        
        for (OrderItem item : items) {
            int quantity = item.getQuantity();
            double unitPrice = item.getProduct().getUnitPrice();
            
            // Calculate how many groups of 10 this product has
            int discountedGroups = quantity / 10;
            
            // Each group of 10 gets 20% discount
            double discountPerGroup = 10 * unitPrice * 0.2;
            totalDiscount += discountedGroups * discountPerGroup;
        }
        
        return totalDiscount;
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