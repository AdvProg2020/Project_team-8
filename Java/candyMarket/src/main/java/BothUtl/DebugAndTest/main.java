package BothUtl.DebugAndTest;

import Client.Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class main {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws JsonProcessingException, ClassNotFoundException {
        createData();
        String json = objectMapper.writeValueAsString(ManageInfo.allGoods);
        ManageInfo.allGoods = new LinkedList<>(Arrays.asList(objectMapper.readValue(json,Good[].class)));
        System.out.println(ManageInfo.allGoods.get(0).getName());
    }
    private static void createData(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("vazn");
        strings.add("andaze");
        Category category = new Category("lebas",strings);
        Seller seller = new Seller("Arash","a","a","a","234","456","sttt");
        ManageInfo.allUsers.add(seller);
        ManageInfo.allSellers.add(seller);
        Good good = new Good("Shalvar","dash st",12,seller,90,category,"fdsds",null,null,strings);
        ManageInfo.allGoods.add(good);
    }
    private static <T>List<T> getObjectsByClassName(){
        return (List<T>) ManageInfo.allCategories;
    }
    private static <T> List<T> getJsonList(Class<T[]> tClass,String response) throws JsonProcessingException {
        ArrayList<Class> d = new ArrayList<>();
        System.out.println(tClass);
        int lastIndexOfDot = tClass.getName().lastIndexOf(".");
        int lastIndexOfSim = tClass.getName().lastIndexOf(";");
        String name = tClass.getName().substring(lastIndexOfDot+1,lastIndexOfSim);
        System.out.println(name);
        List<T> objects= new LinkedList<>(Arrays.asList(objectMapper.readValue(response,tClass)));
        return objects;
    }
}
