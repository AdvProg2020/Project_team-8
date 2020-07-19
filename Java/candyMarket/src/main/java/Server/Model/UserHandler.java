package Server.Model;

import GraphicController.BorderPaneController;

public class UserHandler {
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

        public static void loggingIn(String userName) {
                User user = User.getUserByUsername(userName);
                currentUser = user;
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
}
