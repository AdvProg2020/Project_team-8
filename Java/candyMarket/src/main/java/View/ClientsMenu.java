package View;

import Controller.BuyerManaging;
import Controller.ManagerManaging;
import Controller.SellerManaging;

public class ClientsMenu extends Menu {
    public ClientsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    //Manager

    private Menu viewManagersPersonalInfo() {
        String personalInfo = ManagerManaging.showPersonalInfo();
        return null;
    }

    private Menu manageUsers() {
        return  null;
    }

    private Menu manageAllProducts() {
        return null;
    }

    private Menu createDiscountCode() {
        return null;
    }

    private Menu viewAllDiscountCodes() {
        return null;
    }

    private Menu manageRequests() {
        return null;
    }

    private Menu manageCategories() {
        return null;
    }

    //Seller

    private Menu viewSellersPersonalInfo() {
        String personalInfo = SellerManaging.viewPersonalInfo();
        return null;
    }

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
        String personalInfo = BuyerManaging.showPersonalInfo();
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
