package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat {
    private String buyer;
    private String supporter;
    private List<String> texts;

    public Chat(String buyer, String supporter) {
        this.buyer = buyer;
        this.supporter = supporter;
        this.texts = new ArrayList<>();
        ManageInfo.allChats.add(this);
    }

    public String getBuyer() {
        return buyer;
    }

    public String getSupporter() {
        return supporter;
    }

    public ArrayList<String> getTexts() {
        return (ArrayList<String>) texts;
    }

    public void addText(String sender, String text) {
        if (sender.equals(buyer))
            texts.add("b" + text);
        if (sender.equals(supporter))
            texts.add("s" + text);
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
}
