package Server.DataHandler.Socket;

import com.google.gson.Gson;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class JsonHandler {
    public static Gson gson = new Gson();
    public static void updateOrSaveData(String json,String className) throws ClassNotFoundException{
        Object object = gson.fromJson(json,Class.forName("Server.Model."+className));
        DataAccessor.updateOrSaveObject(object);
    }
    public static void sendDataById(String className, String id, Socket clientSocket) throws IOException {
        Object object = DataAccessor.sendObject(className, id);
        String json = gson.toJson(object);
        MessageHandler.sendSetDataMessage(clientSocket,json);
    }
    public static void sendAllData(String className,Socket clientSocket) throws ClassNotFoundException, IOException {
        List<Object> objects = DataAccessor.getObjectsByClassName(className);
        //className = "class [Server.Client.Model."+className+";";
        String json = gson.toJson(objects);
        MessageHandler.sendSetDataMessage(clientSocket,json);
    }
}
