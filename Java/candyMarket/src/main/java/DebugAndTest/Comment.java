package DebugAndTest;

public class Comment {
    private String brand = null;
    private int doors = 0;
    public Comment(String brand,int doors) {
        this.doors =doors;
        this.brand = brand;
    }
    public Comment(){}
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
