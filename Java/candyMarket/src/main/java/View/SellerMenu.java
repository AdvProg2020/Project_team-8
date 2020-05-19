package View;

import Controller.ManagerManaging;
import Controller.SellerManaging;
import Controller.UserManaging;
import Model.Seller;

import javax.swing.text.View;
import java.beans.XMLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SellerMenu extends Menu {

    public SellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, viewPersonalInfo());
        subMenus.put(2, viewCompanyInfo());
        subMenus.put(3, viewSalesHistory());
        subMenus.put(4, manageProducts());
        subMenus.put(5, addProduct());
        subMenus.put(6, removeProduct());
        subMenus.put(7, showCategories());
        subMenus.put(8, viewOffs());
        subMenus.put(9, viewBalance());
        subMenus.put(10, logout());
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
                            SellerManaging.editFieldOfInfo("firstName", newField);
                            this.run();
                            break;
                        case 2:
                            SellerManaging.editFieldOfInfo("lastName", newField);
                            this.run();
                            break;
                        case 3:
                            SellerManaging.editFieldOfInfo("email", newField);
                            this.run();
                            break;
                        case 4:
                            SellerManaging.editFieldOfInfo("telephoneNumber", newField);
                            this.run();
                            break;
                        case 5:
                            SellerManaging.editFieldOfInfo("password", newField);
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

    private Menu viewCompanyInfo() {
        return new Menu("View Company Information", this) {
            @Override
            public void show() {
                System.out.println("Your company information:");
                String info = SellerManaging.viewCompaniesInfo();
                System.out.println(info);
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 0)
                    this.parentMenu.run();
                else
                    try{
                        throw ViewException.invalidNumber();
                    }catch (ViewException e) {
                        System.out.println(ViewException.invalidNumber().getMessage());
                        this.run();
                    }
            }
        };
    }

    private Menu viewSalesHistory() {
        return new Menu("View Sales History", this) {
            @Override
            public void show() {
                System.out.println("Your sales history:");
                ArrayList<String> info = SellerManaging.viewSalesHistory();
                if (info  != null)
                    for (String o : info) {
                        System.out.println(o);
                    }
                else System.out.println("null");
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 0)
                    this.parentMenu.run();
                else
                    try{
                        throw ViewException.invalidNumber();
                    }catch (ViewException e) {
                        System.out.println(ViewException.invalidNumber().getMessage());
                        this.run();
                    }
            }
        };
    }

    private Menu manageProducts() {
        return new Menu("Manage Info", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                HashMap<Integer, String> info = SellerManaging.manageProducts();
                if (info != null) {
                    System.out.println(info);
                }
                else
                    System.out.println("null");
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                System.out.println("1. view Product\n" +
                        "2. view buyers\n" +
                        "3. edit");
                int menuChanger = ConsoleCmd.scanner.nextInt();
                int id;
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter ID of the Product:");
                        id = ConsoleCmd.scanner.nextInt();
                        System.out.println(SellerManaging.viewProduct(id));
                        this.run();
                    case 2 :
                        System.out.println("Enter ID of the product:");
                        id = ConsoleCmd.scanner.nextInt();
                        System.out.println(SellerManaging.viewBuyersOfProduct(id));
                        this.run();
                    case 3 :
                        System.out.println("Enter ID of the product:");
                        id = ConsoleCmd.scanner.nextInt();
                        System.out.println(SellerManaging.editProduct(id));
                        this.run();
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

    private Menu addProduct() {
        return new Menu("Add Product", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                System.out.println("Enter the name of product :");
                ConsoleCmd.scanner.nextLine();
                String name = ConsoleCmd.scanner.nextLine();
                if (name.charAt(0) == '0' && name.length() == 1)
                    this.parentMenu.run();
                System.out.println("Enter brand of the product :");
                String brand = ConsoleCmd.scanner.nextLine();
                System.out.println("Enter price for your product :");
                int price = ConsoleCmd.scanner.nextInt();
                System.out.println("Enter stock of your product :");
                int stock = ConsoleCmd.scanner.nextInt();
                System.out.println("Enter the category it belongs :");
                ConsoleCmd.scanner.nextLine();
                String category = ConsoleCmd.scanner.nextLine();
                while (!ManagerManaging.isThereSuchCategory(category)) {
                    try {
                        throw ViewException.notExistingCategory();
                    }catch (ViewException e) {
                        System.out.println(ViewException.notExistingCategory().getMessage());
                    }
                    category = ConsoleCmd.scanner.nextLine();
                }
                System.out.println("Enter special attributes");
                String categorySpecialAttributes = ConsoleCmd.scanner.nextLine();
                System.out.println("Enter details of the product");
                String detailInfo = ConsoleCmd.scanner.nextLine();
//                ArrayList<String> newProduct = new ArrayList<String>();
                SellerManaging.addProduct(name, brand, price, stock, category, categorySpecialAttributes, detailInfo);
                System.out.println("Product added successfully");
                this.parentMenu.run();
            }
        };
    }

    private Menu removeProduct() {
        return new Menu("Remove Product", this) {
            @Override
            public void show() {
                System.out.println("0. back");
                System.out.println("Enter a Product ID :");
            }

            @Override
            public void execute() throws ViewException {
                int id = ConsoleCmd.scanner.nextInt();
                if (id == 0) {
                    this.parentMenu.run();
                }
                else {
                    System.out.println(SellerManaging.removeProduct(id));
                    this.run();
                }
            }
        };
    }

    private Menu showCategories() {
        return new Menu("Show Categories", this) {
            @Override
            public void show() {
                System.out.println("0. back");
                System.out.println(SellerManaging.showCategories());
            }

            @Override
            public void execute() throws ViewException {
                super.execute();
            }
        };
    }

    private Menu viewOffs() {
        return new Menu("View Offs", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                HashMap<Integer, String> info = SellerManaging.viewOffs();
                if (info != null) {
                    System.out.println(info);
                }
                else
                    System.out.println("null");
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                System.out.println("1. view Off\n" +
                        "2. edit \n" +
                        "3. add off");
                int menuChanger = ConsoleCmd.scanner.nextInt();
                int id;
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter ID of the Product:");
                        id = ConsoleCmd.scanner.nextInt();
                        System.out.println(SellerManaging.viewOff(id));
                        this.run();
                    case 2 :
                        System.out.println("Enter ID of the product:");
                        id = ConsoleCmd.scanner.nextInt();
                        System.out.println(SellerManaging.editOff(id));
                        this.run();
                    case 3 :
                        System.out.println("enter fields of off");
                        ConsoleCmd.scanner.nextLine();
                        String command = ConsoleCmd.scanner.nextLine();
                        ArrayList<String> newOff = new ArrayList<String>();
                        if (command.charAt(0) == '0') {
                            this.parentMenu.run();
                        }
                        else {
                            newOff.add(command);
                        }
                        System.out.println(SellerManaging.addOff(newOff));
                        this.parentMenu.run();
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

    private Menu viewBalance() {
        return new Menu("View Balance", this) {
            @Override
            public void show() {
                System.out.println("0. back");
                System.out.println(SellerManaging.viewBalance());
            }

            @Override
            public void execute() throws ViewException {
                super.execute();
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
                        SellerManaging.logout();
                        System.out.println("Logged out Successfully");
                        user = LoginType.DEFAULT;
                        this.parentMenu.parentMenu.parentMenu.subMenus.get(1).run();
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


