package Model;

import java.util.ArrayList;

public class Request {
    enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE,ADD_COMMENT
    }
    private Good good;
    private Sale sale;
    private int id;
    private Seller seller;
    private String request;
    ArrayList<Request> requests = ManageInfo.allRequests;
    static requestType requestType;

    //public Request(String request, Request.requestType requestType) {
      //  this.request = request;
        //this.requestType = requestType;
        //this.id=ManageInfo.allRequests.size();
    //}

    public Good getGood() {
        return good;
    }

    public Sale getSale() {
        return sale;
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
