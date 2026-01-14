public class Room {
    private int number;
    private RoomType type; 
    private boolean isOccupied; // Room ka status track karne ke liye
    private Guest currentGuest; // Room ke andar guest ka reference

    public Room(int number, RoomType type) {
        if (number <= 0) {
            throw new IllegalArgumentException("Invalid room number: Must be positive");
        }
        if (type == null) {
            throw new NullPointerException("RoomType cannot be null");
        }
        this.number = number;
        this.type = type;
        this.isOccupied = false; // By default room khali hoga
    }

    // UML Operation: createGuest() - Logic ko thoda expand kiya gaya hai
    public void createGuest(String name, String address) {
        if (isOccupied) {
            System.out.println("Room " + number + " is already occupied by another guest.");
            return;
        }
        
        // Guest class ka object create karke room se link karna
        this.currentGuest = new Guest(name, address);
        this.isOccupied = true;
        
        System.out.println("✅ Success: Guest '" + name + "' has been assigned to Room #" + number);
    }

    // UML Operation: Room availability check karne ke liye helper
    public boolean isAvailable() {
        return !isOccupied;
    }

    // Room khali karne ke liye method
    public void checkout() {
        this.currentGuest = null;
        this.isOccupied = false;
        System.out.println("Room #" + number + " is now vacant.");
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }
}