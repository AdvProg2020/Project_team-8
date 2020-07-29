package Client.Model;

import Client.Controller;
import Client.DataHandler.MessageHandler;
import Client.Model.Category;
import Client.Model.Good;
import Client.Model.Seller;

import javax.persistence.Entity;
import java.io.File;
import java.util.ArrayList;
@Entity
public class FileGood extends Good {
    private String filePath;
    private String fileType;
    public FileGood(){}
    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, String movie, ArrayList<String> specialAttributes, String filePath,String fileType) {
        super(name, brand, price, seller, stock, category, detailInfo, image, movie, specialAttributes);
        this.filePath = filePath;
        this.fileType = fileType;
        Controller.saveOrUpdateObject(this);
        this.getSeller().addGood(this);
        Controller.saveOrUpdateObject(this.getSeller());
        MessageHandler.sendUploadDataMessage(filePath,getName(),fileType);
    }
    public static FileGood getFileGoodByName(String name){
        for (FileGood fileGood : ManageInfo.allFileGoods) {
            if(fileGood.getName().equals(name))
                return fileGood;
        }
        return null;
    }
}
