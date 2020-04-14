package Model;

import java.util.List;

public class Seller extends User {
    private List<Sale> mySales;
    private List<Good> myGoods;
    private List<SellLog> mySellLog;
    private String companyName;
    private String facturyName;
    private String workShopName;
}
