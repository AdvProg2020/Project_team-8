package Controller;

import Model.FilterAndSort;
import Model.Good;

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
        data+="discount : "+good.getSalePercentageAmount()+"%"+"\n";
        data+="category : "+good.getCategory().getName()+"\n";
        data+="score : "+good.getAverageScore()+"\100"+"\n";
        return data;
    }

    public static boolean addProductToCart(String name) {
        return false;
    }

    public static void selectSellerToBuyFrom() {
        ;
    }

    public static String attributes(String goodName)
    {
        Good good = Good.getGoodByName(goodName,Good.fixedGoods);
        String data = "info | price | discount | category | score ";
        data += "\n";
        data+=good.getDetailInfo()+" | "+good.getPrice()+" | "+good.getSalePercentageAmount()
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
        data+=good1.getDetailInfo()+" | "+good1.getPrice()+" | "+good1.getSalePercentageAmount()
                +" | "+good1.getCategory()+" | "+good1.getAverageScore();
        data += "\n";
        data+=good2.getDetailInfo()+" | "+good2.getPrice()+" | "+good2.getSalePercentageAmount()
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

    public static String showComments() {
        return null;
    }
    public static void addComment(String title , String content){

    }
    public static boolean CanComment() {
        return false;
    }
}
