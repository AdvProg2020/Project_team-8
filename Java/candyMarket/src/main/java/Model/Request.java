package Model;

import java.util.ArrayList;

public class Request {
    public static enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    public static enum state {
        CHECKING,ACCEPTED,DECLINED
    }
    private String stateString;
    public state requestState;
    public static requestType requestType;
    private Good good;
    private Sale sale;
    private int requestId;
    private Seller seller;
    private String requestCommand;
    private static int lastId = 0;
    public static ArrayList<Request> sellersRequest;

    public Request(Request.requestType requestType) {
        this.requestState = state.CHECKING;
        this.requestType = requestType;
        ManageInfo.allRequests.add(this);
        this.requestId = ++lastId;
        this.stateString = "checking";
    }

    public String getStateString() {
        return stateString;
    }

    public Good getGood() {
        return good;
    }

    public Sale getSale() {
        return sale;
    }

    public int getRequestId() {
        return requestId;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getRequestCommand() {
        return requestCommand;
    }

    public void createRegisterSellerRequest(Seller seller) {
        this.seller = seller;
        this.requestCommand = "Register seller " + this.getSeller().getSellerCompanyName();
    }

    public String viewSellerRegisterDetails() {
        return this.seller.viewUserPersonalInfo() +"\n" + this.seller.viewCompanyInformation();
    }
    public String viewAddProductDetail() {
        return this.good.viewProductDetails();
    }

    public void createAddProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Add product " + this.getGood().getName();
    }

    public void createEditProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Edit product " + this.getGood().getName();
    }

    public void createDeleteProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Delete product " + this.getGood().getName();
    }

    public String viewInfo() {
        switch (this.requestType) {
            case SELLER_REGISTER:
                return this.viewSellerRegisterDetails();
            case CREATE_GOOD:
                return this.viewAddProductDetail();
            case EDIT_GOOD:
                return this.viewAddProductDetail();
            case REMOVE_GOOD:
                return this.viewAddProductDetail();
        }
        return null;
    }

    public void acceptRequest() {
        switch (this.requestType) {
            case SELLER_REGISTER:
                ManageInfo.allSellers.add(this.getSeller());
                ManageInfo.allUsers.add(this.getSeller());
                break;
            case CREATE_GOOD:
                ManageInfo.allGoods.add(this.getGood());
                this.getGood().getSeller().addGood(this.getGood());
                break;
            case EDIT_GOOD:
                Good goodToBeRemoved = null;
                for (Good good : ManageInfo.allGoods) {
                    if (good.getName().equals(this.getGood().getName())) {
                        goodToBeRemoved = good;
                    }
                }
                ManageInfo.allGoods.remove(goodToBeRemoved);
                goodToBeRemoved.getSeller().getMyGoods().add(this.getGood());
                ArrayList<Good> goods = goodToBeRemoved.getSeller().getMyGoods();
                goods.remove(goodToBeRemoved);
                goodToBeRemoved.getSeller().setMyGoods(goods);
                ManageInfo.allGoods.add(this.getGood());
                break;
            case REMOVE_GOOD:
                ManageInfo.allGoods.remove(this.getGood());
                this.getGood().getSeller().getMyGoods().add(this.getGood());
                ArrayList<Good> products = this.getSeller().getMyGoods();
                products.remove(this.getGood());
                this.getGood().getSeller().setMyGoods(products);
                ManageInfo.allGoods.add(this.getGood());
                break;
        }
        this.requestState = state.ACCEPTED;
        stateString = "accepted";
    }

    public void declineRequest() {
        this.requestState = state.DECLINED;
        stateString = "declined";
    }

    public static ArrayList<Request> getUncheckedRequests() {
        ArrayList<Request> uncheckedRequests = new ArrayList<>();
        for (Request request : ManageInfo.allRequests) {
            if (request.requestState.equals(state.CHECKING)) {
                uncheckedRequests.add(request);
            }
        }
        return uncheckedRequests;
    }

    public void sellerAddGood(Good good) {

    }

    public void sellerRemoveGood(Good good){

    }
    public static Boolean isThereRequestWithId(int id){
        for (Request request : ManageInfo.allRequests) {
            if (request.requestId == id)
                return true;
        }
        return false;
    }

    public static Request getRequestById(int requestId){
        for (Request request : ManageInfo.allRequests) {
            if (request.requestId == requestId)
                return request;
        }
        return null;
    }

    public static void setSellerRequest() {
        sellersRequest = new ArrayList<>();
        for (Request request : ManageInfo.allRequests) {
            if (request.good != null) {
                if (UserHandler.currentSeller == request.good.getSeller()) {
                    sellersRequest.add(request);
                }
            }
        }
    }

    public void sellerEditSale(Sale sale) {

    }
    public void sellerAddSale(Sale sale) {

    }

}