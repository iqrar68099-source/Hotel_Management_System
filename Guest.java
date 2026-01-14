public class Guest {
    private String name;
    private String addressDetails;

    // Default Constructor
    public Guest() {
    }

    // Parameterized Constructor
    public Guest(String name, String addressDetails) {
        // Validation check: Agar name null na ho tabhi assign karein
        this.name = (name != null) ? name : "Unknown";
        this.addressDetails = (addressDetails != null) ? addressDetails : "Not Provided";
    }

    public String getName() {
        return name;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    // Is method ko 'static' banaya hai taaki bina purane object ke naya Guest ban sake
    public static Guest create(String name, String addressDetails) {
        // Yahan aap naya object return karne se pehle extra logic add kar sakte hain
        if (name == null || name.isEmpty()) {
            System.out.println("Warning: Guest name is empty!");
        }
        return new Guest(name, addressDetails);
    }
    
    // Ek extra method (optional) jo data print karne mein kaam aaye
    @Override
    public String toString() {
        return "Guest [Name=" + name + ", Address=" + addressDetails + "]";
    }
}