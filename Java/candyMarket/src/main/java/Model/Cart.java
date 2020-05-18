package Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private String phoneNumber;
    private int totalAmount = 0;
    private int discountAmount = 0;

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    private HashMap<Good,Integer> howManyGoods;
    private ArrayList<Good> goods;
    private String buyerName;
    private CartSituation buySituation;


    public void addDiscount(Discount discount){
        discountAmount = totalAmount* discount.getPercentReduction()/100;
        if(discountAmount > discount.getMaxReductionAmount()) discountAmount=discount.getMaxReductionAmount();
        totalAmount -= discountAmount;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }


    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String sellerName) {
        buyerName = buyerName;
    }

    public CartSituation getBuySituation() {
        return buySituation;
    }

    public void setBuySituation(CartSituation buySituation) {
        this.buySituation = buySituation;
    }
    public Boolean canPay(){
        if(Buyer.currentBuyer==null)
        return false;
        else return true;
    }
    public void createLogs(){
        HashMap<Seller,HashMap<Good,Integer>> logs = new HashMap<>();
        for (Good g:
             User.currentUser.getCart().getGoods()) {
            if(logs.keySet().contains(g.getSeller())) {
                HashMap<Good, Integer> currentGoods = logs.get(g.getSeller());
                if(currentGoods.containsValue(g))
                    currentGoods.put(g,currentGoods.get(g)+1);
                else currentGoods.put(g,1);
                logs.put(g.getSeller(),currentGoods);
            }
            else
            {
                HashMap<Good,Integer> goods = new HashMap<Good,Integer>(){
                    {
                        put(g,1);      
                    }
                };
                logs.put(g.getSeller(),goods);
            }
        }
        for(Map.Entry<Seller, HashMap<Good, Integer>> entry : logs.entrySet()) {
            Seller key = entry.getKey();
            HashMap<Good,Integer> value = entry.getValue();
            int totalAmount = 0;
            int saleAmount = 0;
            for(Map.Entry<Good,Integer> entry2 : value.entrySet()) {
               Good key2 = entry2.getKey();
               int value2 = entry2.getValue();
               totalAmount+=key2.getAmountAfterSale();
               saleAmount+=key2.getsalePercentageAmount()*key2.getPrice()/100;
            }
            BuyLog buyLog = new BuyLog(totalAmount-discountAmount,discountAmount,value,key.getUsername());
            SellLog sellLog = new SellLog(totalAmount,saleAmount,value,Buyer.currentBuyer.getUsername());
            Buyer.currentBuyer.addMyLogs(buyLog);
            Seller.currentSeller.addMySellLog(sellLog);
            ManageInfo.allBuyLogs.add(buyLog);
            ManageInfo.allSellLogs.add(sellLog);
        }
        }
    }
