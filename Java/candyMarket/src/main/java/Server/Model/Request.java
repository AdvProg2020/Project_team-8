package Server.Model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Request {
    public static enum requestType{
        CREATE_GOOD,EDIT_GOOD,REMOVE_GOOD,SELLER_REGISTER,CREATE_SALE,EDIT_SALE
    }
    public static enum state {
        CHECKING,ACCEPTED,DECLINED
    }
    private String stateString;
    @Enumerated(EnumType.STRING)
    public state requestState;
    public static requestType requestType;
    @ManyToOne
    private Good good;
    @ManyToOne
    private Sale sale;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Id
    private int requestId;
    @ManyToOne
    private Seller seller;
    private String requestCommand;
    public static List<Request> sellersRequest;
    public Request(){}
    public Request(Request.requestType requestType) {
        this.requestState = state.CHECKING;
        this.requestType = requestType;
        this.requestId = ManageInfo.allRequests.size();
        this.stateString = "checking";
    }
    public static Request getRequestById(int requestId){
        for (Request request : ManageInfo.allRequests) {
            if (request.requestId == requestId)
                return request;
        }
        return null;
    }

}