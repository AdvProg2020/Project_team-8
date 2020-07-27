package Server.Model;

import Client.Model.Category;
import Client.Model.Good;
import Client.Model.Seller;

import java.io.File;
import java.util.ArrayList;

public class FileGood extends Good {
    private File file;

    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, ArrayList<String> specialAttributes, File file) {
        super(name, brand, price, seller, stock, category, detailInfo, image, null, specialAttributes);
        this.file = file;
    }
}
