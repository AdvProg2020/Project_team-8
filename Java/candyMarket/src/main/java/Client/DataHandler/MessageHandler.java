package Client.DataHandler;

import Client.Model.ManageInfo;
import Client.Model.User;
import Client.Model.UserHandler;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
            ClientSocket.dos.writeUTF("C.deleteDataById#"+id+"#from#"+className);
            ClientSocket.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendDisconnectedMessage(){
        try {
            ClientSocket.dos.writeUTF("C.disconnected");
            ClientSocket.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<User> getLoginUsers() throws IOException {
        ClientSocket.dos.writeUTF("C.getLoginUsers");
        ClientSocket.dos.flush();
        String response;
        while (true){
            response = ClientSocket.dis.readUTF();
            if(response.startsWith("S.login"))
                break;
        }
        String inputs[] = response.split("#");
        response = inputs[1];
        List<User> users = new LinkedList<>(Arrays.asList(JsonHandler.gson.fromJson(response,User[].class)));
        return users;
    }
    public static void sendLogoutMessage() throws IOException {
        String json = JsonHandler.gson.toJson(UserHandler.getCurrentUser());
        ClientSocket.dos.writeUTF("C.logout#"+json);
        ClientSocket.dos.flush();
    }
    public static void sendLoginMessage() throws IOException {
        String json = JsonHandler.gson.toJson(UserHandler.getCurrentUser());
        ClientSocket.dos.writeUTF("C.login#"+json);
        ClientSocket.dos.flush();
    }
    public static void sendIncreaseWalletMessage(String username,String pass,String money,String accountNum) throws IOException,WalletExceptions {
        if(UserHandler.token == null || UserHandler.endTimeToken<System.currentTimeMillis()){
            ClientSocket.dos.writeUTF("C.Bank#"+"get_token "+username+" "+pass);
            ClientSocket.dos.flush();
            String response;
            while (true){
                response = ClientSocket.dis.readUTF();
                if(response.startsWith("S.Bank"))
                    break;
            }
            String inputs[] = response.split("#");
            if(inputs[1].split(" ").length<=1){
                UserHandler.token = inputs[1];
                UserHandler.endTimeToken = System.currentTimeMillis()+1*3600*1000;
            }else
            throw new WalletExceptions(inputs[1]);
            ClientSocket.dos.writeUTF("C.Bank#"+"create_receipt "+UserHandler.token+" withdraw "+money+" "+accountNum+" -1");
            ClientSocket.dos.flush();
            while (true){
                response = ClientSocket.dis.readUTF();
                if(response.startsWith("S.Bank"))
                    break;
            }
            String receiptId;
            inputs = response.split("#");
            if(inputs[1].split(" ").length<=1){
                receiptId = inputs[1];
            }else
                throw new WalletExceptions(inputs[1]);
            ClientSocket.dos.writeUTF("C.Bank#"+"pay "+receiptId);
            ClientSocket.dos.flush();
            while (true){
                response = ClientSocket.dis.readUTF();
                if(response.startsWith("S.Bank"))
                    break;
            }
            inputs = response.split("#");
            if(!inputs[1].equals("done successfully"))
                throw new WalletExceptions(inputs[1]);
        }

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
                else if(input.startsWith("S.login")){
                    String[] inputs = input.split("#");
                    String json = inputs[1];
                    User user = JsonHandler.gson.fromJson(json, User.class);
                    UserHandler.onlineUsers.remove(user);
                }
                else if(input.startsWith("S.logout")){
                    String[] inputs = input.split("#");
                    String json = inputs[1];
                    User user = JsonHandler.gson.fromJson(json, User.class);
                    user = UserHandler.getOnlineUserByUserName(user.getUsername());
                    UserHandler.onlineUsers.add(user);
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
