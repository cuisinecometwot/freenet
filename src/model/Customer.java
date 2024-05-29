package model;

public class Customer extends User{
    private int balance;

    public Customer(String username, String name, String email, String phoneNum, int balance, String password){
        super(username, name, email, phoneNum, password);
        this.balance = balance;

    }

    public Customer() {

    }

    public void addBalance(int amount) {
        if (amount > 0) this.balance += amount;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
