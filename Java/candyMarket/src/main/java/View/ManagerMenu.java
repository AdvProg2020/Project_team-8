package View;

import Controller.LoginOrRegisterManaging;
import Controller.ManagerManaging;
import Controller.SellerManaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu{

    public ManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, viewPersonalInfo());
        subMenus.put(2, manageUsers());
        subMenus.put(3, manageProducts());
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
                String info = ManagerManaging.showPersonalInfo();
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
                            System.out.println(ManagerManaging.editAFieldOfOfInfo("firstName", newField));
                            this.run();
                            break;
                        case 2:
                            System.out.println(ManagerManaging.editAFieldOfOfInfo("lastName", newField));
                            this.run();
                            break;
                        case 3:
                            System.out.println(ManagerManaging.editAFieldOfOfInfo("email", newField));
                            this.run();
                            break;
                        case 4:
                            System.out.println(ManagerManaging.editAFieldOfOfInfo("telephoneNumber", newField));
                            this.run();
                            break;
                        case 5:
                            System.out.println(ManagerManaging.editAFieldOfOfInfo("password", newField));
                            this.run();
                            break;
                        default:
                            try{
                                throw ViewException.invalidNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidNumber().getMessage());
                            }
                    }
                } else if (menuChanger == 0)
                    this.parentMenu.run();
            }
        };
    }

    private Menu manageUsers() {
        return new Menu("Manage Users", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                ArrayList<String> allUsers = ManagerManaging.manageUsers();
                System.out.println(allUsers);
            }

            @Override
            public void execute() throws ViewException {
                System.out.println("1. view user\n" +
                        "2. delete user\n" +
                        "3. create manager profile");
                int menuChanger = ConsoleCmd.scanner.nextInt();
                String username;
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter Username :");
                        username = ConsoleCmd.scanner.nextLine();
                        System.out.println(ManagerManaging.viewUser(username));
                        this.run();
                        break;
                    case 2 :
                        System.out.println("Enter Username :");
                        username = ConsoleCmd.scanner.nextLine();
                        System.out.println(ManagerManaging.deleteUser(username));
                        this.run();
                        break;
                    case 3 :
                        HashMap<String, String> info = new HashMap<>();
                        System.out.println("Enter A UserName :");
                        ConsoleCmd.scanner.nextLine();
                        username = ConsoleCmd.scanner.nextLine();
                        while (LoginOrRegisterManaging.isThereUsernameWithThisName(username)) {
                            try {
                                throw ViewException.existingUsername();
                            }catch (ViewException e) {
                                System.out.println(ViewException.existingUsername().getMessage());
                            }
                            username = ConsoleCmd.scanner.nextLine();
                        }
                        info.put("username", username);
                        System.out.println("Enter your Password :");
                        String password = ConsoleCmd.scanner.nextLine();
                        info.put("password", password);
                        System.out.println("Enter your Name :");
                        String name = ConsoleCmd.scanner.nextLine();
                        info.put("firstName", name);
                        System.out.println("Enter your LastName :");
                        String lastName = ConsoleCmd.scanner.nextLine();
                        info.put("lastName", lastName);
                        System.out.println("Enter your Email :");
                        String email = ConsoleCmd.scanner.nextLine();
                        while (!emailValidation(email) || LoginOrRegisterManaging.isThereASameEmail(email)) {
                            try {
                                throw ViewException.invalidEmailFormat();
                            }catch (ViewException e) {
                                if (!emailValidation(email))
                                    System.out.println(ViewException.invalidEmailFormat().getMessage());
                                if (LoginOrRegisterManaging.isThereUsernameWithThisName(email))
                                    System.out.println(ViewException.existingEmail().getMessage());
                            }
                            email = ConsoleCmd.scanner.nextLine();
                        }
                        info.put("email", email);
                        System.out.println("Enter your phone number :");
                        String phoneNumber = ConsoleCmd.scanner.nextLine();
                        while (!phoneValidation(phoneNumber)) {
                            try {
                                throw ViewException.invalidPhoneNumberFormat();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidPhoneNumberFormat().getMessage());
                            }
                            phoneNumber = ConsoleCmd.scanner.nextLine();
                        }
                        info.put("phoneNumber", phoneNumber);
                        info.put("type", "Manager");
                        LoginOrRegisterManaging.register(info);
                        System.out.println("Registered Successfully");
                        this.run();
                        break;
                    default :
                        try {
                            throw ViewException.invalidNumber();
                        }catch (ViewException e) {
                            System.out.println(ViewException.invalidNumber().getMessage());
                        }
                        this.run();
                }
            }
        };
    }

    private Menu manageProducts() {
        return new Menu("Manage All Products", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println(ManagerManaging.manageAllProducts());
                System.out.println("0. back");
                System.out.println("1. remove product");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                switch (menuChanger) {
                    case 0 :

                }
            }
        };
    }

    private boolean emailValidation(String email) {
        String mailRegex = "^\\S+@\\w+\\.com$";
        Pattern mailPattern = Pattern.compile(mailRegex);
        return email.matches(mailRegex);
    }

    private boolean phoneValidation(String phone) {
        String phoneRegex = "^\\+?\\d+$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        return phone.matches(phoneRegex);
    }
}
