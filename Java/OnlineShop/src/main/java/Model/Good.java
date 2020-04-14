package Model;

import java.util.List;

public class Good {
    private int id;
    private itemCreationSituation situation;
    private String name;
    private String brand;
    private int price;
    private List<User> sellers;
    private int stock;
    private Category category;
    private String categorySpecialAttributes;
    private String detailInfo;
    private int averageScore;
    private List<Opinion> opinions;
}
