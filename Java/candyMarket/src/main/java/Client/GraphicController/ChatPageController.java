package Client.GraphicController;

import Client.GraphicView.CustomChatMessages;
import Client.GraphicView.CustomProfileForChat;
import Client.Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatPageController implements Initializable {
    public static ChatPageController chatPageController;

    @FXML private ScrollPane chatScrollPane;
    @FXML private ScrollPane usersScrollPane;

    @FXML private TextArea commentText;

    private static ArrayList<CustomChatMessages> chats = new ArrayList<>();
    private static ArrayList<CustomProfileForChat> chatters = new ArrayList<>();

    private Chat chat;

    private VBox allChats;
    private VBox allUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chat = null;
        chatPageController = this;
        allChats = new VBox();
        allUsers = new VBox();
        allChats.setSpacing(5);
        allUsers.setSpacing(10);
        allUsers.setStyle("-fx-background-color: #ffffff;");
        if (UserHandler.isSupporter())
            chatters = createBuyersProfileBoxes();
        else
            chatters = createSupportersProfileBoxes();
        allUsers.getChildren().addAll(chatters);
        usersScrollPane.setContent(allUsers);
    }

    private ArrayList<CustomProfileForChat> createSupportersProfileBoxes() {
        ArrayList<CustomProfileForChat> customProfileForChats = new ArrayList<>();
        for (Supporter supporter : ManageInfo.allSupporters) {
            customProfileForChats.add(new CustomProfileForChat(supporter));
        }
        return customProfileForChats;
    }

    private ArrayList<CustomProfileForChat> createBuyersProfileBoxes() {
        ArrayList<CustomProfileForChat> customProfileForChats = new ArrayList<>();
        for (Buyer buyer : ManageInfo.allBuyers) {
            customProfileForChats.add(new CustomProfileForChat(buyer));
        }
        return customProfileForChats;
    }

    public void setMessages(User user){
        Chat chat;
        if (UserHandler.isSupporter())
            chat = Chat.getChatByBuyerSupporter(user.getUsername(), UserHandler.getCurrentUser().getUsername());
        else
            chat = Chat.getChatByBuyerSupporter(UserHandler.getCurrentUser().getUsername(), user.getUsername());
        chats.clear();
        chats = createChatBoxes(chat);
        allChats.getChildren().clear();
        allChats.getChildren().addAll(chats);
        chatScrollPane.setContent(allChats);
        this.chat = chat;
    }

    private ArrayList<CustomChatMessages> createChatBoxes(Chat chat) {
        ArrayList<CustomChatMessages> customChatMessages = new ArrayList<>();
        for (String text : chat.getTexts()) {
            if (text.charAt(0) == 'b')
                customChatMessages.add(new CustomChatMessages(chat.getBuyer(), text.substring(1)));
            else
                customChatMessages.add(new CustomChatMessages(chat.getSupporter(), text.substring(1)));
        }
        return customChatMessages;
    }

    public void sendMessage(ActionEvent actionEvent) {
        if (chat == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select a chat first!");
            alert.show();
        } else if (commentText.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Content is empty");
            alert.show();
        } else {
            chat.addText(UserHandler.getCurrentUser().getUsername(), commentText.getText());
            allChats.getChildren().add(new CustomChatMessages(UserHandler.getCurrentUser().getUsername(), commentText.getText()));
            chatScrollPane.setContent(allChats);
        }
    }

    public void showWhoAmITalkingTo(CustomProfileForChat profileForChat) {
        for (CustomProfileForChat chatter : chatters) {
            if (profileForChat == chatter)
                chatter.setBackgroundChosen();
            else
                chatter.setBackgroundUnChosen();
        }
    }
}
