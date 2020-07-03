package Model;


import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private static Gson usersJson = new Gson();
    private static Gson sellLogsJson = new Gson();
    private static Gson buyLogsJson = new Gson();
    private static Gson categoriesJson = new Gson();
    private static Gson brandsJson = new Gson();
    private static Gson requestsJson = new Gson();
    private static Gson goodsJson = new Gson();
    private static Gson discountsJson = new Gson();
    private static Gson sellersJson = new Gson();
    private static File managersFile = new File("Resources\\managers.txt");
    private static File buyersFile = new File("Resources\\buyers.txt");
    private static File sellersFile = new File("Resources\\sellers.txt");
    private static File usersFile = new File("Resources\\users.txt");
    private static File sellLogsFile = new File("Resources\\sellLogs.txt");
    private static File buyLogsFile = new File("Resources\\buyLogs.txt");
    private static File categoriesFile = new File("Resources\\categories.txt");
    private static File brandsFile = new File("Resources\\brands.txt");
    private static File requestsFile = new File("Resources\\requests.txt");
    private static File goodsFile = new File("Resources\\goods.txt");
    private static File discountsFile = new File("Resources\\discounts.txt");


    public static void getDataFromFiles() throws IOException {
        loadGoodsData();
        loadManagersData();
        loadSellersData();
        loadBuyersData();
        loadSellLogsData();
        loadBuyLogsData();
        loadCategoriesData();
        loadBrandsData();
        loadRequestFile();
        loadDiscountsData();
    }

    public static void setDataIntoFiles() throws IOException {
        writeManagersFiles();
        writeBuyersFiles();
        writeSellersFiles();
        writeSellLog();
        writeBuyLog();
        writeCategories();
        writeBrands();
        writeRequest();
        writeGoods();
        writeDiscounts();
    }

    private static void writeManagersFiles() throws IOException {
        FileWriter writer = new FileWriter(managersFile);
        for (Manager manager : ManageInfo.allManagers) {
            writer.append(usersJson.toJson(manager) + "\n");
        }
        writer.close();
    }
    private static void loadManagersData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(managersFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            Manager manager = usersJson.fromJson(fileReader.nextLine(), Manager.class);
            ManageInfo.allManagers.add(manager);
            ManageInfo.allUsers.add(manager);
        }
    }
    private static void writeSellersFiles() throws IOException {
        FileWriter writer = new FileWriter(sellersFile);
        for (Seller seller : ManageInfo.allSellers) {
            writer.append(sellersJson.toJson(seller) + "\n");
        }
        writer.close();
    }
    private static void loadSellersData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(sellersFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            Seller seller = sellersJson.fromJson(fileReader.nextLine(), Seller.class);
            ManageInfo.allSellers.add(seller);
            ManageInfo.allUsers.add(seller);
            for (Good good:seller.getMyGoods()
                 ) {
                good = Good.getGoodByName(good.getName(),ManageInfo.allGoods);
            }
        }
    }
    private static void writeBuyersFiles() throws IOException {
        FileWriter writer = new FileWriter(buyersFile);
        for (Buyer buyer : ManageInfo.allBuyers) {
            writer.append(usersJson.toJson(buyer) + "\n");
        }
        writer.close();
    }
    private static void loadBuyersData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(sellersFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            Buyer buyer = usersJson.fromJson(fileReader.nextLine(), Buyer.class);
            ManageInfo.allBuyers.add(buyer);
            ManageInfo.allUsers.add(buyer);
        }
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
            User user = usersJson.fromJson(fileReader.nextLine(), User.class);
            ManageInfo.allUsers.add(user);
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
        writer.close();
    }

    private static void loadRequestFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(requestsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allRequests.add(requestsJson.fromJson(fileReader.nextLine(), Request.class));
        }
    }

    private static void writeGoods() throws IOException {
        FileWriter writer = new FileWriter(goodsFile);
        for (Good good : ManageInfo.allGoods) {
            writer.append(goodsJson.toJson(good) + "\n");
        }
        writer.close();
    }

    private static void loadGoodsData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(goodsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            Good good = goodsJson.fromJson(fileReader.nextLine(), Good.class);
            good.setCategory(Category.getCategoryByName(good.getCategory().getName()));
            ManageInfo.allGoods.add(good);
        }
    }

    private static void writeDiscounts() throws IOException {
        FileWriter writer = new FileWriter(discountsFile);
        for (Discount discount : ManageInfo.allDiscounts) {
            writer.append(discountsJson.toJson(discount) + "\n");
        }
        writer.close();
    }

    private static void loadDiscountsData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(discountsFile);
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allDiscounts.add(discountsJson.fromJson(fileReader.nextLine(), Discount.class));
        }
    }

}
