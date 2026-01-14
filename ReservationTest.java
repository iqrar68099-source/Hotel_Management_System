import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

public class ReservationTest {

    @Test
    public void testCreateReservation_ShouldSetAllFields() {
        // 1. Arrange
        // Aaj se 10 minute aage ka time (Validation pass karne ke liye)
        Date start = new Date(System.currentTimeMillis() + 600000); 
        Date end = new Date(start.getTime() + (1000 * 60 * 60 * 24)); // 1 din baad
        RoomType type = new RoomType("Deluxe", 5000.0);
        int roomCount = 2;

        // 2. Act
        Reservation res = Reservation.create(start, end, type, roomCount);

        // 3. Assert
        assertNotNull("Reservation object banna chahiye", res);
        assertEquals("Room count match hona chahiye", roomCount, res.getNumber());
        assertEquals("Room type match hona chahiye", type, res.getRoomType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateReservation_EndBeforeStart_ShouldThrowException() {
        // 1. Arrange
        Date start = new Date(System.currentTimeMillis() + 3600000); // 1 ghanta aage
        Date endBeforeStart = new Date(start.getTime() - 5000); // Start se 5 second pehle
        RoomType type = new RoomType("Standard", 3000.0);

        // 2. Act
        Reservation.create(start, endBeforeStart, type, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateReservation_PastStartDate_ShouldThrowException() {
        // 1. Arrange - Kal ki purani date
        Date pastDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24)); 
        Date end = new Date();
        RoomType type = new RoomType("Standard", 3000.0);

        // 2. Act
        Reservation.create(pastDate, end, type, 1);
    }

    @Test
    public void testAddRoom_CapacityLimit() {
        // 1. Arrange: Sirf 1 room ki reservation
        RoomType type = new RoomType("Suite", 10000.0);
        Date start = new Date(System.currentTimeMillis() + 600000);
        Reservation res = Reservation.create(start, new Date(start.getTime() + 3600000), type, 1);
        
        Room room1 = new Room(101, type);
        Room room2 = new Room(102, type);

        // 2. Act
        res.addRoom(room1);
        res.addRoom(room2); // Yeh room add nahi hona chahiye (logic limit)

        // 3. Assert
        assertTrue("Reservation 1 room ke liye fully assigned honi chahiye", res.isFullyAssigned());
    }
}