import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Hotel Chain aur Hotel setup
            Hotel_Chain myChain = new Hotel_Chain("Serena Group");
            Hotel myHotel = new Hotel("Serena Islamabad");
            myChain.addHotel(myHotel);

            // 2. Room Type aur Room setup
            // 25,000 PKR per night logic
            RoomType luxury = new RoomType("Deluxe", 25000.0);
            Room r101 = new Room(101, luxury);
            myHotel.addRoom(r101);

            // 3. ReserverPayer create karna (Payer details)
            String payerId = "P-5501";
            String card = "1234567812345678";
            
            // HotelChain ke method se payer register karein
            myChain.createReserverPayer(payerId, card);
            
            // Payer object ka reference lein (sirf testing ke liye)
            ReserverPayer payer = ReserverPayer.create(payerId, card);

            // 4. Dates set karna (Reservation period)
            Date checkIn = new Date(); // Aaj ki date
            Date checkOut = new Date(checkIn.getTime() + (2 * 24 * 60 * 60 * 1000)); // 2 din baad
            
            // 5. Reservation generate karna aur Payer se link karna
            Reservation myRes = Reservation.create(checkIn, checkOut, luxury, 1);
            myChain.makeReservation(payer, myRes);

            // 6. Actual Check-in (Guest assignment)
            System.out.println("\n--- Check-in Process ---");
            r101.createGuest("Ahmad Ali", "F-7, Islamabad");

            // 7. Final Summary Output
            displaySummary(myHotel, payer, luxury, myRes);

        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("System Error: " + e.getMessage());
        }
    }

    // Ek alag method summary print karne ke liye (Clean Code practice)
    private static void displaySummary(Hotel hotel, ReserverPayer payer, RoomType type, Reservation res) {
        System.out.println("\n======================================");
        System.out.println("      RESERVATION SUMMARY             ");
        System.out.println("======================================");
        System.out.println("Hotel      : " + hotel.getName());
        System.out.println("Payer ID   : " + payer.getId());
        System.out.println("Card Used  : " + payer.getMaskedCardDetails());
        System.out.println("Room Type  : " + type.getKind());
        System.out.println("Price/Night: " + type.getCost());
        System.out.println("Status     : ✅ Confirmed");
        System.out.println("======================================");
    }
}