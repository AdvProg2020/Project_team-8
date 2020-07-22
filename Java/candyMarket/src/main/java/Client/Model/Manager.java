package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Manager extends User {
    public static Manager currentManager;
    public static int karmozd = 0;
    public static int minWalletMoney = 0;
    public Manager(){}
    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.MANAGER);
        Controller.saveOrUpdateObject(this);
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
    public List<String> viewAllGoods() {
        return null;
    }
    public void removeGood(int goodId) {

    }

    public void createDiscountCode() {

    }

    private void setUsersWhoGetDiscount(Discount discount) {

    }


}