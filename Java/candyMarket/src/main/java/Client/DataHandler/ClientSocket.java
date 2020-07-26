package Client.DataHandler;

import Client.Model.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {
    public static DataInputStream dis;
    public static DataOutputStream dos;
    public static Socket clientSocket;
    public static int port = 8888;
    public static ListenToServerThread listenToServerThread;
    public static boolean connectToServer() throws IOException {
        try {
            clientSocket = new Socket("127.0.0.1", port);
        }catch (Exception e){
            System.out.println("can not connect to any server");
            return false;
        }
        dis = new DataInputStream(clientSocket.getInputStream());
        dos = new DataOutputStream(clientSocket.getOutputStream());
        getAllData();
        listenToServerThread = new ListenToServerThread();
        listenToServerThread.start();
        return true;
    }
    public static void disconnectFromServer() throws IOException {
        MessageHandler.sendDisconnectedMessage();
        listenToServerThread.stop();
        clientSocket.close();
    }
    public static void getAllData() throws IOException {
        ManageInfo.allCategories = JsonHandler.getAllData(Category[].class);
        ManageInfo.allGoods = JsonHandler.getAllData(Good[].class);
        ManageInfo.allSellers = JsonHandler.getAllData(Seller[].class);
        ManageInfo.allBuyers = JsonHandler.getAllData(Buyer[].class);
        ManageInfo.allSupporters = JsonHandler.getAllData(Supporter[].class);
        ManageInfo.allManagers = JsonHandler.getAllData(Manager[].class);
        ManageInfo.allDiscounts = JsonHandler.getAllData(Discount[].class);
        ManageInfo.allComments = JsonHandler.getAllData(Comment[].class);
        ManageInfo.allRequests = JsonHandler.getAllData(Request[].class);
        ManageInfo.allSales = JsonHandler.getAllData(Sale[].class);
        ManageInfo.allScores = JsonHandler.getAllData(Score[].class);
        ManageInfo.allBuyLogs = JsonHandler.getAllData(BuyLog[].class);
        ManageInfo.allSellLogs = JsonHandler.getAllData(SellLog[].class);
        ManageInfo.allChats = JsonHandler.getAllData(Chat[].class);
        UserHandler.onlineUsers = MessageHandler.getLoginUsers();
        ManageInfo.allUsers.addAll(ManageInfo.allBuyers);
        ManageInfo.allUsers.addAll(ManageInfo.allManagers);
        ManageInfo.allUsers.addAll(ManageInfo.allSellers);
        ManageInfo.allUsers.addAll(ManageInfo.allSupporters);
    }
    static class ListenToServerThread extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    MessageHandler.getMessageListener();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
