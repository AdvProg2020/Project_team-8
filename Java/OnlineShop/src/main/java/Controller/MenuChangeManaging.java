package Controller;

import java.util.Scanner;

public class MenuChangeManaging {
     private static Scanner scanner ;
     public static int changeMenu() {
          scanner = new Scanner(System.in);
          Integer input = Integer.parseInt(scanner.nextLine());
          return input;
     }
}
