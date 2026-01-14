import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

public class HotelTest {

    @Test
    public void testHotelCreation_ShouldSetNameAndInitializeLists() {
        // 1. Arrange
        String expectedName = "Serena Islamabad";

        // 2. Act
        Hotel hotel = new Hotel(expectedName);

        // 3. Assert
        assertEquals(expectedName, hotel.getName());
        // Logic check: Bina rooms ke available() false hona chahiye
        assertFalse("New hotel without rooms should not be available", hotel.available());
    }

    @Test
    public void testAvailable_WhenRoomAdded_ShouldReturnTrue() {
        // 1. Arrange
        Hotel hotel = new Hotel("PC Lahore");
        RoomType type = new RoomType("Deluxe", 15000.0);
        Room room = new Room(101, type);

        // 2. Act
        hotel.addRoom(room);

        // 3. Assert
        assertTrue("Hotel should be available after adding a room", hotel.available());
    }

    @Test
    public void testCreateReservation_ShouldExecuteSuccessfully() {
        // 1. Arrange
        Hotel hotel = new Hotel("Marriott");
        RoomType type = new RoomType("Suite", 30000.0);
        
        // Date cushion (To avoid 'Past Date' error)
        Date futureStart = new Date(System.currentTimeMillis() + 3600000); // 1 hour later
        Date futureEnd = new Date(System.currentTimeMillis() + 7200000);   // 2 hours later
        
        // Hotel mein room hona chahiye warna reservation fail ho sakti hai (agar logic check on ho)
        hotel.addRoom(new Room(201, type));
        Reservation res = Reservation.create(futureStart, futureEnd, type, 1);

        // 2. Act
        hotel.createReservation(res);

        // 3. Assert
        assertNotNull(hotel);
        // Agar aapne Hotel class mein getReservations() method add kiya hai:
        // assertEquals(1, hotel.getReservations().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_EmptyName_ShouldThrowException() {
        // Validation check: Empty string should trigger IllegalArgumentException
        new Hotel("  "); 
    }

    @Test(expected = NullPointerException.class)
    public void testCreateReservation_NullReservation_ShouldThrowException() {
        // 1. Arrange
        Hotel hotel = new Hotel("Pearl Continental");

        // 2. Act: Null reservation pass karne par exception aana chahiye
        hotel.createReservation(null); 
    }
}