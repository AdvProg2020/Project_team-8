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
    public static ArrayList<User> users = ManageInfo.allUsers;
    public static User currentUser;

    public User(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.type = type;
        users.add(this);
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

    public static boolean isThereUserWithUsername(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName))
                return true;
        }
        return false;
    }

    public static boolean isThereUserWithEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean isUsernameAndPasswordCorrect(String username, String passWord) {
        if (isThereUserWithUsername(username)) {
            for (User user : users) {
                if (user.getPassWord().equals(passWord)) {
                    currentUser = this;
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }

    public static User getUserByUsername(String userName) {
        return null;
    }

    public String viewUserPersonalInfo(User user) {
        return "Username: " + user.getUserName() + "\n"
                + "Password: " + user.getPassWord() + "\n"
                + "First name: " + user.getFirstName() + "\n"
                + "Last name: " + user.getLastName() + "\n"
                + "Email: " + user.getEmail() + "\n"
                + "Phone number" + user.getPhoneNumber() + "\n";
    }

    public void editPersonalInfo(String toBeEditedField, String newField) {
        switch (toBeEditedField) {
            case "password":
                currentUser.setPassWord(newField);
                break;
            case "firstName":
                currentUser.setFirstName(newField);
                break;
            case "lastName":
                currentUser.setLastName(newField);
                break;
            case "email":
                currentUser.setEmail(newField);
                break;
            case "phoneNumber":
                currentUser.setPhoneNumber(newField);
                break;
        }
    }


}
