package Model;

import java.util.ArrayList;

public class Request {
    public static enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    public static requestType requestType;
    private Good good;
    private Sale sale;
    private int requestId;
    private Seller seller;
    private String requestCommand;

    public Request(Request.requestType requestType) {
        this.requestType = requestType;
        ManageInfo.allRequests.add(this);
        this.requestId = ManageInfo.allRequests.get(ManageInfo.allRequests.size() - 1).getRequestId() + 1;
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
        this.requestCommand = "Register seller";
        this.seller = seller;
    }

    public String viewSellerRegisterDetails() {
        return this.seller.viewUserPersonalInfo() +"\n" + this.seller.viewCompanyInformation();
    }
    public String viewAddProductDetail() {
        return this.good.viewProductDetails();
    }

    public void createAddProductRequest(Good good) {
        this.requestCommand = "Add product";
        this.good = good;
    }

    public String viewInfo() {
        switch (this.requestType) {
            case SELLER_REGISTER:
                return this.viewSellerRegisterDetails();
            case CREATE_GOOD:
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
                break;
        }
        ManageInfo.allRequests.remove(this);
    }

    public void declineRequest() {
        ManageInfo.allRequests.remove(this);
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

    public void sellerEditSale(Sale sale) {

    }
    public void sellerAddSale(Sale sale) {

    }
    public void sellerEditGood(Good good) {

    }

}