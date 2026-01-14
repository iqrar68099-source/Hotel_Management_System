import static org.junit.Assert.*;
import org.junit.Test;

public class How_ManyTest {

    @Test
    public void testHowManyCreation_ShouldSetValidNumber() {
        // 1. Arrange
        int expectedNumber = 5;

        // 2. Act
        How_Many hm = new How_Many(expectedNumber);

        // 3. Assert
        assertEquals("The number should match the input", expectedNumber, hm.getNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHowMany_WithZero_ShouldThrowException() {
        // Validation check for zero
        new How_Many(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHowMany_WithNegativeNumber_ShouldThrowException() {
        // Validation check for negative number
        new How_Many(-10);
    }

    @Test
    public void testLogic_MultiplyWithRate() {
        // 1. Arrange
        How_Many hm = new How_Many(3); // Maslan 3 rooms
        double rate = 1000.0;
        double expectedTotal = 3000.0;

        // 2. Act
        double actualTotal = hm.multiplyWithRate(rate);

        // 3. Assert
        assertEquals(expectedTotal, actualTotal, 0.001); // 0.001 is delta for double comparison
    }
}