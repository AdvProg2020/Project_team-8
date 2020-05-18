package Model;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class Request {
    public enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    requestType requestType;
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
        this.request = "(requestID: " + this.requestId + ") register seller";
        this.seller = seller;
    }

    public String viewSellerRegisterDetails() {
        return this.seller.viewUserPersonalInfo() +"\n" + this.seller.viewCompanyInformation();
    }

    public void createAddProductRequest(Good good) {
        this.request = "(requestID: " + this.requestId + ") add product";
        this.good = good;
    }

    public void sellerAddGood(Good good) {

    }

    public void sellerRemoveGood(Good good){

    }
    public static Boolean isThereRequestWithId(int id){
        return null;
    }

    public static Request getRequestById(int requestId){
        return null;
    }

    public void sellerEditSale(Sale sale) {

    }
    public void sellerAddSale(Sale sale) {

    }
    public void sellerEditGood(Good good) {

    }

}
