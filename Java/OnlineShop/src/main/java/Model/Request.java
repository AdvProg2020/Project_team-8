package Model;

public class Request {
    enum requestType{
        CREATE_GOOD,EDIT_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    private Seller seller;
    private String request;
}
