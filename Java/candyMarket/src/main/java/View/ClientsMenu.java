package View;

import Controller.BuyerManaging;
import Controller.ManagerManaging;
import Controller.SellerManaging;
import Controller.UserManaging;
import Model.User;

import java.util.HashMap;

public class ClientsMenu extends Menu {
    private HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();

    public ClientsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        subMenus.put(1,new ManagerMenu("", this));
        subMenus.put(2,new SellerMenu("", this));
        subMenus.put(3,new BuyerMenu("", this));
        this.setSubMenus(subMenus);
    }

    @Override
    public void run() throws ViewException {
        if (user == LoginType.DEFAULT)
            this.parentMenu.subMenus.get(1).run();
        if (user == LoginType.MANAGER)
            this.subMenus.get(1).run();
        if (user == LoginType.SELLER)
            this.subMenus.get(2).run();
        if (user == LoginType.BUYER)
            this.subMenus.get(3).run();
    }

    //Manager

        private Menu viewManagersPersonalInfo () {
            String personalInfo = ManagerManaging.showPersonalInfo();
            return null;
        }

        private Menu manageUsers () {
            return null;
        }

        private Menu manageAllProducts () {
            return null;
        }

        private Menu createDiscountCode () {
            return null;
        }

        private Menu viewAllDiscountCodes () {
            return null;
        }

        private Menu manageRequests () {
            return null;
        }

        private Menu manageCategories () {
            return null;
        }

    //Seller


    private Menu viewCompaniesInfo() {
        return null;
    }

    private Menu viewSalesHistory() {
        return null;
    }

    private Menu manageProducts() {
        return null;
    }

    private Menu addProduct() {
        return null;
    }

    private Menu removeProduct() {
        return null;
    }

    private Menu showCategories() {
        return null;
    }

    private Menu viewOffs() {
        return null;
    }

    private Menu viewBalance() {
        return null;
    }


    //Buyer

    private Menu viewBuyersPersonalInfo() {
        String personalInfo = UserManaging.showPersonalInfo();
        return null;
    }

    private Menu viewCart() {
        return null;
    }

    private Menu viewOrders() {
        return null;
    }

    //private Menu viewBalance() {
    //        return null;
    //    }

    private Menu viewDiscountCodes() {
        return null;
    }
}
