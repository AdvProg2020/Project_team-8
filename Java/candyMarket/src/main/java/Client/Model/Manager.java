package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;
import Client.DataHandler.MessageHandler;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Manager extends User {
    public static Manager currentManager;
    private int wage = 0;
    private int minWalletMoney = 0;
    private String bankAccountNumber;
    private String token;
    private String tokenEndTime;
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
        String accNum;
        if(Manager.isThisTheFirstManager()){
            String inputs[] = MessageHandler.createBankAccount(userName,pass,firstName,lastName).split("#");
            accNum = inputs[1];
        }else accNum = ManageInfo.allManagers.get(0).getBankAccountNumber();
        Manager manager = new Manager(userName,firstName,lastName,email,phone,pass);
        manager.setBankAccountNumber(accNum);
        Controller.saveOrUpdateObject(manager);
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

    public int getMinWalletMoney() {
        return minWalletMoney;
    }

    public void setMinWalletMoney(int minWalletMoney) {
        this.minWalletMoney = minWalletMoney;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}