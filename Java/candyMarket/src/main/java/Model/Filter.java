package Model;

import Model.FilterAndSort;
import View.ViewException;

public class Filter{
    private int id;
    private boolean enable;
    private String name;
    public void run() throws ViewException {
        this.setEnable(true);
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public int getId() {
        return id;
    }

    public boolean isEnable() {
        return enable;
    }


    public Filter(int id) {
        enable = false;
        name= FilterAndSort.filtersTypes.values()[id-1].toString();
        this.id = id;
    }
    public Filter(int id,String name) {
        enable = false;
        this.name=name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
