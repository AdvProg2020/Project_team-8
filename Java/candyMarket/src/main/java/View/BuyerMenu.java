package View;

import Controller.BuyerManaging;
import Controller.ManagerManaging;
import Controller.SellerManaging;
import Controller.UserManaging;
import Model.Buyer;
import Model.Discount;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.HashMap;

public class BuyerMenu extends Menu {
    public BuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer, Menu> subMenus = new HashMap<>();
        subMenus.put(1, viewPersonalInfo());
        subMenus.put(2, new PurchaseMenu("Cart Menu", this));
        subMenus.put(3, viewOrders());
        subMenus.put(4, rateProducts());
        subMenus.put(5, viewBalance());
        subMenus.put(6, viewDiscountCodes());
        subMenus.put(7, logout());
        this.setSubMenus(subMenus);
    }

    @Override
    public void execute() throws ViewException {
        Menu nextMenu = null;
        int menuChanger = ConsoleCmd.scanner.nextInt();
        if (menuChanger == 0)
        {
            if (this.parentMenu==null)
                System.exit(1);
            else
                nextMenu=this.parentMenu.parentMenu;
        }
        else{
            nextMenu = subMenus.get(menuChanger);
        }
        nextMenu.run();
    }

    private Menu viewPersonalInfo() {
        return new Menu("View Personal Info", this) {
            @Override
            public void show() {
                System.out.println("Your personal information:");
                String info = UserManaging.showPersonalInfo();
                System.out.println(info);
                System.out.println("0. back");
                System.out.println("1. edit");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 1) {
                    System.out.println("Which field do you want to edit:");
                    System.out.println("0. back");
                    System.out.println("1. First name");
                    System.out.println("2. Last name");
                    System.out.println("3. Email");
                    System.out.println("4. Telephone number");
                    System.out.println("5. Password");
                    int selectedFieldToBeEdited = ConsoleCmd.scanner.nextInt();
                    String newField = "";
                    if (selectedFieldToBeEdited != 0 && selectedFieldToBeEdited < 6) {
                        System.out.println("Please enter your new field:");
                        ConsoleCmd.scanner.nextLine();
                        newField = ConsoleCmd.scanner.nextLine();
                    }
                    else if (selectedFieldToBeEdited == 0) {
                        this.run();
                    }
                    switch (selectedFieldToBeEdited) {
                        case 1:
                            BuyerManaging.editAFieldOfOfInfo("firstName", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 2:
                            BuyerManaging.editAFieldOfOfInfo("lastName", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 3:
                            BuyerManaging.editAFieldOfOfInfo("email", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 4:
                            BuyerManaging.editAFieldOfOfInfo("phoneNumber", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 5:
                            BuyerManaging.editAFieldOfOfInfo("password", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        default:
                            try{
                                throw ViewException.invalidNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidNumber().getMessage());
                            }
                            this.run();
                    }
                } else if (menuChanger == 0)
                    this.parentMenu.run();
            }
        };
    }

    private Menu viewOrders() {
        return new Menu("View Orders", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println(ConsoleDesign.divider);
                System.out.println(BuyerManaging.viewOrders());
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else {
                    String order = BuyerManaging.ShowOrder(menuChanger);
                    if(order == null) throw ViewException.invalidNumber();
                    viewOrder(order).run();
                }
                /*switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter a product ID :");
                        int id = ConsoleCmd.scanner.nextInt();
                        while (!BuyerManaging.isBuyerBoughtProductWithId(id)) {
                            try {
                                throw ViewException.invalidIDNumberForBuyer();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumberForBuyer().getMessage());
                            }
                            id =  ConsoleCmd.scanner.nextInt();
                        }
                        System.out.println(BuyerManaging.showOrder(id));
                        this.run();
                        break;
                    case 2 :
                        System.out.println("Enter a product ID :");
                        id = ConsoleCmd.scanner.nextInt();
                        while (!BuyerManaging.isBuyerBoughtProductWithId(id)) {
                            try {
                                throw ViewException.invalidIDNumberForBuyer();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumberForBuyer().getMessage());
                            }
                            id =  ConsoleCmd.scanner.nextInt();
                        }
                        System.out.println("Choose a number between 1 - 5 for rating :");
                        int score = ConsoleCmd.scanner.nextInt();
                        while (score < 1 || score > 5) {
                            try {
                                throw ViewException.invalidScoreNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidScoreNumber().getMessage());
                            }
                            score = ConsoleCmd.scanner.nextInt();
                        }
                        BuyerManaging.rateProduct(id, score);
                        System.out.println("You have rated item #" + id + " : " + score);
                        this.run();
                        break;
                    default :
                        try {
                            throw ViewException.invalidNumber();
                        }catch (ViewException e) {
                            System.out.println(ViewException.invalidNumber().getMessage());
                        }
                        this.run();
                }*/
            }
        };
    }
    private Menu viewOrder(String order) {
        return new Menu("View Order", this) {
            @Override
            public void show() {
                System.out.println(order);
                System.out.println("0. back");
            }
        };
    }
    private Menu rateProducts(){
        return new Menu("Rate Products",this) {
            ArrayList<String> products;
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                products = BuyerManaging.showBoughtProducts();
                for (String s:
                     products) {
                    System.out.println(products.indexOf(s)+1+" : "+s);
                }
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else if (menuChanger>products.size()) throw ViewException.invalidNumber();
                else {
                    System.out.println("please rate from 1 to 5");
                    int score = ConsoleCmd.scanner.nextInt();
                    if(score>5 || score<1)
                        throw ViewException.invalidScoreNumber();
                    BuyerManaging.rateProduct(products.get(menuChanger-1),score);
                    ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,"you rated successfully");
                    this.parentMenu.run();
                }
            }
        };
    }
    private Menu viewBalance() {
        return new Menu("View Balance", this) {
            @Override
            public void show() {
                System.out.println(BuyerManaging.viewBalance());
                System.out.println("0. back");
            }
        };
    }

    private Menu viewDiscountCodes() {
        return new Menu("View Discount Codes", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println(BuyerManaging.viewDiscountCodes());
                System.out.println("0. back");
            }
        };
    }

    private Menu logout() {
        return new Menu("logout", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("1. continue logging out");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        UserManaging.logout();
                        System.out.println("Logged out Successfully");
                        user = LoginType.DEFAULT;
                        this.parentMenu.parentMenu.parentMenu.run();
                        break;
                    default :
                        try{
                            throw ViewException.invalidNumber();
                        }catch (ViewException e) {
                            System.out.println(ViewException.invalidNumber().getMessage());
                            this.run();
                        }
                }
            }
        };
    }
}
