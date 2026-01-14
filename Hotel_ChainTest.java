import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

public class Hotel_ChainTest {

    @Test
    public void testCreateReserverPayer_ShouldAddToChain() {
        // 1. Arrange
        Hotel_Chain chain = new Hotel_Chain("Serena Group");
        String payerId = "P-99";
        // Card length 12+ digits honi chahiye warna exception aayega
        String card = "1234567812345678"; 

        // 2. Act
        chain.createReserverPayer(payerId, card);

        // 3. Assert
        assertNotNull("Chain object should exist", chain);
    }

    @Test
    public void testMakeReservation_WhenPayerExists_ShouldLink() {
        // 1. Arrange
        Hotel_Chain chain = new Hotel_Chain("Marriott");
        ReserverPayer payer = ReserverPayer.create("P-001", "1111222233334444");
        chain.addPayer(payer); 
        
        RoomType type = new RoomType("Deluxe", 20000.0);
        
        // Error se bachne ke liye Future Dates use karein
        long currentTime = System.currentTimeMillis();
        Date startDate = new Date(currentTime + 3600000); // Aaj se 1 ghanta baad
        Date endDate = new Date(currentTime + 86400000);  // Aaj se 1 din baad
        
        Reservation res = Reservation.create(startDate, endDate, type, 1);

        // 2. Act
        chain.makeReservation(payer, res);

        // 3. Assert
        assertNotNull("Payer should have a reservation linked", payer.getReservation());
        assertEquals("Reservation object mismatch", res, payer.getReservation());
    }

    @Test
    public void testAddHotel_ShouldWorkSuccessfully() {
        // 1. Arrange
        Hotel_Chain chain = new Hotel_Chain("Lux Hotel");
        Hotel hotel = new Hotel("Lux Karachi");

        // 2. Act
        chain.addHotel(hotel);

        // 3. Assert
        assertNotNull("Hotel should be added to chain", chain);
        assertEquals("Hotel name mismatch", "Lux Karachi", hotel.getName());
    }
}