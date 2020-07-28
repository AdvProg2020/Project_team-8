package Client;


import Client.DataHandler.ClientSocket;
import Client.DataHandler.DataAccessor;
import Client.DataHandler.JsonHandler;
import Client.DataHandler.MessageHandler;
import Client.Model.*;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Controller {
    public static void saveOrUpdateObject(Object object)  {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataAccessor.updateOrSaveObject(object);
        JsonHandler.setData(object);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void deleteObject(String className,String id)  {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataAccessor.deleteDataById(className, id);
        MessageHandler.sendDeleteDataByIdMessage(id,className);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void connectToServer() throws IOException {
        while (true) {
            if(ClientSocket.connectToServer())
                break;
        }
        System.out.println("connected");
    }
    public static void disconnectFromServer() throws IOException {
        ClientSocket.disconnectFromServer();
        System.out.println("disconnected");
    }
}
