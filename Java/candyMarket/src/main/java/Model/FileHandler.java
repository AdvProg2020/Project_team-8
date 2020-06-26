package Model;


import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;


public class FileHandler {
    private static Gson usersJson = new Gson();
    private static Gson sellLogsJson = new Gson();
    private static Gson buyLogsJson = new Gson();
    private static Gson categoriesJson = new Gson();
    private static Gson brandsJson = new Gson();
    private static Gson requestsJson = new Gson();

    private static File usersFile = new File("Resources\\users.txt");
    private static File sellLogsFile = new File("Resources\\sellLogs.txt");
    private static File buyLogsFile = new File("Resources\\buyLogs.txt");
    private static File categoriesFile = new File("Resources\\categories.txt");
    private static File brandsFile = new File("Resources\\brands.txt");
    private static File requestsFile = new File("Resources\\requests.txt");


    public static void getDataFromFiles() throws IOException {
        loadUsersData();
        loadSellLogsData();
        loadBuyLogsData();
        loadCategoriesData();
        loadBrandsData();
        loadRequestFile();
    }

    public static void setDataIntoFiles() throws IOException {
        writeUsersFiles();
        writeSellLog();
        writeBuyLog();
        writeCategories();
        writeBrands();
        writeRequest();
    }


    private static void writeUsersFiles() throws IOException {
        FileWriter writer = new FileWriter(usersFile);
        for (User user : ManageInfo.allUsers) {
            writer.append(usersJson.toJson(user) + "\n");
        }
        writer.close();
    }

    private static void loadUsersData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(usersFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allUsers.add(usersJson.fromJson(fileReader.nextLine(), User.class));
        }
    }

    private static void writeSellLog() throws IOException {
        FileWriter writer = new FileWriter(sellLogsFile);
        for (SellLog sellLog : ManageInfo.allSellLogs) {
            writer.append(sellLogsJson.toJson(sellLog) + "\n");
        }
        writer.close();
    }

    private static void loadSellLogsData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(sellLogsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allSellLogs.add(sellLogsJson.fromJson(fileReader.nextLine(), SellLog.class));
        }
    }

    private static void writeBuyLog() throws IOException {
        FileWriter writer = new FileWriter(buyLogsFile);
        for (BuyLog buyLog : ManageInfo.allBuyLogs) {
            writer.append(buyLogsJson.toJson(buyLog) + "\n");
        }
        writer.close();
    }

    private static void loadBuyLogsData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(buyLogsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allBuyLogs.add(buyLogsJson.fromJson(fileReader.nextLine(), BuyLog.class));
        }
    }

    private static void writeCategories() throws IOException {
        FileWriter writer = new FileWriter(categoriesFile);
        for (Category category : ManageInfo.allCategories) {
            writer.append(categoriesJson.toJson(category) + "\n");
        }
        writer.close();
    }

    private static void loadCategoriesData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(categoriesFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allCategories.add(categoriesJson.fromJson(fileReader.nextLine(), Category.class));
        }
    }

    private static void writeBrands() throws IOException {
        FileWriter writer = new FileWriter(brandsFile);
        for (String brand : ManageInfo.allBrands) {
            writer.append(brandsJson.toJson(brand) + "\n");
        }
        writer.close();
    }

    private static void loadBrandsData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(brandsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allBrands.add(brandsJson.fromJson(fileReader.nextLine(), String.class));
        }
    }

    private static void writeRequest() throws IOException {
        FileWriter writer = new FileWriter(requestsFile);
        for (Request request : ManageInfo.allRequests) {
            writer.append(requestsJson.toJson(request) + "\n");
        }
    }

    private static void loadRequestFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(requestsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allRequests.add(requestsJson.fromJson(fileReader.nextLine(), Request.class));
        }
    }

}
