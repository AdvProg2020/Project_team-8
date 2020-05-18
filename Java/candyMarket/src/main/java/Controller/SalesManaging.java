package Controller;

import Model.FilterAndSort;
import Model.Good;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SalesManaging {
    private static Scanner scanner;

    public static ArrayList<String> showOffs()
    {
        ArrayList<String> viewGoods = new ArrayList<String>();
        for (Good g:
                Good.fixedGoods) {
            if(g.getsalePercentageAmount()==0) continue;
            String line = "name : "+g.getName()+" | ";
            line+= "before price : "+g.getPrice()+" | ";
            line+= "after price : "+g.getPrice()*(1-g.getsalePercentageAmount())+" | ";
            viewGoods.add(g.getName());
        }
        return viewGoods;
    }

}
