public class GuestTest {
    public static void main(String[] args) {
        testGuestConstructor();
        testGuestStaticCreate();
    }

    public static void testGuestConstructor() {
        System.out.println("Running: testGuestConstructor...");
        
        // 1. Arrange
        String expectedName = "Iqrar";
        String expectedEmail = "hassan@example.com";

        // 2. Act
        Guest guest = new Guest(expectedName, expectedEmail);

        // 3. Assert
        if (expectedName.equals(guest.getName()) && expectedEmail.equals(guest.getAddressDetails())) {
            System.out.println("✅ Passed: Constructor works correctly!");
        } else {
            System.err.println("❌ Failed: Expected " + expectedName + " but got " + guest.getName());
        }
        System.out.println("-----------------------------------");
    }

    public static void testGuestStaticCreate() {
        System.out.println("Running: testGuestStaticCreate...");

        // 1. Arrange
        String name = "Ali";
        String email = "ali@example.com";

        // 2. Act
        // Agar aapne pichle code mein 'create' ko static banaya hai:
        Guest guest = Guest.create(name, email);

        // 3. Assert
        if (guest != null && name.equals(guest.getName())) {
            System.out.println("✅ Passed: Create method works!");
        } else {
            System.err.println("❌ Failed: Create method returned incorrect data.");
        }
    }
}