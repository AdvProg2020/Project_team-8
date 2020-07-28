package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Request {
    public static enum type{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    public static enum state {
        CHECKING,ACCEPTED,DECLINED
    }
    private String stateString;
    @Enumerated(EnumType.STRING)
    public state requestState;
    @Enumerated(EnumType.STRING)
    public type requestType;
    @ManyToOne
    private Good good;
    @ManyToOne
    private Sale sale;
    @Id
    private int requestId;
    @ManyToOne
    private Seller seller;
    private String requestCommand;
    public static List<Request> sellersRequest;
    public Request(){}
    public Request(Request.type requestType) {
        this.requestState = state.CHECKING;
        this.requestType = requestType;
        this.requestId = ManageInfo.allRequests.size();
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
        this.requestCommand = "Register seller " + seller.getSellerCompanyName();
        Controller.saveOrUpdateObject(this);
    }

    public String viewSellerRegisterDetails() {
        Seller seller = this.seller;
        return seller.viewUserPersonalInfo() +"\n" + seller.viewCompanyInformation();
    }
    public String viewAddProductDetail() {
        Good good = this.good;
        return good.viewProductDetails();
    }

    public void createAddProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Add product " + this.getGood().getName();
        Controller.saveOrUpdateObject(this);
    }

    public void createEditProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Edit product " + this.getGood().getName();
        Controller.saveOrUpdateObject(this);
    }

    public void createDeleteProductRequest(Good good) {
        this.good = good;
        this.requestCommand = "Delete product " + this.getGood().getName();
        Controller.saveOrUpdateObject(this);
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
                Seller.registerConfirmation(this.getSeller());
                break;
            case CREATE_GOOD:
                Controller.saveOrUpdateObject(good);
                this.getGood().getSeller().addGood(this.getGood());
                Controller.saveOrUpdateObject(this.getGood().getSeller());
                break;
            case EDIT_GOOD:
                Good goodToBeRemoved = null;
                for (Good good : ManageInfo.allGoods) {
                    if (good.getName().equals(this.getGood().getName())) {
                        goodToBeRemoved = good;
                    }
                }
                Controller.deleteObject("Good",good.getName());
                goodToBeRemoved.getSeller().getGoods().add(this.getGood());
                List<Good> goods = goodToBeRemoved.getSeller().getGoods();
                goods.remove(goodToBeRemoved);
                goodToBeRemoved.getSeller().setGoods(goods);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Controller.saveOrUpdateObject(good);
                Controller.saveOrUpdateObject(goodToBeRemoved.getSeller());
                break;
            case REMOVE_GOOD:
                Controller.deleteObject("Good",good.getName());
                Seller seller = this.getGood().getSeller();
                seller.getGoods().remove(this.getGood());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Controller.saveOrUpdateObject(seller);
                List<Good> products = this.getSeller().getGoods();
                products.remove(this.getGood());
                this.getGood().getSeller().setGoods(products);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Controller.saveOrUpdateObject(good);
                break;
        }
        this.requestState = state.ACCEPTED;
        stateString = "accepted";
        Controller.saveOrUpdateObject(this);
    }

    public void declineRequest() {
        this.requestState = state.DECLINED;
        stateString = "declined";
        Controller.saveOrUpdateObject(this);
    }

    public static List<Request> getUncheckedRequests() {
        List<Request> uncheckedRequests = new ArrayList<>();
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
            if (request.getGood() != null) {
                if (UserHandler.currentSeller == request.getGood().getSeller()) {
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