package Client.DataHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageHandler {
    public static String requestStr;
    public static Socket clientSocket;
    public static int port = 8888;
    public void connectToServer() throws IOException {
        clientSocket = new Socket("127.0.0.1",port);
    }
    public String sendGetByIdMessage(Object id,String className) throws IOException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = null;
        dos =new DataOutputStream(clientSocket.getOutputStream());
        dos.writeUTF("C.getDataById#" + id.toString() + "#from "+className);
        while (true){
            String response = dis.readUTF();
            if(response.startsWith("S"))
                return response;
        }
    }
    public String sendGetAllDataMessage(String className) throws IOException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = null;
        dos =new DataOutputStream(clientSocket.getOutputStream());
        dos.writeUTF("C.getAllData#from#"+className);
        while (true){
            String response = dis.readUTF();
            if(response.startsWith("S"))
                return response;
        }
    }
    public void sendSetDataByIdMessage(Object id , String className,String json) throws IOException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = null;
        dos =new DataOutputStream(clientSocket.getOutputStream());
        dos.writeUTF("C.setDataById#"+id.toString()+"#from#"+className);
    }
}
