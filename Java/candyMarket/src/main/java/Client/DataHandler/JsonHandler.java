package Client.DataHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JsonHandler {
    public static Gson gson = new Gson();
    public static <T> List<T> getAllData(Class<T[]> tClass) throws IOException {
        int lastIndexOfDot = tClass.getName().lastIndexOf(".");
        int lastIndexOfSim = tClass.getName().lastIndexOf(";");
        String name = tClass.getName().substring(lastIndexOfDot+1,lastIndexOfSim);
        String response = MessageHandler.sendGetAllDataMessage(name);
        ArrayList<Class> d = new ArrayList<>();
        List<T> objects= new LinkedList<>(Arrays.asList(gson.fromJson(response,tClass)));
        return objects;
    }
    public static <T> T getDataById(Class<T> tClass,Object id) throws IOException {
        int lastIndexOfDot = tClass.getName().lastIndexOf(".");
        String name = tClass.getName().substring(lastIndexOfDot+1);
        String response = MessageHandler.sendGetByIdMessage(id,name);
        Object object = gson.fromJson(response,tClass);
        return tClass.cast(object);
    }
    public static void setData(Object object) {
        String className = object.getClass().getName();
        int lastIndexOfDot = className.lastIndexOf(".");
        className = className.substring(lastIndexOfDot+1);
        String json = gson.toJson(object);
        MessageHandler.sendSetDataMessage(className,json);
    }
    public static void updateOrSaveData(String json,String className) throws ClassNotFoundException, JsonProcessingException {
        Object object = gson.fromJson(json,Class.forName("Client.Model."+className));
        DataAccessor.updateOrSaveObject(object);
    }
}
