package Model;

public  class UserHandler {
        public static User currentUser = new User();
        public static Cart currentCart = currentUser.getCart();
        Buyer currentBuyer;
        Seller currentSeller;
        Manager currentManager;
}
