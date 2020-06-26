package Model;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class Request {
    public enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE,ADD_COMMENT
    }
    public requestType requestType;
    public static requestType type;
    private Good good;
    private Sale sale;
    private int requestId;
    private Seller seller;
    private String request;
    public static ArrayList<Request> requests = ManageInfo.allRequests;

    public Request(Request.requestType requestType) {
        this.requestType = requestType;
        requests.add(this);
        this.requestId = requests.size();
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

    public String getRequest() {
        return request;
    }

    public void createRegisterSellerRequest(Seller seller) {
        this.request = "register seller";
        this.seller = seller;
    }

    public String viewSellerRegisterDetails() {
        return this.seller.viewUserPersonalInfo() +"\n" + this.seller.viewCompanyInformation();
    }
    public String viewAddProductDetail() {
        return this.good.viewProductDetails();
    }

    public void createAddProductRequest(Good good) {
        this.request = "add product";
        this.good = good;
    }

    public static String viewDetails(Request request) {
        System.out.println(requests.toString());
        switch (request.requestType) {
            case SELLER_REGISTER:
                return request.viewSellerRegisterDetails();
            case CREATE_GOOD:
                return request.viewAddProductDetail();
        }

        return null;
    }

    public void acceptRequest() {
        switch (this.requestType) {
            case SELLER_REGISTER:
                this.getSeller().confirmSeller();
                break;
            case CREATE_GOOD:
                this.getGood().confirmProduct();
        }
        requests.remove(this);
    }

    public void declineRequest() {
        switch (this.requestType) {
            case SELLER_REGISTER:
                ManageInfo.allUsers.remove(this.getSeller());
                break;
            case CREATE_GOOD:
                Good.unconfirmedGoods.remove(this.getGood());
                break;
        }
        requests.remove(this);
    }

    public void sellerAddGood(Good good) {

    }

    public void sellerRemoveGood(Good good){

    }
    public static Boolean isThereRequestWithId(int id){
        for (Request request : requests) {
            if (request.requestId == id)
                return true;
        }
        return false;
    }

    public static Request getRequestById(int requestId){
        for (Request request : requests) {
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