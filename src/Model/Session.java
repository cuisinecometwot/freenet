package Model;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Session {

    private Timestamp startTime;
    private Timestamp endTime;
    private String username;
    private int hourlyRate;
    private int totalCost;

    // Constructor with start time
    public Session(String usr, Timestamp startTime) {
        this.startTime = startTime;
        this.endTime = null; // End time initially unknown
        this.username = usr;
        this.hourlyRate = 10000; // default
        this.totalCost = 0;

    }

    public Session(String usr, int hourlyRate, Timestamp startTime) {
        this.startTime = startTime;
        this.endTime = null;
        this.username = usr;
        this.hourlyRate = 10000;
        if (hourlyRate > 0) this.hourlyRate = hourlyRate;
        this.totalCost = 0;
    }

    // Getters and Setters
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void addToTotalCost(int amount) {
        this.totalCost += amount;
    }

    public String getCustomerUsername() {
        return username;
    }

    public void setCustomerUsername(String username) {
        this.username = username;
    }

    // Method to calculate session duration (assuming endTime is set)
    public int calculateDurationInHours() {
        if (endTime == null) {
            throw new IllegalStateException("End time not set for this session.");
        }
        long differenceInMilliseconds = endTime.getTime() - startTime.getTime();
        return (int) Math.ceil(differenceInMilliseconds / (1000 * 60 * 60));
    }

    public int calculateSessionCost() {
        int durationInHours = calculateDurationInHours();
        return durationInHours * hourlyRate;
    }

    // Optional method to format start time for display (replace with your desired formatting)
    public String getFormattedStartTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(startTime);
    }
}