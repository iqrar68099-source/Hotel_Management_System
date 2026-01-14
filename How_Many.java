public class How_Many {
    private int number; // UML: number: Integer 

    public How_Many(int number) {
        // Defensive Programming: Validation
        if (number <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        // Example: Hotel limit check (Optional logic)
        if (number > 100) {
            System.out.println("Warning: Large group booking detected.");
        }
        this.number = number;
    }

    // Business Logic: Kya ye number available rooms ke limit mein hai?
    public boolean isValidForLimit(int availableInventory) {
        return this.number <= availableInventory;
    }

    // Business Logic: Agar multiple rooms hain to base price calculate karna
    public double multiplyWithRate(double rate) {
        return this.number * rate;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}