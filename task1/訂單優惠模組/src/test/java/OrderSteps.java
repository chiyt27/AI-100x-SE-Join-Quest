import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class OrderSteps {
    private OrderService orderService;
    private Order order;
    private List<OrderItem> orderItems;
    private double thresholdAmount;
    private double thresholdDiscount;
    private boolean bogoActive;
    
    @Given("no promotions are applied")
    public void noPromotionsAreApplied() {
        orderService = new OrderService();
        orderItems = new ArrayList<>();
    }
    
    @When("a customer places an order with:")
    public void aCustomerPlacesAnOrderWith(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        
        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            int quantity = Integer.parseInt(row.get("quantity"));
            double unitPrice = Double.parseDouble(row.get("unitPrice"));
            String category = row.get("category"); // may be null for first scenario
            
            Product product = new Product(productName, unitPrice, category);
            OrderItem orderItem = new OrderItem(product, quantity);
            orderItems.add(orderItem);
        }
        
        order = orderService.checkout(orderItems);
    }
    
    @Then("the order summary should be:")
    public void theOrderSummaryShouldBe(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedSummary = rows.get(0);
        
        if (expectedSummary.containsKey("totalAmount")) {
            double expectedTotal = Double.parseDouble(expectedSummary.get("totalAmount"));
            assertEquals(expectedTotal, order.getTotalAmount(), 0.01);
        }
        
        if (expectedSummary.containsKey("originalAmount")) {
            double expectedOriginal = Double.parseDouble(expectedSummary.get("originalAmount"));
            assertEquals(expectedOriginal, order.getOriginalAmount(), 0.01);
        }
        
        if (expectedSummary.containsKey("discount")) {
            double expectedDiscount = Double.parseDouble(expectedSummary.get("discount"));
            assertEquals(expectedDiscount, order.getDiscount(), 0.01);
        }
    }
    
    @And("the customer should receive:")
    public void theCustomerShouldReceive(DataTable dataTable) {
        List<Map<String, String>> expectedItems = dataTable.asMaps(String.class, String.class);
        
        assertEquals(expectedItems.size(), order.getItems().size());
        
        for (int i = 0; i < expectedItems.size(); i++) {
            Map<String, String> expectedItem = expectedItems.get(i);
            OrderItem actualItem = order.getItems().get(i);
            
            assertEquals(expectedItem.get("productName"), actualItem.getProduct().getName());
            assertEquals(Integer.parseInt(expectedItem.get("quantity")), actualItem.getQuantity());
        }
    }
    
    @Given("the threshold discount promotion is configured:")
    public void theThresholdDiscountPromotionIsConfigured(DataTable dataTable) {
        orderService = new OrderService();
        orderItems = new ArrayList<>();
        
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> config = rows.get(0);
        
        thresholdAmount = Double.parseDouble(config.get("threshold"));
        thresholdDiscount = Double.parseDouble(config.get("discount"));
        
        // TODO: Configure the OrderService with threshold discount
        orderService.configureThresholdDiscount(thresholdAmount, thresholdDiscount);
    }
    
    @Given("the buy one get one promotion for cosmetics is active")
    public void theBuyOneGetOnePromotionForCosmeticsIsActive() {
        if (orderService == null) {
            orderService = new OrderService();
            orderItems = new ArrayList<>();
        }
        bogoActive = true;
        
        // Configure the OrderService with BOGO promotion
        orderService.configureBuyOneGetOnePromotion("cosmetics");
    }
    
    @Given("the Double 11 promotion is active")
    public void theDouble11PromotionIsActive() {
        orderService = new OrderService();
        orderItems = new ArrayList<>();
        
        // Configure the OrderService with Double 11 promotion
        orderService.configureDouble11Promotion();
    }
}