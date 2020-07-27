package Client.Model;

import Client.Controller;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Chat {
    private String buyer;
    private String supporter;
    @ElementCollection
    private List<String> texts;
    @Id
    private int id;
    public Chat(String buyer, String supporter) {
        this.buyer = buyer;
        this.supporter = supporter;
        this.texts = new ArrayList<>();
        this.id = ManageInfo.allChats.size();
        Controller.saveOrUpdateObject(this);
    }
    public Chat(){}
    public String getBuyer() {
        return buyer;
    }

    public String getSupporter() {
        return supporter;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void addText(String sender, String text) {
        if (sender.equals(buyer))
            texts.add("b" + text);
        if (sender.equals(supporter))
            texts.add("s" + text);
        Controller.saveOrUpdateObject(this);
    }

    public static Chat getChatByBuyerSupporter(String buyer, String supporter) {
        for (Chat chat : ManageInfo.allChats) {
            if (chat.getBuyer().equals(buyer) && chat.getSupporter().equals(supporter)) {
                return chat;
            }
        }
        return null;
    }

    public static void newSupporterAdded(String supporter) {
        for (Buyer buyer : ManageInfo.allBuyers) {
            new Chat(buyer.getUsername(), supporter);
        }
    }

    public static void newBuyerAdded(String buyer) {
        for (Supporter supporter : ManageInfo.allSupporters) {
            new Chat(buyer, supporter.getUsername());
        }
    }
    public static Chat getChatById(int id){
        for (Chat chat : ManageInfo.allChats) {
            if(chat.id == id)
                return chat;
        }
        return null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
