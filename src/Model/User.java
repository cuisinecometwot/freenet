package Model;

public abstract class User {
     String name;
     String email;
     String phoneNum;
     String username;
     String password;

     public User(){}
    public User(String username, String name, String password) {
        this.name = name;
        this.username = username;
        this. password = password;
    }
    public User(String username, String name, String email, String phoneNum, String password){
        this.name = name;
        this.username = username;
        this. password = password;
        this.email = email;
        this.phoneNum = phoneNum;
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
