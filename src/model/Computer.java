package model;



public class Computer {
    private int hostID;
    private String username;
    private String status;
    private Session session;
    private String ipAddress;
    private String config;

    private int costPerHour;
    public Computer(int hostID, String status) {
        this.hostID = hostID;
        this.status = status;
        this.session = null;
    }

    public Computer(int hostID, String status, String username) {
        this.hostID = hostID;
        this.status = status;
        this.username = username;
    }
    public Computer(int hostID, String username, String ipAddress, String config,int costPerHour, String status) {
        this.hostID = hostID;
        this.username = username;
        this.ipAddress = ipAddress;
        this.config = config;
        this.status = status;
        this.costPerHour = costPerHour;
    }

    public Computer() {

    }

    public int getHostID() {
        return hostID;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getConfig() {
        return config;
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;
    }
}