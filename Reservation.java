import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private int number; 
    
    private RoomType roomType; 
    private List<Room> rooms;

    private Reservation(Date start, Date end, RoomType type, int num) {
        // Current time minus 1 minute (Grace period to avoid microsecond mismatch)
        long bufferTime = System.currentTimeMillis() - 60000;
        Date todayWithBuffer = new Date(bufferTime);

        // Validation logic fixed
        if (start.before(todayWithBuffer)) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        if (end.before(start)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("Number of rooms must be at least 1");
        }
        
        this.reservationDate = new Date(); // Actual creation time
        this.startDate = start;
        this.endDate = end;
        this.number = num;
        this.roomType = type;
        this.rooms = new ArrayList<>();
    }

    public Reservation(long id) {
        this.reservationDate = new Date();
        this.rooms = new ArrayList<>();
    }

    public static Reservation create(Date start, Date end, RoomType type, int num) {
        return new Reservation(start, end, type, num);
    }

    public void addRoom(Room room) {
        if (room != null) {
            if (rooms.size() < number) {
                this.rooms.add(room);
            } else {
                System.out.println("Limit reached: Cannot add more rooms than reserved.");
            }
        }
    }

    public boolean isFullyAssigned() {
        return rooms.size() == number;
    }

    public int getNumber() { return number; }
    public RoomType getRoomType() { return roomType; }
}