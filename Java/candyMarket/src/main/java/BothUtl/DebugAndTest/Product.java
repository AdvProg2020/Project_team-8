package BothUtl.DebugAndTest;

public class Product {
    public Product(){}
    public Product(User user, String name) {
        this.user = user;
        this.name = name;
    }

    private User user;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
