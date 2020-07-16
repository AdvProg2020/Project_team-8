package Server.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
@Entity
public class Manager extends User {
    public static Manager currentManager;
    public Manager(){}
    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.MANAGER);
        ManageInfo.allManagers.add(this);
        ManageInfo.allUsers.add(this);
    }
    public static boolean isThisTheFirstManager() {
        if(ManageInfo.allManagers.isEmpty())
            return true;
        return false;
    }
    public void removeCategory(Category category){
        for (Good good:
                ManageInfo.allGoods) {
            if(good.getCategory() == category) {
                Seller seller = good.getSeller();
                seller.removeProduct(good);
                ManageInfo.allGoods.remove(good);
            }
        }
        ManageInfo.allCategories.remove(category);
    }
    public static void register(String userName,String firstName,String lastName,String email,String phone,String pass){
        new Manager(userName,firstName,lastName,email,phone,pass);
    }
    public ArrayList<String> viewAllGoods() {
        return null;
    }
    public void removeGood(int goodId) {

    }

    public void createDiscountCode() {

    }

    private void setUsersWhoGetDiscount(Discount discount) {

    }


}