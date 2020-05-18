package View;


import Controller.LoginOrRegisterManaging;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginOrRegister extends Menu {

    public LoginOrRegister(Menu parentMenu) {
        super("LoginOrRegister", parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,RegisterNewPerson());
        subMenus.put(2,Login());
        this.setSubMenus(subMenus);
    }

    @Override
    public void run() throws ViewException {
        if (!(user == LoginType.DEFAULT))
            this.parentMenu.subMenus.get(2).run();
        else
            super.run();
    }


    private Menu RegisterNewPerson() {
        return new Menu("Register", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                boolean checkForExistingManager = LoginOrRegisterManaging.isThisTheFirstManager();
                HashMap<String, String> info = new HashMap<String, String>();
                System.out.println("Enter A UserName :");
                ConsoleCmd.scanner.nextLine();
                String username = ConsoleCmd.scanner.nextLine();
                while (LoginOrRegisterManaging.isThereUsernameWithThisName(username)) {
                    try {
                        throw ViewException.existingUsername();
                    }catch (ViewException e) {
                        System.out.println(ViewException.existingUsername().getMessage());
                    }
                    username = ConsoleCmd.scanner.nextLine();
                }
                info.put("username", username.trim());
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
                        if (LoginOrRegisterManaging.isThereASameEmail(email))
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
                System.out.println("Enter the type of your account\n" +
                        "1. Buyer\n" +
                        "2. Seller\n" +
                        "3. Manager");
                Integer type = 4;
                while (type > 3 || type < 1) {
                    type = ConsoleCmd.scanner.nextInt();
                    switch (type) {
                        case 1:
                            info.put("type", "buyer");
                            break;
                        case 2:
                            info.put("type", "seller");
                            int workType = 0;
                            System.out.println("Enter a type :\n" +
                                    "1. company\n" +
                                    "2. factory\n" +
                                    "3. workshop");
                            workType = ConsoleCmd.scanner.nextInt();
                            while (workType < 1 || workType > 3) {
                                try{
                                    throw ViewException.invalidNumber();
                                }catch (ViewException e) {
                                    System.out.println(ViewException.invalidNumber().getMessage());
                                }
                                workType = ConsoleCmd.scanner.nextInt();
                            }
                            switch (workType) {
                                case 1 :
                                    info.put("workType", "company");
                                    break;
                                case 2 :
                                    info.put("workType", "factory");
                                    break;
                                case 3 :
                                    info.put("workType", "workshop");
                                    break;
                            }
                            System.out.println("Enter your workplace name :");
                            ConsoleCmd.scanner.nextLine();
                            String companyName = ConsoleCmd.scanner.nextLine();
                            info.put("companyName", companyName);
                            break;
                        case 3:
                            if (checkForExistingManager)
                                info.put("type", "manager");
                            else {
                                try {
                                    throw ViewException.existingManager();
                                }catch (ViewException e) {
                                    System.out.println(ViewException.existingManager().getMessage());
                                    type = 4;
                                }
                            }
                            break;
                        default:
                            try {
                                throw ViewException.invalidNumber();
                            } catch (ViewException e) {
                                System.out.println(ViewException.invalidNumber().getMessage());
                            }
                    }
                }
                LoginOrRegisterManaging.register(info);
                System.out.println("Registered Successfully");
                switch (type) {
                    case 1 :
                        user = LoginType.BUYER;
                        break;
                    case 2 :
                        user = LoginType.SELLER;
                        break;
                    case 3 :
                        user = LoginType.MANAGER;
                        break;
                }
                this.parentMenu.run();
            }
        };
    }

    private Menu Login() {
        return new Menu("Login", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
            }

            @Override
            public void execute() throws ViewException {
                System.out.println("Enter A UserName :");
                ConsoleCmd.scanner.nextLine();
                String username = ConsoleCmd.scanner.nextLine().trim();
                System.out.println("Enter your Password :");
                String password = ConsoleCmd.scanner.nextLine();
                int type = 0;
                while ((type = LoginOrRegisterManaging.login(username, password)) == 4) {
                    try {
                        throw ViewException.invalidLogin();
                    }catch (ViewException e) {
                        System.out.println(ViewException.invalidLogin().getMessage());
                    }
                    System.out.println("Enter A UserName :");
                    username = ConsoleCmd.scanner.nextLine().trim();
                    System.out.println("Enter your Password :");
                    password = ConsoleCmd.scanner.nextLine();
                }
                switch (type) {
                    case 1 :
                        user = LoginType.BUYER;
                        break;
                    case 2 :
                        user = LoginType.SELLER;
                        break;
                    case 3 :
                        user = LoginType.MANAGER;
                        break;
                }
                System.out.println("Login Successfully");
                this.parentMenu.run();
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
