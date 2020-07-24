package BothUtl.DebugAndTest;

public class User {
    private String name;

    public User(String name, Product product) {
        this.name = name;
        this.product = product;
    }
    public User(){}
    private Product product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
