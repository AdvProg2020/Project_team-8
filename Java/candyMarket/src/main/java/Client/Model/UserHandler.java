package Client.Model;

import Client.DataHandler.MessageHandler;
import Client.GraphicController.BorderPaneController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {
        public static List<User> onlineUsers = new ArrayList<>();
        public static User getCurrentUser() {
                return currentUser;
        }

        public static void setCurrentUser(User currentUser) {
                UserHandler.currentUser = currentUser;
                BorderPaneController.borderPaneController.currentUserName = currentUser.getUsername();
        }
        private static User currentUser;
        public static Cart currentCart;
        public static Buyer currentBuyer;
        public static Seller currentSeller;
        public static Manager currentManager;
        public static Supporter currentSupporter;

        public static boolean isLogeIn(){
                return currentUser != null;
        }

        public static boolean isManager() {return currentManager != null;}

        public static boolean isSeller() {return currentSeller != null;}

        public static boolean isBuyer() {return currentBuyer != null;}

        public static boolean isSupporter() {return currentSupporter != null;}

        public static void loggingIn(String userName) {
                User user = User.getUserByUsername(userName);
                currentUser = user;
                try {
                        MessageHandler.sendLoginMessage();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                onlineUsers.add(user);
                switch (currentUser.getType()) {
                        case BUYER:
                                currentBuyer = (Buyer)user;
                                Buyer.currentBuyer = currentBuyer;
                                break;
                        case SELLER:
                                currentSeller = (Seller)user;
                                Seller.currentSeller = currentSeller;
                                break;
                        case MANAGER:
                                currentManager = (Manager)user;
                                Manager.currentManager = currentManager;
                                break;
                        case SUPPORTER:
                                currentSupporter = (Supporter)user;
                                Supporter.currentSupporter = currentSupporter;
                                break;
                }
        }

        public static void loggingOut() {
                try {
                        MessageHandler.sendLogoutMessage();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                onlineUsers.remove(currentUser);
                currentUser = null;
                currentManager = null;
                currentBuyer = null;
                currentSeller = null;
                currentSupporter = null;
                Seller.currentSeller = null;
                Buyer.currentBuyer = null;
                Manager.currentManager = null;
                Supporter.currentSupporter = null;
        }
        public static User getOnlineUserByUserName(String username){
                for (User onlineUser : onlineUsers) {
                        if(onlineUser.username.equals(username))
                                return onlineUser;
                }
                return null;
        }
}
