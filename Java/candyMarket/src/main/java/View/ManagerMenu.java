package View;

import Controller.LoginOrRegisterManaging;
import Controller.ManagerManaging;
import Controller.SellerManaging;
import Model.Good;
import Model.Manager;
import Model.Seller;

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
        subMenus.put(4, createDiscountCode());
        subMenus.put(5, viewDiscountCode());
        subMenus.put(6, manageRequests());
        subMenus.put(7, manageCategories());
        subMenus.put(8, logout());
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
                            ManagerManaging.editAFieldOfOfInfo("firstName", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 2:
                            ManagerManaging.editAFieldOfOfInfo("lastName", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 3:
                            ManagerManaging.editAFieldOfOfInfo("email", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 4:
                            ManagerManaging.editAFieldOfOfInfo("phoneNumber", newField);
                            System.out.println("Edited Successfully");
                            this.run();
                            break;
                        case 5:
                            ManagerManaging.editAFieldOfOfInfo("password", newField);
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

    private Menu manageUsers() {
        return new Menu("Manage Users", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                ArrayList<String> allUsers = ManagerManaging.showAllUsers();
                System.out.println(allUsers);
                System.out.println("0. back");
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
                        ConsoleCmd.scanner.nextLine();
                        username = ConsoleCmd.scanner.nextLine();
                        while (!LoginOrRegisterManaging.isThereUsernameWithThisName(username)) {
                            try {
                                throw ViewException.notExistingUsername();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingUsername().getMessage());
                            }
                            username = ConsoleCmd.scanner.nextLine();
                        }
                        System.out.println(ManagerManaging.viewUser(username));
                        this.run();
                        break;
                    case 2 :
                        System.out.println("Enter Username :");
                        ConsoleCmd.scanner.nextLine();
                        username = ConsoleCmd.scanner.nextLine();
                        while (!LoginOrRegisterManaging.isThereUsernameWithThisName(username)) {
                            try {
                                throw ViewException.notExistingUsername();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingUsername().getMessage());
                            }
                            username = ConsoleCmd.scanner.nextLine();
                        }
                        ManagerManaging.deleteUser(username);
                        System.out.println("Deleted Successfully");
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
                        info.put("type", "manager");
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
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter Products id");
                        int id =  ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThereProductWithId(id)) {
                            try {
                                throw ViewException.invalidIDNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumber().getMessage());
                            }
                            id =  ConsoleCmd.scanner.nextInt();
                        }
                        ManagerManaging.removeProduct(id);
                        System.out.println("Removed Successfully");
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

    private Menu createDiscountCode() {
        return new Menu("Create Discount Code", this) {
            @Override
            public void show() {
                System.out.println("Enter the code number :");
            }

            @Override
            public void execute() throws ViewException {
                int code = ConsoleCmd.scanner.nextInt();
                while (ManagerManaging.isThisCodeExists(code)) {
                    try {
                        throw ViewException.existingDiscountCode();
                    }catch (ViewException e) {
                        System.out.println(ViewException.existingDiscountCode().getMessage());
                    }
                    code = ConsoleCmd.scanner.nextInt();
                }
                System.out.println("Enter premier date :");
                long startDate = ConsoleCmd.scanner.nextLong();
                System.out.println("Enter final date :");
                long endDate = ConsoleCmd.scanner.nextLong();
                System.out.println("Enter discount's percentage :");
                int percentage = ConsoleCmd.scanner.nextInt();
                while (percentage < 0 || percentage > 100) {
                    try {
                        throw ViewException.invalidDiscountPercentage();
                    }catch (ViewException e) {
                        System.out.println(ViewException.invalidDiscountPercentage().getMessage());
                    }
                    percentage = ConsoleCmd.scanner.nextInt();
                }
                System.out.println("Enter maximum amount of discount :");
                int maxDiscountAmount = ConsoleCmd.scanner.nextInt();
                ArrayList<String> usersWithDiscount = new ArrayList<>();
//                ???
                ManagerManaging.createDiscountCode(code, startDate, endDate, percentage, maxDiscountAmount,usersWithDiscount);
                System.out.println("Discount built successfully");
                this.parentMenu.run();
            }
        };
    }

    private Menu viewDiscountCode() {
        return new Menu("View Discount Code", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println(ManagerManaging.viewDiscountCodes());
                System.out.println("0. back\n" +
                        "1. view discount code\n" +
                        "2. edit discount code\n" +
                        "3. remove discount code");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                int code = 0;
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        code = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThisCodeExists(code)) {
                            try {
                                throw ViewException.notExistingDiscountCode();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingDiscountCode().getMessage());
                            }
                            code = ConsoleCmd.scanner.nextInt();
                        }
                        System.out.println(ManagerManaging.viewDiscountCode(code));
                        this.run();
                        break;
                    case 2 :
                        code = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThisCodeExists(code)) {
                            try {
                                throw ViewException.notExistingDiscountCode();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingDiscountCode().getMessage());
                            }
                            code = ConsoleCmd.scanner.nextInt();
                        }
                        System.out.println("Enter the field you want to edit :\n" +
                                "0. back\n" +
                                "1. premier date\n" +
                                "2. end date\n" +
                                "3. percentage\n" +
                                "4. max discount amount\n" +
                                "5. new user to have code");
                        int edit = ConsoleCmd.scanner.nextInt();
                        switch (edit) {
                            case 0 :
                                this.run();
                                break;
                            case 1 :
                                System.out.println("Enter new Date :");
                                long newStartDate = ConsoleCmd.scanner.nextLong();
                                ManagerManaging.editDiscountCodeDate("startDate", newStartDate);
                                System.out.println("Edited Successfully");
                                this.run();
                                break;
                            case 2 :
                                System.out.println("Enter new Date :");
                                long newEndDate = ConsoleCmd.scanner.nextLong();;
                                ManagerManaging.editDiscountCodeDate("endDate", newEndDate);
                                System.out.println("Edited Successfully");
                                this.run();
                                break;
                            case 3 :
                                System.out.println("Enter new percentage :");
                                int newPercentage = ConsoleCmd.scanner.nextInt();
                                while (newPercentage < 0 || newPercentage > 100) {
                                    try {
                                        throw ViewException.invalidDiscountPercentage();
                                    }catch (ViewException e) {
                                        System.out.println(ViewException.invalidDiscountPercentage().getMessage());
                                    }
                                    newPercentage = ConsoleCmd.scanner.nextInt();
                                }
                                ManagerManaging.editDiscountCodeIntegers("percentReduction", newPercentage);
                                System.out.println("Edited Successfully");
                                this.run();
                                break;
                            case 4 :
                                System.out.println("Enter new max discount amount :");
                                int newMax = ConsoleCmd.scanner.nextInt();
                                ManagerManaging.editDiscountCodeIntegers("maxReductionAmount", newMax);
                                System.out.println("Edited Successfully");
                                this.run();
                                break;
                            case 5 :
                                System.out.println("Enter new username");
                                ConsoleCmd.scanner.nextLine();
                                String username = ConsoleCmd.scanner.nextLine();
                                while (!LoginOrRegisterManaging.isThereUsernameWithThisName(username) ||
                                ManagerManaging.doesThisPersonHaveThisCode(code, username)) {
                                    try {
                                        throw ViewException.existingDiscountCodeForThisUser();
                                    }catch (ViewException e) {
                                        if (!LoginOrRegisterManaging.isThereUsernameWithThisName(username))
                                            System.out.println(ViewException.notExistingUsername().getMessage());
                                        else if (ManagerManaging.doesThisPersonHaveThisCode(code, username))
                                            System.out.println(ViewException.existingDiscountCodeForThisUser().getMessage());
                                    }
                                    username = ConsoleCmd.scanner.nextLine();
                                }
                                ManagerManaging.addTheCodeToUser(code, username);
                                System.out.println("Added Successfully");
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
                        break;
                    case 3 :
                        code = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThisCodeExists(code)) {
                            try {
                                throw ViewException.notExistingDiscountCode();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingDiscountCode().getMessage());
                            }
                            code = ConsoleCmd.scanner.nextInt();
                        }
                        ManagerManaging.removeDiscountCode(code);
                        System.out.println("Removed Successfully");
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

    private Menu manageRequests() {
        return new Menu("Manage Requests", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println(ManagerManaging.manageRequests());
                System.out.println("0. back");
                System.out.println("1. details of Request\n" +
                        "2. accept request\n" +
                        "3. reject request");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Please enter an ID :");
                        int id = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThereRequestWithID(id)) {
                            try {
                                throw ViewException.invalidIDNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumber().getMessage());
                            }
                            id = ConsoleCmd.scanner.nextInt();
                        }
                        System.out.println(ManagerManaging.requestDetails(id));
                        this.run();
                        break;
                    case 2 :
                        System.out.println("Please enter an ID :");
                        id = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThereRequestWithID(id)) {
                            try {
                                throw ViewException.invalidIDNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumber().getMessage());
                            }
                            id = ConsoleCmd.scanner.nextInt();
                        }
                        ManagerManaging.acceptRequest(id);
                        System.out.println("Request Accepted");
                        this.run();
                        break;
                    case 3 :
                        System.out.println("Please enter an ID :");
                        id = ConsoleCmd.scanner.nextInt();
                        while (!ManagerManaging.isThereRequestWithID(id)) {
                            try {
                                throw ViewException.invalidIDNumber();
                            }catch (ViewException e) {
                                System.out.println(ViewException.invalidIDNumber().getMessage());
                            }
                            id = ConsoleCmd.scanner.nextInt();
                        }
                        ManagerManaging.rejectRequest(id);
                        System.out.println("Request Rejected");
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

    private Menu manageCategories() {
        return new Menu("Manage Categories", this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println(ManagerManaging.manageCategories());
                System.out.println("0. back\n" +
                        "1. edit category\n" +
                        "2. add category\n" +
                        "3. remove category");
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                switch (menuChanger) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        System.out.println("Enter category's name :");
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
                        System.out.println("chose what you want to change\n" +
                                "0. break\n" +
                                "1. name\n" +
                                "2. special attributes\n" +
                                "3. good");
                        int edit = ConsoleCmd.scanner.nextInt();
                        switch (edit) {
                            case 0 :
                                this.run();
                                break;
                            case 1 :
                                System.out.println("Enter new name");
                                ConsoleCmd.scanner.nextLine();
                                String change = ConsoleCmd.scanner.nextLine();
                                while (ManagerManaging.isThereSuchCategory(change)) {
                                    try {
                                        throw ViewException.existingCategory();
                                    }catch (ViewException e) {
                                        System.out.println(ViewException.existingCategory().getMessage());
                                    }
                                    change = ConsoleCmd.scanner.nextLine();
                                }
                                ManagerManaging.editCategory("name", category, change);
                                System.out.println("Name Changed Successfully");
                                this.run();
                                break;
                            case 2 :
                                System.out.println("Enter new attribute");
                                ConsoleCmd.scanner.nextLine();
                                change = ConsoleCmd.scanner.nextLine();
                                ManagerManaging.editCategory("specialAttributes", category, change);
                                System.out.println("Attribute added Successfully");
                                this.run();
                                break;
                            case 3 :
                                System.out.println("Enter new product");
                                ConsoleCmd.scanner.nextLine();
                                change = ConsoleCmd.scanner.nextLine();
                                while (ManagerManaging.isThereSuchGoodInCategory(category, change)) {
                                    try {
                                        throw ViewException.existingGoodInCategory();
                                    }catch (ViewException e) {
                                        System.out.println(ViewException.existingGoodInCategory().getMessage());
                                    }
                                    change = ConsoleCmd.scanner.nextLine();
                                }
                                ManagerManaging.editCategory("goods", category, change);
                                System.out.println("Product added Successfully");
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
                        break;
                    case 2 :
                        System.out.println("Enter new categories Name :");
                        ConsoleCmd.scanner.nextLine();
                        String name = ConsoleCmd.scanner.nextLine();
                        while (ManagerManaging.isThereSuchCategory(name)) {
                            try {
                                throw ViewException.existingCategory();
                            }catch (ViewException e) {
                                System.out.println(ViewException.existingCategory().getMessage());
                            }
                            name = ConsoleCmd.scanner.nextLine();
                        }
                        System.out.println("to add goods and attributes on category go to edit category");
                        ArrayList<Good> goods = new ArrayList<>();
                        ArrayList<String> attributes = new ArrayList<>();
                        ManagerManaging.addCategory(name, goods, attributes);
                        System.out.println("Category added Successfully");
                        this.run();
                        break;
                    case 3 :
                        System.out.println("Enter category's Name :");
                        ConsoleCmd.scanner.nextLine();
                        name = ConsoleCmd.scanner.nextLine();
                        while (!ManagerManaging.isThereSuchCategory(name)) {
                            try {
                                throw ViewException.notExistingCategory();
                            }catch (ViewException e) {
                                System.out.println(ViewException.notExistingCategory().getMessage());
                            }
                            name = ConsoleCmd.scanner.nextLine();
                        }
                        ManagerManaging.removeCategory(name);
                        System.out.println("Removed Successfully");
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
                        ManagerManaging.logout();
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
