    
import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTest {

    @Test
    public void testRoomCreation_ShouldSetNumberAndType() {
        // 1. Arrange
        int expectedNumber = 101;
        RoomType expectedType = new RoomType("Deluxe", 5000.0);

        // 2. Act
        Room room = new Room(expectedNumber, expectedType);

        // 3. Assert
        assertNotNull("Room object null nahi hona chahiye", room);
        assertEquals(expectedNumber, room.getNumber());
        assertEquals(expectedType, room.getType());
        assertTrue("Naya room shuru mein khali (available) hona chahiye", room.isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomCreation_InvalidNumber_ShouldThrowException() {
        // Validation check for room number <= 0
        new Room(0, new RoomType("Standard", 3000.0));
    }

    @Test(expected = NullPointerException.class)
    public void testRoomCreation_NullType_ShouldThrowException() {
        // Validation check for null RoomType
        new Room(102, null);
    }

    @Test
    public void testCreateGuest_ShouldSetRoomToOccupied() {
        // 1. Arrange
        RoomType type = new RoomType("Suite", 10000.0);
        Room room = new Room(201, type);
        
        // 2. Act
        room.createGuest("Zeeshan", "Karachi, Pakistan");

        // 3. Assert
        // Logic verify karein ke guest aane ke baad room khali nahi rehna chahiye
        assertFalse("Guest assign hone ke baad room occupied hona chahiye", room.isAvailable());
    }

    @Test
    public void testCheckout_ShouldMakeRoomAvailableAgain() {
        // 1. Arrange
        Room room = new Room(301, new RoomType("Single", 2000.0));
        room.createGuest("Ali", "Lahore");

        // 2. Act
        room.checkout();

        // 3. Assert
        assertTrue("Checkout ke baad room dobara available hona chahiye", room.isAvailable());
    }
}
