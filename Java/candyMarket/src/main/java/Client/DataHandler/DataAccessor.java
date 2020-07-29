package Client.DataHandler;

import Client.Model.*;

public class DataAccessor {
    public static void deleteDataById(String className,String id){
        if(className.equals("Manager")) {
            ManageInfo.allManagers.remove(Manager.getUserByUsername(id));
            ManageInfo.allUsers.remove(Manager.getUserByUsername(id));
        }
        else if(className.equals("FileGood")) {
            ManageInfo.allFileGoods.remove(FileGood.getFileGoodByName(id));
            ManageInfo.allGoods.remove(FileGood.getFileGoodByName(id));
        }
        else if(className.equals("Good"))
            ManageInfo.allGoods.remove(Good.getGoodByName(id));
        else if(className.equals("Buyer")) {
            ManageInfo.allBuyers.remove(Buyer.getUserByUsername(id));
            ManageInfo.allUsers.remove(Buyer.getUserByUsername(id));
        }
        else if(className.equals("Seller")) {
            ManageInfo.allSellers.remove(Seller.getUserByUsername(id));
            ManageInfo.allUsers.remove(Seller.getUserByUsername(id));
        }
        else if(className.equals("Supporter")) {
            ManageInfo.allSupporters.remove(Supporter.getUserByUsername(id));
            ManageInfo.allUsers.remove(Supporter.getUserByUsername(id));
        }
        else if(className.equals("Category"))
            ManageInfo.allCategories.remove(Category.getCategoryByName(id));
        else if(className.equals("Request"))
            ManageInfo.allRequests.remove(Request.getRequestById(Integer.parseInt(id)));
        else if(className.equals("Discount"))
            ManageInfo.allDiscounts.remove(Discount.getDiscountByCode(id));
        else if(className.equals("Comment"))
            ManageInfo.allComments.remove(Comment.getCommentById(Integer.parseInt(id)));
        else if(className.equals("Sale"))
            ManageInfo.allSales.remove(Sale.getSaleById(Integer.parseInt(id)));
        else if(className.equals("Score"))
            ManageInfo.allScores.remove(Score.getScoreById(Integer.parseInt(id)));
        else if(className.equals("BuyLog"))
            ManageInfo.allBuyLogs.remove(BuyLog.getBuyLogById(Integer.parseInt(id)));
        else if(className.equals("SellLog"))
            ManageInfo.allSellLogs.remove(SellLog.getSellLogById(Integer.parseInt(id)));
        else if(className.equals("Chat"))
            ManageInfo.allChats.remove(Chat.getChatById(Integer.parseInt(id)));
        else if(className.equals("Auction"))
            ManageInfo.allAuctions.remove(Auction.getAuctionById(Integer.parseInt(id)));
    }
    public static void updateOrSaveObject(Object object){
        if(object instanceof FileGood){
            FileGood fileGood = (FileGood) object;
            FileGood currentFileGood = (FileGood) Good.getGoodByName(fileGood.getName());
            if (currentFileGood != null) {
                ManageInfo.allGoods.remove((Good)currentFileGood);
                ManageInfo.allFileGoods.remove(currentFileGood);
            }
            ManageInfo.allGoods.add((Good) fileGood);
            ManageInfo.allFileGoods.add(fileGood);
        }
        else if(object instanceof Good)
        {
            Good good = (Good) object;
            Good currentGood =  Good.getGoodByName(good.getName());
            if (currentGood != null)
                ManageInfo.allGoods.remove(currentGood);
            ManageInfo.allGoods.add(good);
        }
        else if(object instanceof Category)
        {
            Category category = (Category) object;
            Category currentCategory = Category.getCategoryByName(category.getName());
            if (currentCategory != null)
                ManageInfo.allCategories.remove(currentCategory);
            ManageInfo.allCategories.add(category);
        }
        else if(object instanceof BuyLog)
        {
            BuyLog buyLog = (BuyLog) object;
            BuyLog currentBuyLog = BuyLog.getBuyLogById(buyLog.getId());
            if (currentBuyLog != null)
                ManageInfo.allBuyLogs.remove(currentBuyLog);
            ManageInfo.allBuyLogs.add(buyLog);
        }
        else if(object instanceof SellLog)
        {
            SellLog sellLog = (SellLog) object;
            SellLog currentSellLog = SellLog.getSellLogById(sellLog.getId());
            if (currentSellLog != null)
                ManageInfo.allSellLogs.remove(currentSellLog);
            ManageInfo.allSellLogs.add(sellLog);
        }
        else if(object instanceof Comment)
        {
            Comment comment  = (Comment) object;
            Comment currentComment = Comment.getCommentById(comment.getId());
            if (currentComment != null)
                ManageInfo.allComments.remove(currentComment);
            ManageInfo.allComments.add(comment);
        }
        else if(object instanceof Score)
        {
            Score score = (Score) object;
            Score currentScore = Score.getScoreById(score.getId());
            if (currentScore != null)
                ManageInfo.allScores.remove(currentScore);
            ManageInfo.allScores.add(score);
        }
        else if(object instanceof Discount)
        {
            Discount discount = (Discount) object;
            Discount currentDiscount = Discount.getDiscountByCode(discount.getCode());
            if (currentDiscount != null)
                ManageInfo.allDiscounts.remove(currentDiscount);
            ManageInfo.allDiscounts.add(discount);
        }
        else if(object instanceof Seller)
        {
            Seller seller = (Seller) object;
            Seller currentSeller = (Seller) Seller.getUserByUsername(seller.getUsername());
            if (currentSeller != null) {
                ManageInfo.allSellers.remove(currentSeller);
                ManageInfo.allUsers.remove(currentSeller);
            }
            ManageInfo.allSellers.add(seller);
            ManageInfo.allUsers.add(seller);
        }
        else if(object instanceof Buyer)
        {
            Buyer buyer = (Buyer) object;
            Buyer currentBuyer = (Buyer) Buyer.getUserByUsername(buyer.getUsername());
            if (currentBuyer != null) {
                ManageInfo.allBuyers.remove(currentBuyer);
                ManageInfo.allUsers.remove(currentBuyer);
            }
            ManageInfo.allBuyers.add(buyer);
            ManageInfo.allUsers.add(buyer);
        }
        else if(object instanceof Manager)
        {
            Manager manager = (Manager) object;
            Manager currentManager = (Manager) Manager.getUserByUsername(manager.getUsername());
            if (currentManager != null){
                ManageInfo.allManagers.remove(currentManager);
                ManageInfo.allUsers.remove(currentManager);
            }
            ManageInfo.allManagers.add(manager);
            ManageInfo.allUsers.add(manager);
        }
        else if(object instanceof Supporter){
            Supporter supporter = (Supporter) object;
            Supporter currentSupporter = (Supporter) Supporter.getUserByUsername(supporter.getUsername());
            if (currentSupporter != null) {
                ManageInfo.allSupporters.remove(currentSupporter);
                ManageInfo.allUsers.remove(currentSupporter);
            }
            ManageInfo.allSupporters.add(supporter);
            ManageInfo.allUsers.add(supporter);
        }
        else if(object instanceof Request)
        {
            Request request = (Request) object;
            Request currentRequest = Request.getRequestById(request.getRequestId());
            if (currentRequest != null)
                ManageInfo.allRequests.remove(currentRequest);
            ManageInfo.allRequests.add(request);
        }
        else if(object instanceof Sale)
        {
            Sale sale = (Sale) object;
            Sale currentSale = Sale.getSaleById(sale.getId());
            if (currentSale != null)
                ManageInfo.allSales.remove(currentSale);
            ManageInfo.allSales.add(sale);
        }
        else if(object instanceof Chat){
            Chat chat = (Chat) object;
            Chat currentChat = Chat.getChatById(chat.getId());
            if(currentChat!=null)
                ManageInfo.allChats.remove(currentChat);
            ManageInfo.allChats.add(chat);
        }
        else if(object instanceof Auction){
            Auction auction = (Auction) object;
            Auction currentAuction = Auction.getAuctionById(auction.getId());
            if(currentAuction!=null)
                ManageInfo.allAuctions.remove(currentAuction);
            ManageInfo.allAuctions.add(auction);
        }
    }
}

