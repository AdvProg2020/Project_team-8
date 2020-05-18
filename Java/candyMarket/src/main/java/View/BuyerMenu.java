package View;

import Controller.BuyerManaging;
import Controller.ManagerManaging;

import java.util.HashMap;

public class BuyerMenu extends Menu {
    public BuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer, Menu> subMenus = new HashMap<>();
        subMenus.put(1, viewPersonalInfo());
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
                String info = BuyerManaging.showPersonalInfo();
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


}
