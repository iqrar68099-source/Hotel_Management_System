import java.util.Date;

public class IntegrationTest {
    public static void main(String[] args) {
        try {
            // --- 1. Arrange ---
            Hotel hotel = new Hotel("Beach Resort");
            RoomType suiteType = new RoomType("Suite", 50000.0);
            Room room = new Room(505, suiteType);
            hotel.addRoom(room);

            // ReserverPayer (Card length check: 16 digits)
            ReserverPayer payer = ReserverPayer.create("P-100", "5555444433332222");

            // Date setup with 2-minute cushion to avoid "Past Date" error
            Date startDate = new Date(System.currentTimeMillis() + 120000); 
            Date endDate = new Date(startDate.getTime() + (5 * 24 * 60 * 60 * 1000));

            // --- 2. Act ---
            
            // Reservation create karein
            Reservation res = Reservation.create(startDate, endDate, suiteType, 1);
            res.addRoom(room); 
            
            // Hotel aur Payer ke saath link karein
            hotel.createReservation(res);
            payer.makeReservation(res);

            // --- 3. Assert ---
            
            // Room availability aur Payer linking dono check karein
            boolean isHotelReady = hotel.available();
            boolean isReservationLinked = (payer.getReservation() == res);

            System.out.println("\n--- Integration Result ---");
            if (isHotelReady && isReservationLinked) {
                System.out.println("✅ Integration Test Passed: Full booking flow is working!");
                System.out.println("Hotel Name  : " + hotel.getName());
                System.out.println("Room Number : " + room.getNumber() + " (" + suiteType.getKind() + ")");
                System.out.println("Payer ID    : " + payer.getId());
                System.out.println("Total Stay  : 5 Days");
            } else {
                System.out.println("❌ Integration Test Failed!");
                if(!isHotelReady) System.out.println("Reason: Hotel reported as not available.");
                if(!isReservationLinked) System.out.println("Reason: Reservation not linked to payer.");
            }

        } catch (Exception e) {
            System.out.println("❌ Test crashed with error: " + e.getMessage());
            // Debugging ke liye stack trace zaroori hai
        }
    }
}