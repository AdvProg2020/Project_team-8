package Server.DataHandler;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class User {
    @Id
    private String name;
    private String location;
    private String items;
    private String test;
    public User(){}
    public User(String test,String name, String location) {
        items="";
        this.test = test;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void addItems(String s){
        items+=s+",";
    }
    public ArrayList<String> getItems() {
        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList(items.split(",")));
        return strings;
    }
    public void setItems(ArrayList<String> items) {
        this.items = items.toString();
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
