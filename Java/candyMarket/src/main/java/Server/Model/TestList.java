package Server.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestList {
    public TestList(){}
    private String s;
    @Id
    private int id;

    public TestList(String s, int id) {
        this.s = s;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
