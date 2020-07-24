package Client.DataHandler;

import Client.Model.ManageInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler {
    public static String sendGetByIdMessage(Object id,String className) throws IOException {

        ClientSocket.dos.writeUTF("C.getDataById#" + id.toString() + "#from#"+className);
        ClientSocket.dos.flush();
        String response;
        while (true){
            response = ClientSocket.dis.readUTF();
            if(response.startsWith("S"))
                break;
        }
        response = response.substring(2);
        return response;
    }
    public static String sendGetAllDataMessage(String className) throws IOException {
        ClientSocket.dos.writeUTF("C.getAllData#from#"+className);
        ClientSocket.dos.flush();
        String response;
        while (true){
            response = ClientSocket.dis.readUTF();
            if(response.startsWith("S"))
                break;
        }
        return response.substring(2);
    }
    public static void sendSetDataMessage(String className,String json)  {
        try {
            ClientSocket.dos.writeUTF("C.setData#to#"+className+"#"+json);
            ClientSocket.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendDeleteDataByIdMessage(String id , String className) {
        try {
            ClientSocket.dos.writeUTF("C.deleteDataById#"+id+"#from"+className);
            ClientSocket.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendDisconnectMessage() throws IOException {
        ClientSocket.dos.writeUTF("C.disconnected");
        ClientSocket.dos.flush();
    }
    public static void getMessageListener() throws IOException, ClassNotFoundException, InterruptedException {
        List<String> serverChanges = new ArrayList<>();
        DataInputStream dis = null;
        dis =new DataInputStream(ClientSocket.clientSocket.getInputStream());
        String input;
        while (true){
            input = dis.readUTF();
            System.out.println(input);
            if(input.startsWith("S"))
            {
                if(input.startsWith("S.DeleteDataById")){
                    String[] inputs = input.split("#");
                    String className = inputs[3];
                    String id = inputs[1];
                    DataAccessor.deleteDataById(className,id);
                }
                else {
                    String inputs[] = input.split("#");
                    String className = inputs[2];
                    String json = inputs[3];
                    JsonHandler.updateOrSaveData(json, className);
                    System.out.println(ManageInfo.allCategories.get(0).getName());
                }
            }
        }
    }
}
