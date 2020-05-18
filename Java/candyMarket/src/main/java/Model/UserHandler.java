package Model;

public  class UserHandler {
        public static User currentUser = new User();
        public static Cart currentCart = currentUser.getCart();
        public static Buyer currentBuyer;
        public static Seller currentSeller;
        public static Manager currentManager;
}
