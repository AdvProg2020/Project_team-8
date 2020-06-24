package Model;

public  class UserHandler {
        public static User currentUser;
        public static Cart currentCart;
        public static Buyer currentBuyer;
        public static Seller currentSeller;
        public static Manager currentManager;
        public static boolean isLogeIn(){
                return currentUser != null;
        }
        public static void start(){
                currentCart = new Cart();
        }

        public static void loggingIn(User user) {
                currentUser = user;
                switch (currentUser.getType()) {
                        case BUYER:
                                currentBuyer = (Buyer)user;
                                break;
                        case SELLER:
                                currentSeller = (Seller)user;
                                break;
                        case MANAGER:
                                currentManager = (Manager)user;
                                break;
                }
        }

        public static void loggingOut() {
                currentUser = null;
                currentManager = null;
                currentBuyer = null;
                currentSeller = null;
        }
}
