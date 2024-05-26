package Model;



public class Computer {
    private String hostID;
    private String username;
    private String status;
    private Session session;

    public Computer(String hostID, String status) {
        this.hostID = hostID;
        this.status = status;
        this.session = null;
    }

    public Computer(String hostID, String status, String username) {
        this.hostID = hostID;
        this.status = status;
        this.username = username;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
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
}