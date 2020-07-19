package Model;

import javax.persistence.Entity;

@Entity
public class Supporter extends User {

    public static Supporter currentSupporter;
    public Supporter(){}
    public Supporter(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.SUPPORTER);
        ManageInfo.allSupporters.add(this);
        ManageInfo.allUsers.add(this);
    }

    public static void register(String userName, String firstName, String lastName, String email, String phone, String pass) {
        new Supporter(userName, firstName, lastName, email, phone, pass);
    }

}
