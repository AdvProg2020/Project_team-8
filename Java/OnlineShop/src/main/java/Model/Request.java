package Model;

import java.util.ArrayList;

public class Request {
    enum requestType{
        CREATE_GOOD,EDIT_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    private int requestId;
    private Seller seller;
    private String request;
    private static ArrayList<Request> requests;

    public static Request getRequestById(String requestId) {
        return null;
    }
}
