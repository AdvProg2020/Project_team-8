package Model;

import java.util.ArrayList;

public class Request {
    enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    private Good good;
    private Sale sale;
    private int requestId;
    private Seller seller;
    private String request;
    ArrayList<Request> requests=ManageInfo.allRequests;
    public static Request getRequestById(String requestId) {
        return null;
    }
    public void sellerEditSale(Sale sale) {

    }
    public void sellerAddSale(Sale sale) {

    }
    public void sellerEditGood(Good good) {

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

    public void sellerAddGood(Good good) {

    }

    public void sellerRemoveGood(Good good){

    }
    public static Boolean isRequestWithId(int id){
        return null;
    }
    public static Request getRequestById(int id){
        return null;
    }
}
