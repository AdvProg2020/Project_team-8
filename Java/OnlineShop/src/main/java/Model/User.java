package Model;

import java.util.ArrayList;

public class User {
    enum UserType {
        BUYER, SELLER, MANAGER
    }
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String passWord;
    private int credit;
    protected UserType type;
    public static ArrayList<User> users;

    public User(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public UserType getType() {
        return type;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static boolean isThereUserWithUsername(String userName) {
        return true;
    }

    public static User getUserByUsername(String userName) {
        return null;
    }

    public ArrayList<String> viewUserPersonalInfo(User user) {
        return null;
    }

    public void editPersonalInfo(String field) {

    }


}
