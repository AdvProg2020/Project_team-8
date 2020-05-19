package Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String address;
    private String buyerName;
    private CartSituation buySituation;
    private String phoneNumber;
    private int totalAmount = 0;

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

    private int discountAmount = 0;

    public HashMap<Good, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Good, Integer> goods) {
        this.goods = goods;
    }

    private HashMap<Good,Integer> goods = new HashMap<>();

    public void addDiscount(Discount discount){
        discountAmount = totalAmount* discount.getPercentReduction()/100;
        if(discountAmount > discount.getMaxReductionAmount()) discountAmount=discount.getMaxReductionAmount();
        totalAmount -= discountAmount;
    }

    public void pay(){
        for (Good g:
             goods.keySet()) {
            g.addBuyers(UserHandler.currentBuyer);
            UserHandler.currentBuyer.getBoughtGoods().add(g);
        }
        UserHandler.currentBuyer.setBalance(UserHandler.currentBuyer.getBalance()-UserHandler.currentCart.getTotalAmount());
        resetCart();
    }
    public void resetCart(){
        address = null;
        buyerName = null;
        phoneNumber = null;
        totalAmount = 0;
        discountAmount = 0;
        goods = new HashMap<>();
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
        if(Buyer.currentBuyer.getBalance()<UserHandler.currentCart.getTotalAmount()) return false;
        else return true;
    }
    public boolean increaseProduct(Good good){
        if(good.getStock()==0)
            return false;
        if(goods.get(good) == null)
            goods.put(good,1);
        else
        goods.put(good,goods.get(good)+1);
        good.setStock(good.getStock()-1);
        UserHandler.currentCart.setTotalAmount(UserHandler.currentCart.getTotalAmount()+good.getPriceAfterSale());
        return true;
    }
    public void decreaseProduct(Good good){
        if(goods.get(good)<=1)
            goods.remove(good);
        else
            goods.put(good,goods.get(good)-1);
        good.setStock(good.getStock()+1);
        UserHandler.currentCart.setTotalAmount(UserHandler.currentCart.getTotalAmount()-good.getPriceAfterSale());
    }
    public void createLogs(){
        HashMap<Seller,HashMap<Good,Integer>> logs = new HashMap<>();
        for(Map.Entry<Good,Integer> entry : goods.entrySet()) {
            Good g = entry.getKey();
            int gNum = entry.getValue();
            if(logs.keySet().contains(g.getSeller())) {
                HashMap<Good, Integer> currentGoods = logs.get(g.getSeller());
                if(currentGoods.containsValue(g))
                    currentGoods.put(g,currentGoods.get(g)+gNum);
                else currentGoods.put(g,gNum);
                logs.put(g.getSeller(),currentGoods);
            }
            else
            {
                HashMap<Good,Integer> goods = new HashMap<Good,Integer>(){
                    {
                        put(g,gNum);
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
               totalAmount+=key2.getPriceAfterSale();
               saleAmount+=key2.getSalePercentageAmount()*key2.getPrice()/100;
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
