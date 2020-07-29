package Server.Model;



import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.File;
import java.util.ArrayList;
@Entity
public class FileGood extends Good {
    private String filePath;
    private String fileType;
    public FileGood(){}
    public FileGood(String name, String brand, int price, Seller seller, int stock, Category category,
                    String detailInfo, String image, ArrayList<String> specialAttributes, String filePath,String fileType) {
        super(name, brand, price, seller, stock, category, detailInfo, image, null, specialAttributes);
        this.filePath = filePath;
        this.fileType = fileType;
    }
    public static FileGood getFileGoodByName(String name){
        for (FileGood fileGood : ManageInfo.allFileGoods) {
            if(fileGood.getName().equals(name))
                return fileGood;
        }
        return null;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
