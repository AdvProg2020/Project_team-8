import View.ConsoleDesign;
import View.MainMenu;
import View.ViewException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ViewException {
        MainMenu mainMenu = new MainMenu();
        System.out.println(ConsoleDesign.WHITE);
        mainMenu.run();
    }
}
