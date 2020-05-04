package View.FilterAndSort;

import View.ViewException;

import java.util.ArrayList;

public class SubFilter {
    private int id;
    private String name;
    private boolean enable;
    public static ArrayList<SubFilter> createAllSubFilters(ArrayList<String> list){
        ArrayList<SubFilter> SubFilters = new ArrayList<SubFilter>();
        for(int i=0;i<list.size();i++){
            SubFilters.add(new SubFilter(i+1,list.get(0)));
        }
        return SubFilters;
    }
    public static SubFilter getSubFilterById(int id,ArrayList<SubFilter> subFilters) throws ViewException {
        for (SubFilter s:
                subFilters) {
            if(s.id == id) return s;
        }
        throw ViewException.invalidNumber();
    }
    public SubFilter(int id, String name) {
        this.id = id;
        this.name = name;
        this.enable=false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }
}
