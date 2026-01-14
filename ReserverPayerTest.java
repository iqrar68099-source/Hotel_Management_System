import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

public class ReserverPayerTest {

    @Test
    public void testCreateReserverPayer_ShouldSetCorrectDetails() {
        // 1. Arrange
        String expectedId = "P-101";
        String validCard = "1234567890123456"; // 16 digits

        // 2. Act
        ReserverPayer payer = ReserverPayer.create(expectedId, validCard);

        // 3. Assert
        assertNotNull("Payer object null nahi hona chahiye", payer);
        assertEquals(expectedId, payer.getId());
        assertEquals(validCard, payer.getCreditCardDetails());
    }

    @Test
    public void testCardMasking_ShouldOnlyShowLastFourDigits() {
        // 1. Arrange
        String card = "1111222233334444";
        ReserverPayer payer = ReserverPayer.create("P-101", card);
        String expectedMasked = "****-****-****-4444";

        // 2. Act
        String actualMasked = payer.getMaskedCardDetails();

        // 3. Assert
        assertEquals("Card number sahi mask hona chahiye", expectedMasked, actualMasked);
    }

    @Test
    public void testMakeReservation_ShouldLinkReservationToPayer() {
        // 1. Arrange
        ReserverPayer payer = ReserverPayer.create("P-101", "1234567890123456");
        RoomType type = new RoomType("Deluxe", 5000.0);
        
        // Future dates for reservation
        Date start = new Date(System.currentTimeMillis() + 3600000); 
        Date end = new Date(start.getTime() + 86400000);
        Reservation res = Reservation.create(start, end, type, 1);

        // 2. Act
        payer.makeReservation(res);

        // 3. Assert
        assertNotNull("Reservation link honi chahiye", payer.getReservation());
        assertEquals("Link ki gayi reservation wahi honi chahiye", res, payer.getReservation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_WithShortCardNumber_ShouldThrowException() {
        // Validation check for security (less than 12 digits should fail)
        ReserverPayer.create("P-102", "12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_WithNullId_ShouldThrowException() {
        // Null ID check
        ReserverPayer.create(null, "1234567890123456");
    }
}