package View;


import javax.swing.text.View;
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
    public void run() {
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
                System.out.println("Enter A UserName :");
                ConsoleCmd.scanner.nextLine();
                String username = ConsoleCmd.scanner.nextLine();
                if (username.charAt(0) == '0')
                    this.parentMenu.run();
                if (username.charAt(0) == '0')
                    this.parentMenu.run();
                System.out.println("Enter your Password :");
                String password = ConsoleCmd.scanner.nextLine();
                if (password.charAt(0) == '0')
                    this.parentMenu.run();
                System.out.println("Enter your Name :");
                String name = ConsoleCmd.scanner.nextLine();
                if (name.charAt(0) == '0')
                    this.parentMenu.run();
                System.out.println("Enter your LastName :");
                String lastName = ConsoleCmd.scanner.nextLine();
                if (lastName.charAt(0) == '0')
                    this.parentMenu.run();
                System.out.println("Enter your Email :");
                String email = ConsoleCmd.scanner.nextLine();
                while (!emailValidation(email)) {
                    if (password.charAt(0) == '0')
                        this.parentMenu.run();
                    try {
                        throw ViewException.invalidEmailFormat();
                    }catch (ViewException e) {
                        System.out.println(ViewException.invalidEmailFormat().getMessage());
                    }
                    email = ConsoleCmd.scanner.nextLine();
                }
                System.out.println("Enter the type of your account\n" +
                        "0. back\n" +
                        "1. Buyer\n" +
                        "2. Seller\n" +
                        "3. Manager");
                String type = ConsoleCmd.scanner.nextLine();
                switch (Integer.parseInt(type)) {
                    case 0 :
                        this.parentMenu.run();
                        break;
                    case 1 :
                        user = LoginType.BUYER;
                        break;
                    case 2 :
                        System.out.println("Enter your company's name :");
                        String companyName = ConsoleCmd.scanner.nextLine();
                        if (companyName.charAt(0) == '0')
                            this.parentMenu.run();
                        user = LoginType.SELLER;
                        break;
                    case 3 :
                        user = LoginType.MANAGER;
                        break;
                }
                System.out.println("Registered Successfully");
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
                String username = ConsoleCmd.scanner.nextLine();
                if (username.charAt(0) == '0')
                    this.parentMenu.run();
                System.out.println("Enter your Password :");
                String password = ConsoleCmd.scanner.nextLine();
                if (password.charAt(0) == '0')
                    this.parentMenu.run();
                user = LoginType.MANAGER;
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
}
