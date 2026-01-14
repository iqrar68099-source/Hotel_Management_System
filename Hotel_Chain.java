import java.util.ArrayList;
import java.util.List;

public class Hotel_Chain {
    private String name;
    private List<Hotel> hotels;
    private List<ReserverPayer> payers;

    public Hotel_Chain(String name) {
        this.name = name;
        this.hotels = new ArrayList<>();
        this.payers = new ArrayList<>();
    }

    // UML Operation: Thoda logic improve kiya gaya hai
    public void createReserverPayer(String id, String card) {
        // Check karein ke kahin ye ID pehle se to maujood nahi
        for (ReserverPayer p : payers) {
            if (p.getId().equals(id)) {
                System.out.println("Error: Payer with ID " + id + " already exists.");
                return;
            }
        }
        ReserverPayer newPayer = ReserverPayer.create(id, card);
        this.payers.add(newPayer);
    }

    public void addPayer(ReserverPayer payer) {
        if (payer != null && !payers.contains(payer)) {
            this.payers.add(payer);
        }
    }

    // UML Operation: Reservation logic validation ke saath
    public void makeReservation(ReserverPayer payer, Reservation res) {
        if (payers.contains(payer)) {
            payer.makeReservation(res);
            System.out.println("Reservation successfully linked to Payer.");
        } else {
            System.out.println("Error: Payer not found in this Hotel Chain.");
        }
    }

    public void addHotel(Hotel hotel) {
        if (hotel != null) {
            this.hotels.add(hotel);
        }
    }

    // UML Operations (Placeholders logic ke saath)
    public void cancelReservation() { 
        // Real-world scenario mein yahan ID mangi jati hai
        System.out.println("System: Processing reservation cancellation...");
    }

    public void checkInGuest() {
        System.out.println("System: Identity verified. Guest check-in complete.");
    }

    public void checkOutGuest() { 
        System.out.println("System: Payment processed. Guest checked out.");
    }

    // Getters for scannability
    public String getName() { return name; }
    public List<Hotel> getHotels() { return hotels; }
}