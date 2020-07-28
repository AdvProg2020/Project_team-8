package Server.DataHandler.Socket;

import Server.DataHandler.DataBase.DBHandler;
import Server.DataHandler.DataBase.DBManager;
import Server.Model.*;
import com.sun.webkit.graphics.WCFontCustomPlatformData;
import javassist.bytecode.stackmap.TypeData;

import java.util.ArrayList;
import java.util.List;

public class DataAccessor {
    public static void deleteDataById(String className,String id){
        if(className.equals("Manager")) {
            DBManager.deleteObject(Manager.getUserByUsername(id));
            ManageInfo.allManagers.remove(Manager.getUserByUsername(id));
            ManageInfo.allUsers.remove(Manager.getUserByUsername(id));
        }
        if(className.equals("Good")) {
            DBManager.deleteObject(Good.getGoodByName(id));
            ManageInfo.allGoods.remove(Good.getGoodByName(id));
        }
        if(className.equals("Buyer")) {
            DBManager.deleteObject(Buyer.getUserByUsername(id));
            ManageInfo.allBuyers.remove(Buyer.getUserByUsername(id));
            ManageInfo.allUsers.remove(Buyer.getUserByUsername(id));
        }
        if(className.equals("Seller")) {
            DBManager.deleteObject(Seller.getUserByUsername(id));
            ManageInfo.allSellers.remove(Seller.getUserByUsername(id));
            ManageInfo.allUsers.remove(Seller.getUserByUsername(id));
        }
        if(className.equals("Supporter")) {
            DBManager.deleteObject(Supporter.getUserByUsername(id));
            ManageInfo.allSupporters.remove(Supporter.getUserByUsername(id));
            ManageInfo.allUsers.remove(Supporter.getUserByUsername(id));
        }
        if(className.equals("Category")) {
            DBManager.deleteObject(Category.getCategoryByName(id));
            ManageInfo.allCategories.remove(Category.getCategoryByName(id));
        }
        if(className.equals("Request")) {
            DBManager.deleteObject(Request.getRequestById(Integer.parseInt(id)));
            ManageInfo.allRequests.remove(Request.getRequestById(Integer.parseInt(id)));
        }
        if(className.equals("Discount")) {
            DBManager.deleteObject(Discount.getDiscountByCode(id));
            ManageInfo.allDiscounts.remove(Discount.getDiscountByCode(id));
        }
        if(className.equals("Comment")) {
            DBManager.deleteObject(Comment.getCommentById(Integer.parseInt(id)));
            ManageInfo.allComments.remove(Comment.getCommentById(Integer.parseInt(id)));
        }
        if(className.equals("Sale")) {
            DBManager.deleteObject(Integer.parseInt(id));
            ManageInfo.allSales.remove(Sale.getSaleById(Integer.parseInt(id)));
        }
        if(className.equals("Score")) {
            DBManager.deleteObject(Score.getScoreById(Integer.parseInt(id)));
            ManageInfo.allScores.remove(Score.getScoreById(Integer.parseInt(id)));
        }
        if(className.equals("BuyLog")) {
            DBManager.deleteObject(BuyLog.getBuyLogById(Integer.parseInt(id)));
            ManageInfo.allBuyLogs.remove(BuyLog.getBuyLogById(Integer.parseInt(id)));
        }
        if(className.equals("SellLog")) {
            DBManager.deleteObject(SellLog.getSellLogById(Integer.parseInt(id)));
            ManageInfo.allSellLogs.remove(SellLog.getSellLogById(Integer.parseInt(id)));
        }
        if(className.equals("Chat")) {
            DBManager.deleteObject(Chat.getChatById(Integer.parseInt(id)));
            ManageInfo.allChats.remove(Chat.getChatById(Integer.parseInt(id)));
        }
        if(className.equals("Auction")) {
            DBManager.deleteObject(Auction.getAuctionById(Integer.parseInt(id)));
            ManageInfo.allAuctions.remove(Auction.getAuctionById(Integer.parseInt(id)));
        }
    }
    public static void updateOrSaveObject(Object object){
        if(object instanceof Good)
        {
            Good good = (Good) object;
            Good currentGood =  Good.getGoodByName(good.getName());
            if (currentGood != null) {
                DBManager.deleteObject(currentGood);
                ManageInfo.allGoods.remove(currentGood);
            }
            ManageInfo.allGoods.add(good);
        }
        else if(object instanceof Category)
        {
            Category category = (Category) object;
            Category currentCategory = Category.getCategoryByName(category.getName());
            if (currentCategory != null) {
                DBManager.deleteObject(currentCategory);
                ManageInfo.allCategories.remove(currentCategory);
            }
            ManageInfo.allCategories.add(category);
        }
        else if(object instanceof BuyLog)
        {
            BuyLog buyLog = (BuyLog) object;
            BuyLog currentBuyLog = BuyLog.getBuyLogById(buyLog.getId());
            if (currentBuyLog != null) {
                DBManager.deleteObject(currentBuyLog);
                ManageInfo.allBuyLogs.remove(currentBuyLog);
            }
            ManageInfo.allBuyLogs.add(buyLog);
        }
        else if(object instanceof SellLog)
        {
            SellLog sellLog = (SellLog) object;
            SellLog currentSellLog = SellLog.getSellLogById(sellLog.getId());
            if (currentSellLog != null) {
                DBManager.deleteObject(currentSellLog);
                ManageInfo.allSellLogs.remove(currentSellLog);
            }
            ManageInfo.allSellLogs.add(sellLog);
        }
        else if(object instanceof Comment)
        {
            Comment comment  = (Comment) object;
            Comment currentComment = Comment.getCommentById(comment.getId());
            if (currentComment != null) {
                DBManager.deleteObject(currentComment);
                ManageInfo.allComments.remove(currentComment);
            }
            ManageInfo.allComments.add(comment);
        }
        else if(object instanceof Score)
        {
            Score score = (Score) object;
            Score currentScore = Score.getScoreById(score.getId());
            if (currentScore != null) {
                DBManager.deleteObject(currentScore);
                ManageInfo.allScores.remove(currentScore);
            }
            ManageInfo.allScores.add(score);
        }
        else if(object instanceof Discount)
        {
            Discount discount = (Discount) object;
            Discount currentDiscount = Discount.getDiscountByCode(discount.getCode());
            if (currentDiscount != null) {
                DBManager.deleteObject(currentDiscount);
                ManageInfo.allDiscounts.remove(currentDiscount);
            }
            ManageInfo.allDiscounts.add(discount);
        }
        else if(object instanceof Seller)
        {
            Seller seller = (Seller) object;
            Seller currentSeller = (Seller) Seller.getUserByUsername(seller.getUsername());
            if (currentSeller != null) {
                DBManager.deleteObject(currentSeller);
                ManageInfo.allSellers.remove(currentSeller);
            }
            ManageInfo.allSellers.add(seller);
        }
        else if(object instanceof Buyer)
        {
            Buyer buyer = (Buyer) object;
            Buyer currentBuyer = (Buyer) Buyer.getUserByUsername(buyer.getUsername());
            if (currentBuyer != null) {
                DBManager.deleteObject(currentBuyer);
                ManageInfo.allBuyers.remove(currentBuyer);
            }
            ManageInfo.allBuyers.add(buyer);
        }
        else if(object instanceof Manager)
        {
            Manager manager = (Manager) object;
            Manager currentManager = (Manager) Manager.getUserByUsername(manager.getUsername());
            if (currentManager != null) {
                DBManager.deleteObject(currentManager);
                ManageInfo.allManagers.remove(currentManager);
            }
            ManageInfo.allManagers.add(manager);
        }
        else if(object instanceof Supporter){
            Supporter supporter = (Supporter) object;
            Supporter currentSupporter = (Supporter) Supporter.getUserByUsername(supporter.getUsername());
            if (currentSupporter != null) {
                DBManager.deleteObject(currentSupporter);
                ManageInfo.allSupporters.remove(currentSupporter);
            }
            ManageInfo.allSupporters.add(supporter);
        }
        else if(object instanceof Request)
        {
            Request request = (Request) object;
            Request currentRequest = Request.getRequestById(request.getRequestId());
            if (currentRequest != null) {
                DBManager.deleteObject(currentRequest);
                ManageInfo.allRequests.remove(currentRequest);
            }
            ManageInfo.allRequests.add(request);
        }
        else if(object instanceof Sale)
        {
            Sale sale = (Sale) object;
            Sale currentSale = Sale.getSaleById(sale.getId());
            if (currentSale != null) {
                DBManager.deleteObject(currentSale);
                ManageInfo.allSales.remove(currentSale);
            }
            ManageInfo.allSales.add(sale);
        }
        else if(object instanceof Chat)
        {
            Chat chat = (Chat) object;
            Chat currentChat = Chat.getChatById(chat.getId());
            if (currentChat != null) {
                DBManager.deleteObject(currentChat);
                ManageInfo.allChats.remove(currentChat);
            }
            ManageInfo.allChats.add(chat);
        }
        else if(object instanceof Auction)
        {
            Auction auction = (Auction) object;
            Auction currentAuction = Auction.getAuctionById(auction.getId());
            if (currentAuction != null) {
                DBManager.deleteObject(currentAuction);
                ManageInfo.allAuctions.remove(currentAuction);
            }
            ManageInfo.allAuctions.add(auction);
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DBManager.saveObject(object);
    }
    public static <T>List<T> getObjectsByClassName(String className){
        if(className.equals("Manager"))
            return (List<T>) ManageInfo.allManagers;
        else if(className.equals("Good"))
            return (List<T>) ManageInfo.allGoods;
        else if(className.equals("Buyer"))
            return (List<T>) ManageInfo.allBuyers;
        else if(className.equals("Seller"))
            return (List<T>) ManageInfo.allSellers;
        else if(className.equals("Manager"))
            return (List<T>) ManageInfo.allManagers;
        else if(className.equals("Supporter"))
            return (List<T>) ManageInfo.allSupporters;
        else if(className.equals("Category"))
            return (List<T>) ManageInfo.allCategories;
        else if(className.equals("BuyLog"))
            return (List<T>) ManageInfo.allBuyLogs;
        else if(className.equals("SellLog"))
            return (List<T>) ManageInfo.allSellLogs;
        else if(className.equals("Comment"))
            return (List<T>) ManageInfo.allComments;
        else if(className.equals("Discount"))
            return (List<T>) ManageInfo.allDiscounts;
        else if(className.equals("Request"))
            return (List<T>) ManageInfo.allRequests;
        else if(className.equals("Sale"))
            return (List<T>) ManageInfo.allSales;
        else if(className.equals("Score"))
            return (List<T>) ManageInfo.allScores;
        else if(className.equals("Chat"))
            return (List<T>) ManageInfo.allChats;
        else if(className.equals("Auction"))
            return (List<T>) ManageInfo.allAuctions;
        else return null;
    }
    public static Object sendObject(String name,String id){
        if(name.equals("Manager")){
            Manager manager = (Manager) Manager.getUserByUsername(id);
            return manager;
        }
        else if(name.equals("Good")){
            Good good = Good.getGoodByName(id);
            return good;
        }
        else if(name.equals("Seller")){
            Seller seller = (Seller) Seller.getUserByUsername(id);
            return seller;
        }
        else if(name.equals("Buyer")){
            Buyer buyer = (Buyer) Buyer.getUserByUsername(id);
            return buyer;
        }
        else if(name.equals("Supporter")){
            Supporter supporter = (Supporter) Supporter.getUserByUsername(id);
            return supporter;
        }
        else if(name.equals("Category")){
            Category category = (Category) Category.getCategoryByName(id);
            return category;
        }
        else if(name.equals("Comment")){
            Comment comment = Comment.getCommentById(Integer.parseInt(id));
            return comment;
        }
        else if(name.equals("Discount")){
            Discount discount = Discount.getDiscountByCode(id);
            return discount;
        }
        else if(name.equals("BuyLog")){
            BuyLog buyLog = BuyLog.getBuyLogById(Integer.parseInt(id));
            return buyLog;
        }
        else if(name.equals("SellLog")){
            SellLog sellLog = SellLog.getSellLogById(Integer.parseInt(id));
            return sellLog;
        }
        else if(name.equals("Score")){
            Score score = Score.getScoreById(Integer.parseInt(id));
            return score;
        }
        else if(name.equals("Sale")){
            Sale sale = Sale.getSaleById(Integer.parseInt(id));
            return sale;
        }
        else if(name.equals("Request")){
            Request request = Request.getRequestById(Integer.parseInt(id));
            return request;
        }
        else if(name.equals("Chat")){
            Chat chat = Chat.getChatById(Integer.parseInt(id));
            return chat;
        }
        else if(name.equals("Auction")){
            Auction auction = Auction.getAuctionById(Integer.parseInt(id));
            return auction;
        }
        return null;
    }
}
