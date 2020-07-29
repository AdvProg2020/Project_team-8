package Server.Model;

import javax.persistence.Entity;
import java.io.File;
import java.util.ArrayList;
@Entity
public class FileGood extends Good {
    private String filePath;
    public FileGood(){}
    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, ArrayList<String> specialAttributes, String filePath) {
        super(name, brand, price, seller, stock, category, detailInfo, image, null, specialAttributes);
        this.filePath = filePath;
    }
}
