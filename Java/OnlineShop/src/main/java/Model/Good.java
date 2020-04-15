package Model;

import java.util.ArrayList;

public class Good {
    private int id;
    private ItemCreationSituation situation;
    private String name;
    private String brand;
    private int price;
    private ArrayList<User> sellers;
    private ArrayList<Buyer> buyers;
    private int stock;
    private Category category;
    private String categorySpecialAttributes;
    private String detailInfo;
    private int averageScore;
    private ArrayList<Opinion> opinions;

    public static boolean isThereGoodWithId(int goodId) {
        return true;
    }

    public static Good getGoodById(int goodId) {
        return null;
    }


}
