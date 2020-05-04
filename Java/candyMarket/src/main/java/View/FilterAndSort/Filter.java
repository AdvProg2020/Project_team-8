package View.FilterAndSort;

import View.ViewException;

import java.util.ArrayList;

public class Filter{
    private int id;
    private boolean enable;
    private FilterAndSort.filtersType filterType;
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

    public FilterAndSort.filtersType getFilterType() {
        return filterType;
    }

    public Filter(int id) {
        enable = false;
        filterType=FilterAndSort.filtersType.values()[id-1];
        this.id = id;
    }
    public static Filter getFilterById(int id,ArrayList<Filter> filters) throws ViewException {
        for (Filter f:
             filters) {
            if(f.id == id) return f;
        }
        throw ViewException.invalidNumber();
    }
}
