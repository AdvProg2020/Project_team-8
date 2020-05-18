package Controller;

import Model.*;
import com.sun.xml.internal.ws.server.ServerRtException;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class GoodManaging {
    private static Good good;
    private static Scanner scanner;

    public static void setGood() {
        ;
    }

    public static void setScanner(Scanner scanner) {
        GoodManaging.scanner = scanner;
    }

    public static String digest(String goodName) {
        Good good = Good.getGoodByName(goodName,Good.fixedGoods);
        String data = "info: "+good.getDetailInfo()+"\n";
        data+="price : "+good.getPrice()+"\n";
        data+="discount : "+good.getsalePercentageAmount()+"%"+"\n";
        data+="category : "+good.getCategory().getName()+"\n";
        data+="score : "+good.getAverageScore()+"\100"+"\n";
        return data;
    }

    public static boolean addProductToCart(String goodName) {
        return CartManaging.increaseProductNumberInCart(goodName);
    }

    public static void selectSellerToBuyFrom() {
        ;
    }

    public static String attributes(String goodName)
    {
        Good good = Good.getGoodByName(goodName,Good.fixedGoods);
        String data = "info | price | discount | category | score ";
        data += "\n";
        data+=good.getDetailInfo()+" | "+good.getPrice()+" | "+good.getsalePercentageAmount()
                +" | "+good.getCategory()+" | "+good.getAverageScore();
        data+="\n\n\n";
        data+="brand | stock | categoryAttributes | goodSituation ";
        data+="\n";
        data+=good.getBrand()+" | "+good.getStock()+" | "+good.getCategorySpecialAttributes()+" | "+good
                .getSituation().toString();
        return data;
    }

    public static String compareToAnotherProduct(String goodName1,String goodName2){
        Good good1 = Good.getGoodByName(goodName1,Good.fixedGoods);
        Good good2 = Good.getGoodByName(goodName2,Good.fixedGoods);
        String data = "info | price | discount | category | score ";
        data += "\n";
        data+=good1.getDetailInfo()+" | "+good1.getPrice()+" | "+good1.getsalePercentageAmount()
                +" | "+good1.getCategory()+" | "+good1.getAverageScore();
        data += "\n";
        data+=good2.getDetailInfo()+" | "+good2.getPrice()+" | "+good2.getsalePercentageAmount()
                +" | "+good2.getCategory()+" | "+good2.getAverageScore();
        data+="\n\n\n";
        data+="brand | stock | categoryAttributes | goodSituation ";
        data+="\n";
        data+=good1.getBrand()+" | "+good1.getStock()+" | "+good1.getCategorySpecialAttributes()+" | "+good1
                .getSituation().toString();
        data+="\n";
        data+=good2.getBrand()+" | "+good2.getStock()+" | "+good2.getCategorySpecialAttributes()+" | "+good2
                .getSituation().toString();
        return data;
    }

    public static ArrayList<String> showComments(String goodName) {
        ArrayList<String> show = new ArrayList<>();
        Good good = Good.getGoodByName(goodName, ManageInfo.allGoods);
        for (Comment c:
             good.getComments()){
            if(c.getSituation()== Comment.OpinionSituation.CONFIRMED) show.add(c.toString());
        }
        return show;
    }
    public static boolean addComment(String goodName, String title , String content){
        if(Buyer.currentBuyer == null) return false;
        Good good = Good.getGoodByName(goodName, ManageInfo.allGoods);
        if(!good.getBuyers().contains(good)) return false;
        UserHandler.currentBuyer.addComment(good,title,content);
        return true;
    }
}
