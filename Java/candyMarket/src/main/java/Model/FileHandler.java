package Model;


import com.google.gson.Gson;

import javax.print.attribute.standard.MediaName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private static Gson categoriesJson;
    private static Gson requestJson;
    private static Gson goodsJson;
    private static Gson buyLogsJson;
    private static Gson sellLogs;
    private static Gson usersJson;
    private static Gson brandsJson;
    private static Gson discountCodes;

    private static FileWriter categoriesFile;
    private static FileWriter goodsFile;
    private static FileWriter sellLogsFile;
    private static FileWriter usersFile;
    private static FileWriter buyLogsFile;
    private static FileWriter brandsFile;
    private static FileWriter discountsFile;
    private static FileWriter requestFile;


    public static void getDataFromFiles() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("users.txt"));
        ManageInfo.allUsers.clear();
        ManageInfo.allUsers.addAll(usersJson.fromJson(reader, ArrayList.class));
    }

    public static void setDataIntoFiles() throws IOException {
        createJson();
        createFile();
        writeFiles();
    }

    private static void createJson () {
        categoriesJson = new Gson();
        categoriesJson.toJson(ManageInfo.allCategories);
        requestJson = new Gson();
        requestJson.toJson(ManageInfo.allRequests);
        goodsJson = new Gson();
        goodsJson.toJson(ManageInfo.allGoods);
        buyLogsJson = new Gson();
        buyLogsJson.toJson(ManageInfo.allBuyLogs);
        sellLogs = new Gson();
        sellLogs.toJson(ManageInfo.allSellLogs);
        usersJson = new Gson();
        usersJson.toJson(ManageInfo.allUsers);
        brandsJson = new Gson();
        brandsJson.toJson(ManageInfo.allBrands);
        discountCodes = new Gson();
        discountCodes.toJson(ManageInfo.allDiscounts);
    }

    private static void createFile() throws IOException {
        try {
            categoriesFile = new FileWriter("categories.txt");
            usersFile = new FileWriter("users.txt", false);
            brandsFile = new FileWriter("brands.txt");
            buyLogsFile = new FileWriter("buyLogs.txt");
            sellLogsFile = new FileWriter("sellLogs.txt");
            goodsFile = new FileWriter("goods.txt");
            discountsFile = new FileWriter("discounts.txt");
            requestFile = new FileWriter("requests.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeFiles() throws IOException {
        usersFile.write(String.valueOf(usersJson));
        usersFile.close();
    }

}
