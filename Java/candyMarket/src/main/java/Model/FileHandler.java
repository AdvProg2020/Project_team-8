package Model;


import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;


public class FileHandler {
    private static Gson usersJson = new Gson();
    private static Gson sellLogsJson = new Gson();
    private static Gson buyLogsJson = new Gson();

    private static File usersFile = new File("Resources\\users.txt");
    private static File sellLogsFile = new File("Resources\\sellLogs.txt");
    private static File buyLogsFile = new File("Resources\\buyLogs.txt");


    public static void getDataFromFiles() throws IOException {
        loadUsersData();
        loadSellLogsData();
    }

    public static void setDataIntoFiles() throws IOException {
        writeUsersFiles();
        writeSellLog();
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

}
