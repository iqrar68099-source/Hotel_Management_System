import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTypeTest {

    @Test
    public void testRoomTypeCreation_ShouldSetCorrectValues() {
        // 1. Arrange
        String expectedKind = "Deluxe";
        double expectedCost = 25000.0;

        // 2. Act
        RoomType room = new RoomType(expectedKind, expectedCost);

        // 3. Assert
        assertEquals(expectedKind, room.getKind());
        assertEquals(expectedCost, room.getCost(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomTypeCreation_NegativeCost_ShouldThrowException() {
        // Test case for invalid cost logic
        new RoomType("Standard", -100.0);
    }

    @Test
    public void testCalculateTotalCost_MultipleNights() {
        // 1. Arrange
        RoomType room = new RoomType("Suite", 10000.0);
        int nights = 3;
        double expectedTotal = 30000.0;

        // 2. Act
        double actualTotal = room.calculateTotalCost(nights);

        // 3. Assert
        assertEquals("Total cost for 3 nights should be 30000", expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void testGetDiscountedPrice_ValidDiscount() {
        // 1. Arrange
        RoomType room = new RoomType("Budget", 1000.0);
        double discountPercent = 10.0; // 10% discount
        double expectedPrice = 900.0;

        // 2. Act
        double actualPrice = room.getDiscountedPrice(discountPercent);

        // 3. Assert
        assertEquals("Price after 10% discount should be 900", expectedPrice, actualPrice, 0.001);
    }
}