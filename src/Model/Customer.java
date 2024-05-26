package Model;

public class Customer {
    private String name;
    private String email;
    private String phoneNum;
    private int balance;
    private String username;
    private String password;

    public Customer(String username, String name, String email, String phoneNum, int balance, String password){
        this.balance = balance;
        this.name = name;
        this.username = username;
        this. password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Customer(String username, String name, int balance){
        this.username = username;
        this.name = name;
        this.balance = 0;
        if (balance > 0) this.balance = balance;
    }
    public void addBalance(int amount) {
        if (amount > 0) this.balance += amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
