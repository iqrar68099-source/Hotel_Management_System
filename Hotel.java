import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name; 
    private List<Room> rooms; 
    private List<Reservation> reservations;

    // Constructor (UML: create())
    public Hotel(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty");
        }
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // UML Operation: available() - Real logic add kiya gaya hai
    public boolean available() {
        // Logic: Agar ek bhi room list mein hai aur wo occupied nahi hai
        for (Room room : rooms) {
            if (room.isAvailable()) { // Assuming Room class has isAvailable() method
                return true;
            }
        }
        return false;
    }

    // UML Operation: createReservation()
    public void createReservation(Reservation res) {
        if (res == null) {
            throw new NullPointerException("Reservation details cannot be null");
        }
        
        // Extra check: Pehle dekho hotel available hai ya nahi
        if (available()) {
            this.reservations.add(res);
            System.out.println("✅ Reservation successfully created for Hotel: " + name);
        } else {
            System.out.println("❌ Failed: No rooms available in " + name);
        }
    }

    // Aggregation helper: Room add karne ke liye
    public void addRoom(Room room) {
        if (room != null && !rooms.contains(room)) {
            this.rooms.add(room);
        }
    }

    public String getName() {
        return name;
    }

    // Getter for reservations (Helper for testing)
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations); // Return copy for safety
    }
}