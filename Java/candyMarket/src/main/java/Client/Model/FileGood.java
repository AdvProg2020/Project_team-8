package Client.Model;

import Client.Model.Category;
import Client.Model.Good;
import Client.Model.Seller;

import java.io.File;
import java.util.ArrayList;

public class FileGood extends Good {
    private String filepath;
    private String fileName;

    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, String movie, ArrayList<String> specialAttributes, File file) {
        super(name, brand, price, seller, stock, category, detailInfo, image, movie, specialAttributes);
        this.filepath = file.getPath();
        this.fileName = file.getName();
    }

    public String getFilepath() {
        return filepath;
    }

    public String getFileName() {
        return fileName;
    }
}
