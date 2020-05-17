package View;

import Controller.BuyerManaging;
import Controller.CartManaging;
import Controller.GoodsManaging;
import View.FilterMenus.Filtering;
import View.FilterMenus.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseMenu extends Menu {
    enum menuType{
        CHOOSE,INCREASE,DECREASE
    }
    public PurchaseMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,showProducts());
        subMenus.put(2,showTotalPrice());
        subMenus.put(3,purchase());
        this.setSubMenus(subMenus);
    }
    private Menu showTotalPrice() {
        return new Menu("Show Total price",this) {
            menuType menuType;
            ArrayList<String> products  = CartManaging.showProductsFromCart();
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Total Price : "+CartManaging.showTotalPrice());
            }
        };
    }
    private Menu showProducts() {
        return new Menu("Show Products",this) {
            menuType menuType;
            ArrayList<String> products  = CartManaging.showProductsFromCart();
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("press + for increase type");
                System.out.println("press - for decrease type");
                System.out.println("press c for choose type");
                System.out.println("Available products : ");
                for (String s:
                        products) {
                    System.out.println(products.indexOf(s)+1+". "+s);
                }
            }
            @Override
            public void execute() throws ViewException {
                String line = ConsoleCmd.scanner.nextLine();
                if(line == "+")
                    menuType = PurchaseMenu.menuType.INCREASE;
                else if(line == "-")
                    menuType = PurchaseMenu.menuType.DECREASE;
                else if(line=="c")
                    menuType = PurchaseMenu.menuType.CHOOSE;
                else {
                    int menuChanger = Integer.parseInt(line);
                    if (menuChanger == 0)
                        this.parentMenu.run();
                    else {
                        if(menuType == PurchaseMenu.menuType.CHOOSE)
                        new ProductMenu(this, products.get(menuChanger));
                        else if(menuType == PurchaseMenu.menuType.INCREASE)
                            CartManaging.increaseProductNumberInCart(products.get(menuChanger));
                        else
                            CartManaging.decreaseProductNumberInCart(products.get(menuChanger));
                    }
                }
            }
        };
    }
    private Menu purchase() {
        return new Menu("Purchase",this) {
            @Override
            public void run() throws ViewException {
                receiverInformation().run();
                discountCode().run();
                payment().run();
            }
        };
    }
    private Menu receiverInformation() {
        return new Menu("log info",this) {
            @Override
            public void run() {
                System.out.println(this.getName());
                String address;
                String phoneNumber;
                System.out.println("Enter your Address : ");
                //??
                ConsoleCmd.scanner.next();
                address = ConsoleCmd.scanner.nextLine();
                System.out.println("Enter your phoneNumber : ");
                phoneNumber = ConsoleCmd.scanner.nextLine();
                CartManaging.receiveInformation(address,phoneNumber);
            }
        };
    }
    private Menu discountCode() {
        return new Menu("Discount code",this) {
            @Override
            public void run() throws ViewException {
                String discountCode;
                System.out.println("Enter your code(if you dont have code press enter) : ");
                discountCode = ConsoleCmd.scanner.nextLine();
                if(discountCode.equals("")) return;
                if(!CartManaging.addDiscountCode(discountCode))
                    System.out.println(ViewException.invalidDiscountCode().getMessage());
                    run();
            }
        };
    }
    private Menu payment() {
        return new Menu("payment",this) {
            @Override
            public void show() {
                System.out.println("0. reset information");
                System.out.println("1. cancel ");
                System.out.println("2. pay ");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0) purchase().run();
                else if (menuChanger==1) this.parentMenu.run();
                else if(menuChanger==2) {
                    if(!CartManaging.pay())
                        throw ViewException.insufficientFunds();
                    else{
                        ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,"paied successfully");
                        this.parentMenu.parentMenu.run();
                    }
                }
            }
        };
    }
}
