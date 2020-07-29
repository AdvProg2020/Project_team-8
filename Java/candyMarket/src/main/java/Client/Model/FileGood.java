package Client.Model;

import Client.Controller;
import Client.Model.Category;
import Client.Model.Good;
import Client.Model.Seller;

import javax.persistence.Entity;
import java.io.File;
import java.util.ArrayList;
@Entity
public class FileGood extends Good {
    private String filePath;
    public FileGood(){}
    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, String movie, ArrayList<String> specialAttributes, String filePath) {
        super(name, brand, price, seller, stock, category, detailInfo, image, movie, specialAttributes);
        this.filePath = filePath;
        Controller.saveOrUpdateObject(this);
    }
}
