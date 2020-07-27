package Server.DataHandler.Socket;

import Client.DataHandler.ClientSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BankClientSocket {
    public static Socket bankClientSocket;
    public static int bankPort = 9999;
    public static String sendMessage(String msg) throws IOException {
        DataOutputStream dos = null;
        dos =new DataOutputStream(bankClientSocket.getOutputStream());
        dos.writeUTF(msg);
        DataInputStream dis = null;
        dis =new DataInputStream(bankClientSocket.getInputStream());
        String response = dis.readUTF();
        return response;
    }
    public static boolean connectToBankServer(){
        try {
            bankClientSocket = new Socket("127.0.0.1", bankPort);
        }catch (Exception e){
            System.out.println("can not connect to any server");
            return false;
        }
        System.out.println("connected to bank server");
        return true;
    }
}
