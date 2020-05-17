package View;

import java.util.HashMap;

public abstract class Menu {
    private String name;
    protected Menu parentMenu;
    protected HashMap<Integer, Menu> subMenus;
    protected static LoginType user = LoginType.DEFAULT;

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public HashMap<Integer, Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(HashMap<Integer, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getName() {
        return name;
    }

    public void show() {
        System.out.println(this.name);
        if(this.parentMenu==null) System.out.println("0. exit");
        else System.out.println("0. back");
        if(subMenus == null) return;
        for (Integer menuNum : subMenus.keySet()) {
            System.out.println(menuNum+". "+subMenus.get(menuNum).getName());
        }
    }
    public void run() throws ViewException {
        try {
            show();
            execute();
        }
        catch (ViewException e){
            System.out.println(e.getMessage());
            run();
        }
    }
    public void execute() throws ViewException {
        Menu nextMenu = null;
        int menuChanger = ConsoleCmd.scanner.nextInt();
        if (menuChanger == 0)
        {
            if (this.parentMenu==null)
                System.exit(1);
            else
                nextMenu=this.parentMenu;
        }
        else{
            nextMenu = subMenus.get(menuChanger);
        }
        nextMenu.run();
    }
}

