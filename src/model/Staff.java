package model;

public class Staff extends User{
    private int wage;


    public Staff(String username, String name, String email, String phoneNum, int wage, String password){
        super(username, name, email, phoneNum, password);
        this.wage = wage;
    }

    public Staff() {

    }



    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

}