package Server.DataHandler;

import PathHandler.PathHandler;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerSocket {
    public static java.net.ServerSocket serverSocket;
    private static File managersFile = new File("Resources\\managers.txt");
    private static File buyersFile = new File("Resources\\buyers.txt");
    private static File sellersFile = new File("Resources\\sellers.txt");
    private static File usersFile = new File("Resources\\users.txt");
    private static File sellLogsFile = new File("Resources\\sellLogs.txt");
    private static File buyLogsFile = new File("Resources\\buyLogs.txt");
    private static File categoriesFile = new File("Resources\\categories.txt");
    private static File brandsFile = new File("Resources\\brands.txt");
    private static File requestsFile = new File("Resources\\requests.txt");
    private static File goodsFile = new File("Resources\\goods.txt");
    private static File discountsFile = new File("Resources\\discounts.txt");
    class RefreshThread extends Thread{
        Socket clientSocket;
        public RefreshThread(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                refresh(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class clientThread extends Thread{
        @Override
        public void run() {
            while (true) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    RefreshThread refreshThread = new RefreshThread(clientSocket);
                    refreshThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void refresh(Socket clientSocket) throws IOException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        while (true){
            String str = dis.readUTF();
            //do stuff
        }
    }
    private void sendAllDataToClient(Socket clientSocket) throws SQLException {
        String url = PathHandler.resourceURL+"ApProjectDataBase.db";
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+url);
        Statement statement = connection.createStatement();

    }
}
