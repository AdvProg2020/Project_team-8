package View;


import java.util.HashMap;

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
                user = LoginType.MANAGER;
                System.out.println("Enter A UserName :");
                ConsoleCmd.scanner.nextLine();
                String username = ConsoleCmd.scanner.nextLine();
                System.out.println("Enter your Password :");
                String password = ConsoleCmd.scanner.nextLine();
                System.out.println("Login Successfully");
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
                System.out.println("Enter a username");
            }

            @Override
            public void execute() throws ViewException {
                user = LoginType.MANAGER;
                super.execute();
            }
        };
    }

}
