package View;

import Controller.SellerManaging;
import Model.Seller;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.text.View;
import java.beans.XMLEncoder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SellerMenu extends Menu {

    public SellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        subMenus = new HashMap<>();
        subMenus.put(1, viewPersonalInfo());
    }

    private Menu viewPersonalInfo() {
        return new Menu("View Personal Info", this) {
            @Override
            public void show() {
                System.out.println("Your personal information:");
                String info = SellerManaging.viewPersonalInfo();
                System.out.println(info);
                System.out.println("0. back");
                System.out.println("1. edit");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 1) {
                    System.out.println("Which field do you want to edit:");
                    System.out.println("1. First name");
                    System.out.println("2. Last name");
                    System.out.println("3. Email");
                    System.out.println("4. Telephone number");
                    System.out.println("5. Password");
                    int selectedFieldToBeEdited = ConsoleCmd.scanner.nextInt();
                    System.out.println("Please enter your new field:");
                    String newField = ConsoleCmd.scanner.nextLine();
                    switch (selectedFieldToBeEdited) {
                        case 1:
                            SellerManaging.editFieldOfInfo("firstName", newField);
                            break;
                        case 2:
                            SellerManaging.editFieldOfInfo("lastName", newField);
                            break;
                        case 3:
                            SellerManaging.editFieldOfInfo("email", newField);
                            break;
                        case 4:
                            SellerManaging.editFieldOfInfo("telephoneNumber", newField);
                            break;
                        case 5:
                            SellerManaging.editFieldOfInfo("password", newField);
                            break;
                        default:
                            throw ViewException.invalidNumber();
                    }
                } else
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
                    throw ViewException.invalidNumber();
            }
        };
    }

    private Menu viewSalesHistory() {
        return new Menu("View Sales History", this) {
            @Override
            public void show() {
                System.out.println("Your sales history:");
                ArrayList info = SellerManaging.viewSalesHistory();
                for (Object o : info) {
                    System.out.println(o);
                }
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 0)
                    this.parentMenu.run();
                else
                    throw ViewException.invalidNumber();
            }
        };
    }

}
