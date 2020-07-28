package Server.Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User  {
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    public User(){}
    public enum UserType {
        BUYER, SELLER, MANAGER, SUPPORTER
    }
    @Transient
    protected Cart cart = new Cart();
    @Id
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    private UserType type;
    private String userPhoto;
    public User(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        this.username = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = passWord;
    }

    public static List<User> getUsers() {
        return ManageInfo.allUsers;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public static boolean isThereUserWithUsername(String userName) {
        for (User user : ManageInfo.allUsers) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    public static boolean isThereUserWithEmail(String email) {
        for (User user : ManageInfo.allUsers) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean isUsernameAndPasswordCorrect(String username, String passWord) {
        if (isThereUserWithUsername(username)) {
            for (User user : ManageInfo.allUsers) {
                if (user.getPassword().equals(passWord)) {
                    UserHandler.setCurrentUser(this);
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username) {
        for (User user : ManageInfo.allUsers) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public String viewUserPersonalInfo() {
        return "Username: " + this.getUsername() + "\n"
                + "Password: " + this.getPassword() + "\n"
                + "First name: " + this.getFirstName() + "\n"
                + "Last name: " + this.getLastName() + "\n"
                + "Email: " + this.getEmail() + "\n"
                + "Phone number:" + this.getPhoneNumber() + "\n";
    }

    public void editPersonalInfo(String toBeEditedField, String newField) {
        switch (toBeEditedField) {
            case "password":
                UserHandler.getCurrentUser().setPassword(newField);
                break;
            case "firstName":
                UserHandler.getCurrentUser().setFirstName(newField);
                break;
            case "lastName":
                UserHandler.getCurrentUser().setLastName(newField);
                break;
            case "email":
                UserHandler.getCurrentUser().setEmail(newField);
                break;
            case "phoneNumber":
                UserHandler.getCurrentUser().setPhoneNumber(newField);
                break;
        }
    }
    public boolean isManager(){
        return type == UserType.MANAGER;
    }
    public boolean isBuyer(){
        return type == UserType.BUYER;
    }
    public boolean isSeller(){
        return type == UserType.SELLER;
    }
    public boolean isSupporter(){
        return type == UserType.SUPPORTER;
    }
    public static void deleteUser(User user) {
        ManageInfo.allUsers.remove(user);
    }
}