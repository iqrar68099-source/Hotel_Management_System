public class ReserverPayer {
    private String id;
    private String creditCardDetails;
    
    // Relationship: ReserverPayer has a Reservation (1 to 0..1)
    private Reservation reservation;

    // Private constructor (Better for Factory pattern)
    private ReserverPayer(String id, String creditCardDetails) {
        this.id = id;
        this.creditCardDetails = creditCardDetails;
    }

    // UML Operation: create() 
    public static ReserverPayer create(String id, String creditCardDetails) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid ID provided");
        }
        // Basic card validation (Check length or empty)
        if (creditCardDetails == null || creditCardDetails.length() < 12) {
            throw new IllegalArgumentException("Invalid Credit Card details");
        }
        return new ReserverPayer(id, creditCardDetails);
    }

    // Security improvement: Poora card number kabhi return nahi karte
    public String getMaskedCardDetails() {
        if (creditCardDetails != null && creditCardDetails.length() > 4) {
            return "****-****-****-" + creditCardDetails.substring(creditCardDetails.length() - 4);
        }
        return "Invalid Card";
    }

    // UML Operation: makeReservation()
    public void makeReservation(Reservation res) {
        if (res == null) {
            System.err.println("Error: Cannot assign a null reservation.");
            return;
        }
        
        // Agar pehle se reservation hai to notify karein
        if (this.reservation != null) {
            System.out.println("Notice: Replacing existing reservation for Payer " + id);
        }

        this.reservation = res;
        System.out.println("✅ Success: Payer " + id + " has authorized the reservation using " + getMaskedCardDetails());
    }

    public String getId() { return id; }
    
    public String getCreditCardDetails() { return creditCardDetails; }

    public Reservation getReservation() { return reservation; }
}