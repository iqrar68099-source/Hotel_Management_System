public class RoomType {

    private String kind;  
    private double cost;  

    
    public RoomType(String kind, double cost) {
        
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative: " + cost);
        }
        if (kind == null || kind.trim().isEmpty()) {
            throw new IllegalArgumentException("Room kind is required and cannot be empty");
        }
        
        this.kind = kind;
        this.cost = cost;
    }

    public double calculateTotalCost(int nights) {
        if (nights <= 0) return 0;
        return this.cost * nights;
    }

    
    public double getDiscountedPrice(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            return this.cost;
        }
        return this.cost * (1 - (discountPercentage / 100));
    }

    public double getCost() { return cost; }
    public String getKind() { return kind; }

    @Override
    public String toString() {
        return kind + " Room ($" + cost + " per night)";
    }
}