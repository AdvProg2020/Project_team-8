package Model;

public  class UserHandler {
        public static User currentUser;
        public static Cart currentCart;
        public static Buyer currentBuyer;
        public static Seller currentSeller;
        public static Manager currentManager;
        public static boolean isLogeIn(){
                if(currentUser == null)
                        return false;
                return true;
        }
        public static void start(){
                currentCart = new Cart();
        }
}
